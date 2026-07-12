package com.sun.source.tree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/IntersectionTypeTree.class */
public interface IntersectionTypeTree extends Tree {
    List<? extends Tree> getBounds();
}
