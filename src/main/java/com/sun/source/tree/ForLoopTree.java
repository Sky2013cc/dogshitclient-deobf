package com.sun.source.tree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/ForLoopTree.class */
public interface ForLoopTree extends StatementTree {
    List<? extends StatementTree> getInitializer();

    ExpressionTree getCondition();

    List<? extends ExpressionStatementTree> getUpdate();

    StatementTree getStatement();
}
