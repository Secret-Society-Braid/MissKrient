package org.braid.society.secret.misskrient.internal.auth;

import jakarta.annotation.Nonnull;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.extern.slf4j.Slf4j;
import org.braid.society.secret.misskrient.api.Cleanable;
import org.braid.society.secret.misskrient.api.Initializable;
import org.braid.society.secret.misskrient.api.account.AccountInfo;

/**
 * Store and control user's credentials as user can have multiple accounts.
 * This class is thread-safe.
 *
 * @see AccountInfo
 */
@Slf4j
public class CredentialHelper implements Initializable, Cleanable, Serializable {
  @Serial
  private static final long serialVersionUID = 8398260957136698260L;
  private ConcurrentHashMap<UUID, AccountInfo> credentialWarehouse;
  private static CredentialHelper instance;
  private final transient AtomicBoolean isInitialized = new AtomicBoolean(false);

  private static final String CREDENTIAL_HELPER_OBJECT_PATH = "generated/system/secure/credentialHelper";

  @Override
  public void initialize() {
    if(Files.notExists(Path.of(CREDENTIAL_HELPER_OBJECT_PATH))) {
      try {
        Files.createDirectories(Path.of(CREDENTIAL_HELPER_OBJECT_PATH));
        isInitialized.compareAndExchange(false, true);
      } catch (IOException e) {
        log.error("Failed to create credential helper object file. Please check the permission of the directory.", e);
        log.error("If the problem persists, please contact the developer.");
      }
    } else {
      try {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CREDENTIAL_HELPER_OBJECT_PATH));
        instance = (CredentialHelper) ois.readObject();
      } catch (IOException e) {
        log.error("Failed to read credential helper object file.", e);
        log.warn("The temporal helper will be created instead.");
      } catch (ClassNotFoundException noClass) {
        log.error("Failed to deserialize credential helper object.");
        log.error("The user must re-authenticate to use the app.", noClass);
        throw new RuntimeException(noClass);
      }
    }
  }

  @Override
  public void cleanUp() {

  }

  /**
   * Get the singleton instance of this class.
   * @return the instance of this class.
   */
  public static CredentialHelper summon() {
    return Objects.requireNonNullElseGet(instance, () -> instance = new CredentialHelper());
  }

  /**
   * Store the account information with the specified id.
   * <p>
   * Duplicates will NOT be checked. If the specified id already exists, it will be overwritten as normal mapping operation
   * @param unique the unique uuid of the account
   * @param accountInfo the account information to be stored
   * @return the {@link UUID} instance that is put in parameter.
   */
  @Nonnull
  public UUID store(@Nonnull UUID unique, @Nonnull AccountInfo accountInfo) {
    credentialWarehouse.put(unique, accountInfo);
    return unique;
  }

  /**
   * Store the account information with a random generated id.
   * @param accountInfo the account information to be stored
   * @return The {@link UUID} instance that is newly associated. Please use this instance to retrieve the account information in the future use.
   */
  @Nonnull
  public UUID store(@Nonnull AccountInfo accountInfo) {
    return store(UUID.randomUUID(), accountInfo);
  }

  /**
   * Retrieve the account information with the specified id.
   * @param unique the unique uuid of the account
   * @return the account information associated with the specified id.
   */
  @Nonnull
  public AccountInfo retrieve(@Nonnull UUID unique) {
    return credentialWarehouse.get(unique);
  }
}
