package com.sun.source.tree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/ErroneousTree.class */
public interface ErroneousTree extends ExpressionTree {
    List<? extends Tree> getErrorTrees();
}
