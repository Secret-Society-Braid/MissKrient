package org.braid.society.secret.misskrient.api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

@Slf4j
@RequiredArgsConstructor
public abstract class Auth {

  protected static final OkHttpClient client = new OkHttpClient();
  protected static final ObjectMapper mapper = new ObjectMapper();
  protected final AuthUrlConfig config;

  @Nonnull
  public String constructAuthUrl() {
    // Delegate generate process to AuthUrlConfig.
    log.info("Delegate generate URL process to AuthUrlConfig: {}", config.toString());
    return this.config.apply();
  }

  public abstract void authenticate();
}
