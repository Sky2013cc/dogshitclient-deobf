package com.sun.source.util;

import com.sun.source.tree.Tree;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/util/TreePathScanner.class */
public class TreePathScanner<R, P> extends TreeScanner<R, P> {
    private TreePath path;

    public R scan(TreePath treePath, P p) {
        this.path = treePath;
        try {
            R r = (R) treePath.getLeaf().accept(this, p);
            this.path = null;
            return r;
        } catch (Throwable th) {
            this.path = null;
            throw th;
        }
    }

    @Override // com.sun.source.util.TreeScanner
    public R scan(Tree tree, P p) {
        if (tree == null) {
            return null;
        }
        TreePath treePath = this.path;
        this.path = new TreePath(this.path, tree);
        try {
            R r = (R) tree.accept(this, p);
            this.path = treePath;
            return r;
        } catch (Throwable th) {
            this.path = treePath;
            throw th;
        }
    }

    public TreePath getCurrentPath() {
        return this.path;
    }
}
