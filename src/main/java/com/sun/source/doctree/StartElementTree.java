package com.sun.source.doctree;

import java.util.List;
import javax.lang.model.element.Name;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/StartElementTree.class */
public interface StartElementTree extends DocTree {
    Name getName();

    List<? extends DocTree> getAttributes();

    boolean isSelfClosing();
}
