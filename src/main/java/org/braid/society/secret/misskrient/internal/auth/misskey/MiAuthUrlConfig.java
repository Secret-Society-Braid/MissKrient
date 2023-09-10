package org.braid.society.secret.misskrient.internal.auth.misskey;

import com.google.common.base.Joiner;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import lombok.ToString;
import misskey4j.entity.contant.Scope;
import org.braid.society.secret.misskrient.api.auth.AuthUrlConfig;

@ToString
public class MiAuthUrlConfig implements AuthUrlConfig {

  private static final String BASE_URL = "https://%s/miauth/%s?name=MissKrient&permission=%s";
  private final String sessionUuid;
  private String hostname;
  private List<Scope> scopes;

  private MiAuthUrlConfig(String sessionUuid) {
    this.hostname = "misskey.io";
    this.sessionUuid = sessionUuid;
    this.scopes = Arrays.stream(Scope.ALL).toList();
  }

  public static MiAuthUrlConfig newConfig(String sessionUuid) {
    return new MiAuthUrlConfig(sessionUuid);
  }

  private static String joinScopes(List<Scope> scopes) {
    Joiner j = Joiner.on(",").skipNulls();
    return j.join(scopes);
  }

  public MiAuthUrlConfig hostname(String hostname) {
    this.hostname = hostname;
    return this;
  }

  public MiAuthUrlConfig scopes(Scope... scopes) {
    this.scopes = Arrays.stream(scopes).toList();
    return this;
  }

  @Override
  @Nonnull
  public String getHostname() {
    return this.hostname;
  }

  @Override
  @Nullable
  public String getSessionUuid() {
    return this.sessionUuid;
  }

  @Override
  @Nonnull
  public List<Scope> getScopes() {
    return this.scopes;
  }

  @Override
  public String apply() {
    return String.format(BASE_URL, this.getHostname(), this.getSessionUuid(),
      joinScopes(getScopes()));
  }
}
