package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/ReturnTree.class */
public interface ReturnTree extends BlockTagTree {
    List<? extends DocTree> getDescription();
}
