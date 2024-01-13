package org.braid.society.secret.mfm.internal.inline;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.braid.society.secret.mfm.api.MfmInline;
import org.braid.society.secret.mfm.api.MfmNode;


@Slf4j
public class MfmUnicodeEmoji extends MfmInline {

  public MfmUnicodeEmoji(List<MfmNode> children,
    Map<String, String> props) {
    super(children, props);
  }

  @Override
  @Nonnull
  public MfmNodeType getType() {
    return MfmNodeType.UNICODEEMOJI;
  }
}
