package org.braid.society.secret.mfm.internal.inline;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.braid.society.secret.mfm.api.MfmInline;
import org.braid.society.secret.mfm.api.MfmNode;


@Slf4j
public class MfmText extends MfmInline {


  public MfmText(List<MfmNode> children,
    Map<String, String> props) {
    super(children, props);
  }

  @Nonnull
  @Override
  public MfmNodeType getType() {
    return MfmNodeType.TEXT;
  }
}
