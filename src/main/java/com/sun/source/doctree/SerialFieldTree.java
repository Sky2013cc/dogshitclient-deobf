package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/SerialFieldTree.class */
public interface SerialFieldTree extends BlockTagTree {
    IdentifierTree getName();

    ReferenceTree getType();

    List<? extends DocTree> getDescription();
}
