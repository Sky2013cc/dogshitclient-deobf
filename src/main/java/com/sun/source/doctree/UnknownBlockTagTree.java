package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/UnknownBlockTagTree.class */
public interface UnknownBlockTagTree extends BlockTagTree {
    List<? extends DocTree> getContent();
}
