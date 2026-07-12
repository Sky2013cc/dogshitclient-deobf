package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/SynchronizedTree.class */
public interface SynchronizedTree extends StatementTree {
    ExpressionTree getExpression();

    BlockTree getBlock();
}
