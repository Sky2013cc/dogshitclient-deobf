package com.sun.source.tree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/ParameterizedTypeTree.class */
public interface ParameterizedTypeTree extends Tree {
    Tree getType();

    List<? extends Tree> getTypeArguments();
}
