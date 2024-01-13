package org.braid.society.secret.misskrient.api.builder;

import java.util.List;
import java.util.UUID;
import misskey4j.entity.contant.Scope;
import org.braid.society.secret.misskrient.api.auth.AuthUrlConfig;
import org.braid.society.secret.misskrient.internal.auth.misskey.MiAuthUrlConfig;

public class MiAuthUrlConfigBuilder {

  private final String sessionUuid;
  private String hostname;
  private List<Scope> scopes;

  private MiAuthUrlConfigBuilder() {
    this.sessionUuid = UUID.randomUUID().toString();
  }

  public static MiAuthUrlConfigBuilder newBuilder() {
    return new MiAuthUrlConfigBuilder();
  }

  public MiAuthUrlConfigBuilder setHostname(String hostname) {
    this.hostname = hostname;
    return this;
  }

  public MiAuthUrlConfigBuilder setScopes(List<Scope> scopes) {
    this.scopes = scopes;
    return this;
  }

  public MiAuthUrlConfigBuilder setScopes(Scope... scopes) {
    this.scopes = List.of(scopes);
    return this;
  }

  public AuthUrlConfig build() {
    return MiAuthUrlConfig.newConfig(this.sessionUuid)
      .hostname(this.hostname)
      .scopes(this.scopes.toArray(new Scope[0]));
  }
}
