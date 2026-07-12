package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/DoWhileLoopTree.class */
public interface DoWhileLoopTree extends StatementTree {
    ExpressionTree getCondition();

    StatementTree getStatement();
}
