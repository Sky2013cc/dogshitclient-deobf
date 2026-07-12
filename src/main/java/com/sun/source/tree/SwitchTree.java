package com.sun.source.tree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/SwitchTree.class */
public interface SwitchTree extends StatementTree {
    ExpressionTree getExpression();

    List<? extends CaseTree> getCases();
}
