package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/AssignmentTree.class */
public interface AssignmentTree extends ExpressionTree {
    ExpressionTree getVariable();

    ExpressionTree getExpression();
}
