package com.sun.source.tree;

import java.util.List;
import javax.lang.model.element.Name;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/TypeParameterTree.class */
public interface TypeParameterTree extends Tree {
    Name getName();

    List<? extends Tree> getBounds();

    List<? extends AnnotationTree> getAnnotations();
}
