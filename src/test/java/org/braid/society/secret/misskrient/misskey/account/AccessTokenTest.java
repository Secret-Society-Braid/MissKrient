package org.braid.society.secret.misskrient.misskey.account;

import static com.google.common.truth.Truth.assertThat;

import javax.swing.JOptionPane;
import misskey4j.Misskey;
import misskey4j.MisskeyFactory;
import misskey4j.api.request.CreateAppRequest;
import misskey4j.api.request.GenerateAuthSessionRequest;
import misskey4j.api.request.UserKeyAuthSessionRequest;
import misskey4j.api.response.CreateAppResponse;
import misskey4j.api.response.GenerateAuthSessionResponse;
import misskey4j.api.response.UserKeyAuthSessionResponse;
import misskey4j.entity.contant.Scope;
import misskey4j.entity.share.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Disabled("This test requires manual input.")
class AccessTokenTest {

  private static final Logger log = LoggerFactory.getLogger(AccessTokenTest.class);
  private static final String HOST = "https://misskey.io";

  @Test
  void test() {
    Misskey misskey = MisskeyFactory.getInstance(HOST);

    Response<CreateAppResponse> createAppResponse = misskey.app().createApp(
      CreateAppRequest.builder()
        .name("MissKrient")
        .description("Misskey Client for PC made by Ranfa")
        .callbackUrl("https://localhost:8080")
        .permission(Scope.ALL)
        .build());

    assertThat(createAppResponse.get().getSecret()).isNotEmpty();
    log.info("Client Secret: {}", createAppResponse.get().getSecret());

    Response<GenerateAuthSessionResponse> generateAuthSessionResponse = misskey.auth()
      .sessionGenerate(
        GenerateAuthSessionRequest.builder()
          .appSecret(createAppResponse.get().getSecret())
          .build()
      );

    log.info("Auth Session: {}", generateAuthSessionResponse.get().getToken());

    String token = JOptionPane.showInputDialog("Enter the token: ");

    Response<UserKeyAuthSessionResponse> userKeyAuthSessionResponse = misskey.auth().sessionUserKey(
      UserKeyAuthSessionRequest.builder()
        .token(token)
        .appSecret(createAppResponse.get().getSecret())
        .build()
    );

    assertThat(userKeyAuthSessionResponse.get().getAccessToken()).isNotEmpty();
    assertThat(userKeyAuthSessionResponse.get().getUser().getName()).isNotEmpty();
  }
}
