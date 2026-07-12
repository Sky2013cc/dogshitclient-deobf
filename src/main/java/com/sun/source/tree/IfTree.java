package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/IfTree.class */
public interface IfTree extends StatementTree {
    ExpressionTree getCondition();

    StatementTree getThenStatement();

    StatementTree getElseStatement();
}
