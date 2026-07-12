package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/LinkTree.class */
public interface LinkTree extends InlineTagTree {
    ReferenceTree getReference();

    List<? extends DocTree> getLabel();
}
