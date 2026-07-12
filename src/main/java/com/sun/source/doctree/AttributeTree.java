package com.sun.source.doctree;

import java.util.List;
import javax.lang.model.element.Name;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/AttributeTree.class */
public interface AttributeTree extends DocTree {

    @Exported
    /* loaded from: target.jar:com/sun/source/doctree/AttributeTree$ValueKind.class */
    public enum ValueKind {
        EMPTY,
        UNQUOTED,
        SINGLE,
        DOUBLE
    }

    Name getName();

    ValueKind getValueKind();

    List<? extends DocTree> getValue();
}
