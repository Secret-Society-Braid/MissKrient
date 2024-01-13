package org.braid.society.secret.misskrient.internal.account;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.braid.society.secret.misskrient.api.account.AccountInfo;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = lombok.AccessLevel.PRIVATE)
public class AccountInfoImpl implements AccountInfo {

  private final String host;
  private final String accessToken;
  private final String userName;


  @Override
  public String getHost() {
    return this.host;
  }

  @Override
  public String getAccessToken() {
    return this.accessToken;
  }

  @Override
  public String getUserName() {
    return this.userName;
  }
}
