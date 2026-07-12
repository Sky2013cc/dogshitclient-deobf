package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/UnknownInlineTagTree.class */
public interface UnknownInlineTagTree extends InlineTagTree {
    List<? extends DocTree> getContent();
}
