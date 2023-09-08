package org.braid.society.secret.misskrient.misskey;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.JOptionPane;
import misskey4j.entity.contant.Scope;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.braid.society.secret.misskrient.api.account.AccountInfo;
import org.braid.society.secret.misskrient.api.auth.Auth;
import org.braid.society.secret.misskrient.api.auth.AuthUrlConfig;
import org.braid.society.secret.misskrient.api.database.Repository;
import org.braid.society.secret.misskrient.internal.account.AccountRepository;
import org.braid.society.secret.misskrient.internal.auth.misskey.MiAuth;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
@Disabled("This test requires manual input.")
class MiAuthTest {

  private static final Logger log = LoggerFactory.getLogger(AccessTokenTest.class);
  private static final String HOST = "https://misskey.io";
  private static final OkHttpClient client = new OkHttpClient();
  private static final List<Scope> scopes = Arrays.stream(Scope.ALL).toList();

  @Mock
  AuthUrlConfig config;
  Repository<AccountInfo> r;

  @Test
  void wrapperTest() {
    final String sessionUuid = UUID.randomUUID().toString();
    final String expectedUrl =
      "https://misskey.io/miauth/" + sessionUuid + "?name=MissKrient&permission=" + Joiner.on(",")
        .join(scopes);
    // prepare
    when(config.getHostname()).thenReturn("misskey.io");
    when(config.getSessionUuid()).thenReturn(sessionUuid);
    when(config.build()).thenReturn(expectedUrl);

    r = new AccountRepository();

    // test
    Auth auth = new MiAuth(config, r);

    String url = auth.constructAuthUrl();
    System.out.println(url);

    assertThat(url).startsWith("https://misskey.io/miauth/");
    assertThat(url).contains(sessionUuid);
    assertThat(url).contains("name=MissKrient");
    assertThat(url).contains("permission=" + Joiner.on(",").join(scopes));

    JOptionPane.showConfirmDialog(null, "Proceed after you have logged in.");

    auth.authenticate();

    verify(config, times(1)).build();
  }

  @Test
  void test() {
    final String sessionUuid = UUID.randomUUID().toString();
    Map<String, String> query = Map.of(
      "name", "MissKrient",
      "permission", Joiner.on(",").join(scopes)
    );
    MapJoiner joiner = Joiner.on("&").withKeyValueSeparator("=");
    String requestUrl = Joiner.on("").join(HOST, "/miauth/", sessionUuid, "?", joiner.join(query));
    log.info("Request URL: {}", requestUrl);

    JOptionPane.showConfirmDialog(null, "Press OK after you have logged in.");

    String checkUrl = String.format("%s/api/miauth/%s/check", HOST, sessionUuid);
    log.info("Check URL: {}", checkUrl);
    RequestBody requestBody = RequestBody.create("{}", MediaType.get("application/json"));
    Request req = new Request.Builder()
      .url(checkUrl)
      .post(requestBody)
      .addHeader("Content-Type", "application/json")
      .build();
    try (Response r = client.newCall(req).execute()) {

      String response = r.body().string();
      assertThat(response).isNotEmpty();

      ObjectMapper mapper = new ObjectMapper();
      JsonNode node = mapper.readTree(response);

      assertThat(node.get("token").asText()).isNotEmpty();
      assertThat(node.get("user").toString()).isNotEmpty();

      log.info("Response: {}", node.toPrettyString());
    } catch (IOException e) {
      fail(e);
    }
  }
}
