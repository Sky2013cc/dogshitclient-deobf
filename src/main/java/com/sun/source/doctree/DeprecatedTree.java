package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/DeprecatedTree.class */
public interface DeprecatedTree extends BlockTagTree {
    List<? extends DocTree> getBody();
}
