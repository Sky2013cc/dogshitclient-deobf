package com.sun.source.util;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.Tree;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/util/SourcePositions.class */
public interface SourcePositions {
    long getStartPosition(CompilationUnitTree compilationUnitTree, Tree tree);

    long getEndPosition(CompilationUnitTree compilationUnitTree, Tree tree);
}
