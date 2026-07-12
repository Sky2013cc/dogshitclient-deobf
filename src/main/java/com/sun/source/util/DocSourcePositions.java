package com.sun.source.util;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.tree.CompilationUnitTree;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/util/DocSourcePositions.class */
public interface DocSourcePositions extends SourcePositions {
    long getStartPosition(CompilationUnitTree compilationUnitTree, DocCommentTree docCommentTree, DocTree docTree);

    long getEndPosition(CompilationUnitTree compilationUnitTree, DocCommentTree docCommentTree, DocTree docTree);
}
