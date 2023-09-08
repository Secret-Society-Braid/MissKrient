package org.braid.society.secret.misskrient.api.account;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.braid.society.secret.misskrient.internal.account.AccountInfoImpl;

@JsonDeserialize(as = AccountInfoImpl.class)
public interface AccountInfo {

  String getHost();

  String getAccessToken();

  String getUserName();
}
