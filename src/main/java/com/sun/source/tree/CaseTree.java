package com.sun.source.tree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/CaseTree.class */
public interface CaseTree extends Tree {
    ExpressionTree getExpression();

    List<? extends StatementTree> getStatements();
}
