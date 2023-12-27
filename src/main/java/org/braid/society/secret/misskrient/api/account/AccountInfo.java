package org.braid.society.secret.misskrient.api.account;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import misskey4j.Misskey;
import misskey4j.MisskeyFactory;
import org.braid.society.secret.misskrient.internal.account.AccountInfoImpl;

@JsonDeserialize(as = AccountInfoImpl.class)
public interface AccountInfo {

  String getHost();

  String getAccessToken();

  String getUserName();

  default Misskey getInstance() {
    return MisskeyFactory.getInstanceWithOwnedAccessToken(this.getHost(), this.getAccessToken());
  }
}
