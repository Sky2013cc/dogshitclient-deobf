package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/SeeTree.class */
public interface SeeTree extends BlockTagTree {
    List<? extends DocTree> getReference();
}
