package com.sun.source.tree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/NewClassTree.class */
public interface NewClassTree extends ExpressionTree {
    ExpressionTree getEnclosingExpression();

    List<? extends Tree> getTypeArguments();

    ExpressionTree getIdentifier();

    List<? extends ExpressionTree> getArguments();

    ClassTree getClassBody();
}
