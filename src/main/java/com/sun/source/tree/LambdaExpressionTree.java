package com.sun.source.tree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/LambdaExpressionTree.class */
public interface LambdaExpressionTree extends ExpressionTree {

    @Exported
    /* loaded from: target.jar:com/sun/source/tree/LambdaExpressionTree$BodyKind.class */
    public enum BodyKind {
        EXPRESSION,
        STATEMENT
    }

    List<? extends VariableTree> getParameters();

    Tree getBody();

    BodyKind getBodyKind();
}
