package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/WhileLoopTree.class */
public interface WhileLoopTree extends StatementTree {
    ExpressionTree getCondition();

    StatementTree getStatement();
}
