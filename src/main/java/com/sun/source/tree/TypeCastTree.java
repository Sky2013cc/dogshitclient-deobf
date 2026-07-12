package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/TypeCastTree.class */
public interface TypeCastTree extends ExpressionTree {
    Tree getType();

    ExpressionTree getExpression();
}
