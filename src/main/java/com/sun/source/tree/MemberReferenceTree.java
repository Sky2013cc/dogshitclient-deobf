package com.sun.source.tree;

import java.util.List;
import javax.lang.model.element.Name;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/MemberReferenceTree.class */
public interface MemberReferenceTree extends ExpressionTree {

    @Exported
    /* loaded from: target.jar:com/sun/source/tree/MemberReferenceTree$ReferenceMode.class */
    public enum ReferenceMode {
        INVOKE,
        NEW
    }

    ReferenceMode getMode();

    ExpressionTree getQualifierExpression();

    Name getName();

    List<? extends ExpressionTree> getTypeArguments();
}
