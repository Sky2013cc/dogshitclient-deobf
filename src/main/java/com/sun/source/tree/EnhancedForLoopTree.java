package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/EnhancedForLoopTree.class */
public interface EnhancedForLoopTree extends StatementTree {
    VariableTree getVariable();

    ExpressionTree getExpression();

    StatementTree getStatement();
}
