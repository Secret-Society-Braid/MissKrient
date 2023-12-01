package org.braid.society.secret.misskrient.internal.mfm;

import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.braid.society.secret.misskrient.api.mfm.MfmNode;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractMfmNode implements MfmNode {

  protected List<MfmNode> children;
  protected Map<String, String> props;

  public AbstractMfmNode(List<MfmNode> children, Map<String, String> props) {
    this.children = children;
    this.props = props;
  }

  @NonNull
  @Override
  public List<MfmNode> getChildren() {
    return this.children;
  }

  @NonNull
  @Override
  public Map<String, String> getProps() {
    return this.props;
  }
}
