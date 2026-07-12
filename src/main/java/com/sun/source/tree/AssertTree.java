package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/AssertTree.class */
public interface AssertTree extends StatementTree {
    ExpressionTree getCondition();

    ExpressionTree getDetail();
}
