package org.braid.society.secret.misskrient.internal.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.braid.society.secret.misskrient.api.account.AccountInfo;
import org.braid.society.secret.misskrient.api.database.Repository;

@Slf4j
public class AccountRepository implements Repository<AccountInfo> {

  private static final String REPO_FILE_NAME = "repo/accounts.json";
  private static final ObjectMapper MAPPER = new ObjectMapper();
  private List<AccountInfo> accounts;

  public AccountRepository() {
    try {
      Path p = Paths.get(REPO_FILE_NAME);
      Files.createDirectories(p.getParent());
      if (Files.notExists(p)) {
        Files.createFile(p);
      }
    } catch (IOException e) {
      log.error("Error while creating account info repository.", e);
    }
  }

  private void setAccounts() {
    this.accounts = this.load();
  }

  @Override
  public void save(AccountInfo accountInfo) {
    if (this.accounts == null) {
      this.setAccounts();
    }
    try {
      this.write(this.accounts);
    } catch (IOException e) {
      log.error("Error while saving account info.", e);
    }
  }

  @Override
  public AccountInfo get(int id) {
    if (this.isEmpty()) {
      return null;
    }
    return this.accounts.get(id);
  }

  @Override
  public boolean delete(int id) {
    if (this.accounts == null) {
      this.setAccounts();
    }
    this.accounts.remove(id);
    try {
      this.write(this.accounts);
    } catch (IOException e) {
      log.error("Error while deleting account info.", e);
      return false;
    }
    return true;
  }

  private List<AccountInfo> load() {
    try {
      List<String> lines = Files.readAllLines(Paths.get(REPO_FILE_NAME));
      if(lines.isEmpty()) {
        return new ArrayList<>();
      }
      return MAPPER.readValue(
        Paths.get(REPO_FILE_NAME).toFile(),
        MAPPER.getTypeFactory().constructCollectionType(List.class, AccountInfo.class));
    } catch (IOException e) {
      log.error("Error while loading account info.", e);
      return new ArrayList<>();
    }
  }

  private void write(List<AccountInfo> accounts) throws IOException {
    MAPPER.writer().writeValue(Paths.get(REPO_FILE_NAME).toFile(), accounts);
  }

  @Override
  public boolean isEmpty() {
    if (this.accounts == null) {
      this.setAccounts();
    }
    return this.accounts.isEmpty();
  }
}
