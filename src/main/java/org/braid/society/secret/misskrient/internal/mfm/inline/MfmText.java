package org.braid.society.secret.misskrient.internal.mfm.inline;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.braid.society.secret.misskrient.api.mfm.MfmInline;
import org.braid.society.secret.misskrient.api.mfm.MfmNode;

@NoArgsConstructor
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
