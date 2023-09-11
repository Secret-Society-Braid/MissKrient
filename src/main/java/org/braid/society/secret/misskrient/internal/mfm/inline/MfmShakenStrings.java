package org.braid.society.secret.misskrient.internal.mfm.inline;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.braid.society.secret.misskrient.api.mfm.MfmInline;
import org.braid.society.secret.misskrient.api.mfm.MfmNode;

/**
 * MFM inline syntax: Shaken strings.
 * <p>
 * This syntax is currently planned to be removed in the future Misskey Version.
 * <p>
 * Usage restrictions:
 * <ul>
 *   <li>Inline Parser will be applied to the inside contents.</li>
 *   <li>All characters, and Return character can be used.</li>
 *   <li>Inside content must not be empty.</li>
 * </ul>
 *
 * @since 0.0.1
 * @deprecated This syntax is currenly planned to be removed in the future Misskey Version. Alternative syntax is presented.
 * @see MfmFunction
 */
@NoArgsConstructor
@Slf4j
@Deprecated(forRemoval = true, since = "0.0.1")
public class MfmShakenStrings extends MfmInline {

  public MfmShakenStrings(List<MfmNode> children,
      Map<String, String> props) {
    super(children, props);
  }

  @Override
  @Nonnull
  public MfmNodeType getType() {
    return MfmNodeType.SHAKEN;
  }
}
