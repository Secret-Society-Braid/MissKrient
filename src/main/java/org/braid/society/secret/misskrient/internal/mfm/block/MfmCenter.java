package org.braid.society.secret.misskrient.internal.mfm.block;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.braid.society.secret.misskrient.api.mfm.MfmBlock;
import org.braid.society.secret.misskrient.api.mfm.MfmNode;


@Slf4j
public class MfmCenter extends MfmBlock {

  public MfmCenter(List<MfmNode> children,
    Map<String, String> props) {
    super(children, props);
  }

  @Nonnull
  @Override
  public MfmNodeType getType() {
    return MfmNodeType.CENTER;
  }
}
