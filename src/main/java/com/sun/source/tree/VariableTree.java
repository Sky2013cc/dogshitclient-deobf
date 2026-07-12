package com.sun.source.tree;

import javax.lang.model.element.Name;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/VariableTree.class */
public interface VariableTree extends StatementTree {
    ModifiersTree getModifiers();

    Name getName();

    ExpressionTree getNameExpression();

    Tree getType();

    ExpressionTree getInitializer();
}
