package org.braid.society.secret.misskrient.api.database;

import org.braid.society.secret.misskrient.api.account.AccountInfo;
import org.braid.society.secret.misskrient.internal.account.AccountRepository;

public class RepositorySelector {

  public static Repository<AccountInfo> forAccount() {
    return new AccountRepository();
  }
}
