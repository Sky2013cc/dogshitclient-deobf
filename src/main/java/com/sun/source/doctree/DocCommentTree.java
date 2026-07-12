package com.sun.source.doctree;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/DocCommentTree.class */
public interface DocCommentTree extends DocTree {
    List<? extends DocTree> getFirstSentence();

    List<? extends DocTree> getBody();

    List<? extends DocTree> getBlockTags();
}
