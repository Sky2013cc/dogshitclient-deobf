package com.sun.source.tree;

import java.util.List;
import java.util.Set;
import javax.lang.model.element.Modifier;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/ModifiersTree.class */
public interface ModifiersTree extends Tree {
    Set<Modifier> getFlags();

    List<? extends AnnotationTree> getAnnotations();
}
