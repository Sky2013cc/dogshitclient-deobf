package com.sun.source.tree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/MethodInvocationTree.class */
public interface MethodInvocationTree extends ExpressionTree {
    List<? extends Tree> getTypeArguments();

    ExpressionTree getMethodSelect();

    List<? extends ExpressionTree> getArguments();
}
