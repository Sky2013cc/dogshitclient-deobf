package com.sun.source.tree;

import javax.lang.model.type.TypeKind;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/PrimitiveTypeTree.class */
public interface PrimitiveTypeTree extends Tree {
    TypeKind getPrimitiveTypeKind();
}
