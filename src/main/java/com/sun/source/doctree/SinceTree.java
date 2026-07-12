package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/SinceTree.class */
public interface SinceTree extends BlockTagTree {
    List<? extends DocTree> getBody();
}
