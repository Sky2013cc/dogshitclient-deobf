package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/CatchTree.class */
public interface CatchTree extends Tree {
    VariableTree getParameter();

    BlockTree getBlock();
}
