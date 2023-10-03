package org.braid.society.secret.misskrient.api.mfm;

import jakarta.annotation.Nonnull;
import java.util.Arrays;
import lombok.Getter;

public interface MfmNode {
  // TODO: define common methods and fields

  @Nonnull
  MfmNodeType getType();

  @Nonnull
  MfmSyntax getSyntax();

  enum MfmSyntax {
    BLOCK,
    INLINE;

    public static MfmSyntax fromString(String s) {
      return MfmSyntax.valueOf(s.toUpperCase());
    }

    @Override
    public String toString() {
      return super.toString().toLowerCase();
    }
  }

  @Getter
  enum MfmNodeType {
    QUOTE,
    SEARCH,
    CODE("blockCode"),
    MATH("mathBlock"),
    CENTER,
    SHAKEN("fn"),
    BOLD,
    SMALL,
    ITALIC,
    STRIKE,
    INLINECODE("inlineCode"),
    INLINEMATH("mathInline"),
    MENTION,
    HASHTAG,
    URL,
    LINK,
    EMOJICODE("emojiCode"),
    FUNCTION("fn"),
    UNICODEEMOJI("unicodeEmoji"),
    TEXT;

    private final String type;

    MfmNodeType() {
      this.type = this.getType().toLowerCase();
    }

    MfmNodeType(String type) {
      this.type = type;
    }

    public static MfmNodeType fromString(String s) {
      return MfmNodeType.valueOf(
        Arrays
          .stream(MfmNodeType.values())
          .map(MfmNodeType::getType)
          .filter(s::equalsIgnoreCase)
          .findFirst()
          .orElse("text"));
    }

    @Override
    public String toString() {
      return "MfmNodeType{" +
        "type='" + getType() + '\'' +
        '}';
    }
  }
}
