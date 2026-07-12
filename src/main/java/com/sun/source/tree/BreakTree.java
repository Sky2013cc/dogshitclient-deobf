package com.sun.source.tree;

import javax.lang.model.element.Name;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/BreakTree.class */
public interface BreakTree extends StatementTree {
    Name getLabel();
}
