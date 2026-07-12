package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/SerialTree.class */
public interface SerialTree extends BlockTagTree {
    List<? extends DocTree> getDescription();
}
