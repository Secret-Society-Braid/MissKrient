package org.braid.society.secret.misskrient.api.mfm;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.braid.society.secret.misskrient.internal.mfm.AbstractMfmNode;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class MfmBlock extends AbstractMfmNode {

  public MfmBlock(List<MfmNode> children, Map<String, String> props) {
    super(children, props);
  }

  @Nonnull
  @Override
  public MfmSyntax getSyntax() {
    return MfmSyntax.BLOCK;
  }

}
