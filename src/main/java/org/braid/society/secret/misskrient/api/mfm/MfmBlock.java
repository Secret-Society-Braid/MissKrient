package org.braid.society.secret.misskrient.api.mfm;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class MfmBlock implements MfmNode {

  protected List<MfmNode> children;
  protected Map<String, String> props;

  public MfmBlock(List<MfmNode> children, Map<String, String> props) {
    this.children = children;
    this.props = props;
  }

  @Nonnull
  @Override
  public MfmSyntax getSyntax() {
    return MfmSyntax.BLOCK;
  }

  // TODO: add common methods for Block type node
}
