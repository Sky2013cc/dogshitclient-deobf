package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/ThrowsTree.class */
public interface ThrowsTree extends BlockTagTree {
    ReferenceTree getExceptionName();

    List<? extends DocTree> getDescription();
}
