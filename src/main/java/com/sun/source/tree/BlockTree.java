package com.sun.source.tree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/BlockTree.class */
public interface BlockTree extends StatementTree {
    boolean isStatic();

    List<? extends StatementTree> getStatements();
}
