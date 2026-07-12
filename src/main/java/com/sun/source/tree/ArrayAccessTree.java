package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/ArrayAccessTree.class */
public interface ArrayAccessTree extends ExpressionTree {
    ExpressionTree getExpression();

    ExpressionTree getIndex();
}
