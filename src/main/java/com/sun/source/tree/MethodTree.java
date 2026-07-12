package com.sun.source.tree;

import java.util.List;
import javax.lang.model.element.Name;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/MethodTree.class */
public interface MethodTree extends Tree {
    ModifiersTree getModifiers();

    Name getName();

    Tree getReturnType();

    List<? extends TypeParameterTree> getTypeParameters();

    List<? extends VariableTree> getParameters();

    VariableTree getReceiverParameter();

    List<? extends ExpressionTree> getThrows();

    BlockTree getBody();

    Tree getDefaultValue();
}
