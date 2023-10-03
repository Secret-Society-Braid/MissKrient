package org.braid.society.secret.misskrient.internal.mfm.inline;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.braid.society.secret.misskrient.api.mfm.MfmInline;
import org.braid.society.secret.misskrient.api.mfm.MfmNode;

@Slf4j
@NoArgsConstructor
public class MfmItalic extends MfmInline {

  public MfmItalic(List<MfmNode> children,
    Map<String, String> props) {
    super(children, props);
  }

  @Nonnull
  @Override
  public MfmNodeType getType() {
    return MfmNodeType.ITALIC;
  }
}
