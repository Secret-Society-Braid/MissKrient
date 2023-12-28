package org.braid.society.secret.misskrient.misskey;

import static com.google.common.truth.Truth.assertThat;

import misskey4j.Misskey;
import misskey4j.api.request.i.IRequest;
import misskey4j.api.response.i.IResponse;
import org.braid.society.secret.misskrient.api.account.AccountInfo;
import org.braid.society.secret.misskrient.api.database.RepositorySelector;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InstanceWithAccountTest {

  @Test
  @Disabled("Intended to be run locally")
  void fromAccountTest() {
    AccountInfo i = RepositorySelector.forAccount().get(0);
    Misskey m = i.getInstance();
    IResponse r = m.accounts().i(IRequest.builder().build()).get();

    assertThat(r.getUsername()).isEqualTo(i.getUserName());
    assertThat(m.getHost()).contains(i.getHost());
  }
}
