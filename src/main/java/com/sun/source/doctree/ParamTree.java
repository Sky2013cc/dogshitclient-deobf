package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/ParamTree.class */
public interface ParamTree extends BlockTagTree {
    boolean isTypeParameter();

    IdentifierTree getName();

    List<? extends DocTree> getDescription();
}
