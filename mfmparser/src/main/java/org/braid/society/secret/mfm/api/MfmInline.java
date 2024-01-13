package org.braid.society.secret.mfm.api;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.braid.society.secret.mfm.internal.AbstractMfmNode;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class MfmInline extends AbstractMfmNode {

  public MfmInline(List<MfmNode> children, Map<String, String> props) {
    super(children, props);
  }

  @Nonnull
  @Override
  public MfmNode.MfmSyntax getSyntax() {
    return MfmSyntax.INLINE;
  }

}
