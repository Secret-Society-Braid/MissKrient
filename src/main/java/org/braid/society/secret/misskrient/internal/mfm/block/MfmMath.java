package org.braid.society.secret.misskrient.internal.mfm.block;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.braid.society.secret.misskrient.api.mfm.MfmBlock;
import org.braid.society.secret.misskrient.api.mfm.MfmNode;

@NoArgsConstructor
@Slf4j
public class MfmMath extends MfmBlock {

  public MfmMath(List<MfmNode> children,
    Map<String, String> props) {
    super(children, props);
  }

  @Override
  @Nonnull
  public MfmNodeType getType() {
    return MfmNodeType.MATH;
  }
}
