package com.sun.source.tree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/UnionTypeTree.class */
public interface UnionTypeTree extends Tree {
    List<? extends Tree> getTypeAlternatives();
}
