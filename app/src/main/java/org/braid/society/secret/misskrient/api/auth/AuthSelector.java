package org.braid.society.secret.misskrient.api.auth;

import org.braid.society.secret.misskrient.api.account.AccountInfo;
import org.braid.society.secret.misskrient.api.database.Repository;
import org.braid.society.secret.misskrient.internal.auth.misskey.MiAuth;

public class AuthSelector {

  public static Auth forMisskey(AuthUrlConfig config, Repository<AccountInfo> repo) {
    return new MiAuth(config, repo);
  }
}
