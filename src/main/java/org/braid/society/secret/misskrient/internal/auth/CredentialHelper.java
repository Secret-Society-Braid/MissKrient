package org.braid.society.secret.misskrient.internal.auth;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.braid.society.secret.misskrient.api.account.AccountInfo;

@RequiredArgsConstructor
public class CredentialHelper {

  private final ConcurrentHashMap<UUID, AccountInfo> credentialWarehouse;
}
