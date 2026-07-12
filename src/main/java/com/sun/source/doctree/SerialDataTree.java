package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/SerialDataTree.class */
public interface SerialDataTree extends BlockTagTree {
    List<? extends DocTree> getDescription();
}
