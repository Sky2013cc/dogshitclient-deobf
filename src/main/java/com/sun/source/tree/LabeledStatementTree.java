package com.sun.source.tree;

import javax.lang.model.element.Name;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/LabeledStatementTree.class */
public interface LabeledStatementTree extends StatementTree {
    Name getLabel();

    StatementTree getStatement();
}
