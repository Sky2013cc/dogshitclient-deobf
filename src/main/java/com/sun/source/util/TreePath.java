package com.sun.source.util;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.Tree;
import java.util.Iterator;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/util/TreePath.class */
public class TreePath implements Iterable<Tree> {
    private CompilationUnitTree compilationUnit;
    private Tree leaf;
    private TreePath parent;

    public static TreePath getPath(CompilationUnitTree compilationUnitTree, Tree tree) {
        return getPath(new TreePath(compilationUnitTree), tree);
    }

    public static TreePath getPath(TreePath treePath, Tree tree) {
        treePath.getClass();
        tree.getClass();
        if (treePath.getLeaf() == tree) {
            return treePath;
        }
        try {
            new TreePathScanner<TreePath, Tree>() { // from class: com.sun.source.util.TreePath.1PathFinder
                @Override // com.sun.source.util.TreePathScanner, com.sun.source.util.TreeScanner
                public TreePath scan(Tree tree2, Tree tree3) {
                    if (tree2 == tree3) {
                        throw new C1Result(new TreePath(getCurrentPath(), tree3));
                    }
                    return (TreePath) super.scan(tree2, tree3);
                }
            }.scan(treePath, (TreePath) tree);
            return null;
        } catch (C1Result e) {
            return e.path;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sun.source.util.TreePath$1Result, reason: invalid class name */
    /* loaded from: target.jar:com/sun/source/util/TreePath$1Result.class */
    public class C1Result extends Error {
        static final long serialVersionUID = -5942088234594905625L;
        TreePath path;

        C1Result(TreePath treePath) {
            this.path = treePath;
        }
    }

    public TreePath(CompilationUnitTree compilationUnitTree) {
        this(null, compilationUnitTree);
    }

    public TreePath(TreePath treePath, Tree tree) {
        if (tree.getKind() == Tree.Kind.COMPILATION_UNIT) {
            this.compilationUnit = (CompilationUnitTree) tree;
            this.parent = null;
        } else {
            this.compilationUnit = treePath.compilationUnit;
            this.parent = treePath;
        }
        this.leaf = tree;
    }

    public CompilationUnitTree getCompilationUnit() {
        return this.compilationUnit;
    }

    public Tree getLeaf() {
        return this.leaf;
    }

    public TreePath getParentPath() {
        return this.parent;
    }

    @Override // java.lang.Iterable
    public Iterator<Tree> iterator() {
        return new Iterator<Tree>() { // from class: com.sun.source.util.TreePath.1
            private TreePath next;

            {
                this.next = TreePath.this;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next != null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Tree next() {
                Tree tree = this.next.leaf;
                this.next = this.next.parent;
                return tree;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
