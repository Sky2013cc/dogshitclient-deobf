package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/ConditionalExpressionTree.class */
public interface ConditionalExpressionTree extends ExpressionTree {
    ExpressionTree getCondition();

    ExpressionTree getTrueExpression();

    ExpressionTree getFalseExpression();
}
