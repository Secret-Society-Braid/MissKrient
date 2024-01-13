package org.braid.society.secret.mfm.api;

import jakarta.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NonNull;

public interface MfmNode {
  // TODO: define common methods and fields

  @Nonnull
  MfmNodeType getType();

  @Nonnull
  MfmSyntax getSyntax();

  @Nonnull
  List<MfmNode> getChildren();

  @NonNull
  Map<String, String> getProps();

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
      if(s.equalsIgnoreCase("fn")) {
        return FUNCTION;
      }
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
