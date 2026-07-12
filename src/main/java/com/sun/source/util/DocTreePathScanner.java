package com.sun.source.util;

import com.sun.source.doctree.DocTree;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/util/DocTreePathScanner.class */
public class DocTreePathScanner<R, P> extends DocTreeScanner<R, P> {
    private DocTreePath path;

    public R scan(DocTreePath docTreePath, P p) {
        this.path = docTreePath;
        try {
            R r = (R) docTreePath.getLeaf().accept(this, p);
            this.path = null;
            return r;
        } catch (Throwable th) {
            this.path = null;
            throw th;
        }
    }

    @Override // com.sun.source.util.DocTreeScanner
    public R scan(DocTree docTree, P p) {
        if (docTree == null) {
            return null;
        }
        DocTreePath docTreePath = this.path;
        this.path = new DocTreePath(this.path, docTree);
        try {
            R r = (R) docTree.accept(this, p);
            this.path = docTreePath;
            return r;
        } catch (Throwable th) {
            this.path = docTreePath;
            throw th;
        }
    }

    public DocTreePath getCurrentPath() {
        return this.path;
    }
}
