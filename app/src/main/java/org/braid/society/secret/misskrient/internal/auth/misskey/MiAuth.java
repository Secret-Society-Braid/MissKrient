package org.braid.society.secret.misskrient.internal.auth.misskey;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Joiner;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.braid.society.secret.misskrient.api.account.AccountInfo;
import org.braid.society.secret.misskrient.api.auth.Auth;
import org.braid.society.secret.misskrient.api.auth.AuthUrlConfig;
import org.braid.society.secret.misskrient.api.database.Repository;
import org.braid.society.secret.misskrient.internal.account.AccountInfoImpl;

@Slf4j
public class MiAuth extends Auth {

  private final Repository<AccountInfo> aRepo;

  public MiAuth(AuthUrlConfig config, Repository<AccountInfo> aRepo) {
    super(config);
    this.aRepo = aRepo;
  }

  @Override
  public void authenticate() {
    Request req = new Request.Builder()
      .url(this.constructCheckUrl())
      .addHeader("Content-Type", "application/json")
      .post(RequestBody.create("{}", MediaType.parse("application/json")))
      .build();
    try (Response r = client.newCall(req).execute()) {
      JsonNode node = mapper.readTree(r.body().string());
      AccountInfo i = new AccountInfoImpl(this.config.getHostname(), node.get("token").asText(),
        node.get("user").get("username").asText());
      this.aRepo.save(i);
    } catch (IOException e) {
      log.error("Error while checking auth status.", e);
      log.warn("Authentication not completed.");
      return;
    }
  }

  private String constructCheckUrl() {
    Joiner j = Joiner.on("");
    return j.join(
      "https://",
      this.config.getHostname(),
      "/api/miauth/",
      this.config.getSessionUuid(),
      "/check");
  }
}
