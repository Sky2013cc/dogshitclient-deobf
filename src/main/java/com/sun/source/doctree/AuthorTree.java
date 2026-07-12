package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/AuthorTree.class */
public interface AuthorTree extends BlockTagTree {
    List<? extends DocTree> getName();
}
