package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/ImportTree.class */
public interface ImportTree extends Tree {
    boolean isStatic();

    Tree getQualifiedIdentifier();
}
