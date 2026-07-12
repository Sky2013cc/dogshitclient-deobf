package com.sun.source.tree;

import javax.lang.model.element.Name;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/MemberSelectTree.class */
public interface MemberSelectTree extends ExpressionTree {
    ExpressionTree getExpression();

    Name getIdentifier();
}
