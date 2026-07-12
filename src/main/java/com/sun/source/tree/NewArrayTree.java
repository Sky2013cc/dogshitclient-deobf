package com.sun.source.tree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/NewArrayTree.class */
public interface NewArrayTree extends ExpressionTree {
    Tree getType();

    List<? extends ExpressionTree> getDimensions();

    List<? extends ExpressionTree> getInitializers();

    List<? extends AnnotationTree> getAnnotations();

    List<? extends List<? extends AnnotationTree>> getDimAnnotations();
}
