package org.braid.society.secret.misskrient.api.auth;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.List;
import misskey4j.entity.contant.Scope;

public interface AuthUrlConfig {

  String build();

  @Nonnull
  String getHostname();

  @Nullable
  String getSessionUuid();

  @Nonnull
  List<Scope> getScopes();

}
