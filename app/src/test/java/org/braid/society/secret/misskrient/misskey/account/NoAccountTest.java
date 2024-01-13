package org.braid.society.secret.misskrient.misskey.account;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.braid.society.secret.misskrient.internal.account.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NoAccountTest {

  @Spy
  AccountRepository r;

  @Test
  void noAccountTest() {
    r = spy(AccountRepository.class);
    when(r.isEmpty()).thenReturn(true);

    assertThat(r.get(0)).isNull();
    verify(r).isEmpty();
  }
}
