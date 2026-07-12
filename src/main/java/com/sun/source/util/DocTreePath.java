package com.sun.source.util;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;
import java.util.Iterator;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/util/DocTreePath.class */
public class DocTreePath implements Iterable<DocTree> {
    private final TreePath treePath;
    private final DocCommentTree docComment;
    private final DocTree leaf;
    private final DocTreePath parent;

    public static DocTreePath getPath(TreePath treePath, DocCommentTree docCommentTree, DocTree docTree) {
        return getPath(new DocTreePath(treePath, docCommentTree), docTree);
    }

    public static DocTreePath getPath(DocTreePath docTreePath, DocTree docTree) {
        docTreePath.getClass();
        docTree.getClass();
        if (docTreePath.getLeaf() == docTree) {
            return docTreePath;
        }
        try {
            new DocTreePathScanner<DocTreePath, DocTree>() { // from class: com.sun.source.util.DocTreePath.1PathFinder
                @Override // com.sun.source.util.DocTreePathScanner, com.sun.source.util.DocTreeScanner
                public DocTreePath scan(DocTree docTree2, DocTree docTree3) {
                    if (docTree2 == docTree3) {
                        throw new C1Result(new DocTreePath(getCurrentPath(), docTree3));
                    }
                    return (DocTreePath) super.scan(docTree2, docTree3);
                }
            }.scan(docTreePath, (DocTreePath) docTree);
            return null;
        } catch (C1Result e) {
            return e.path;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sun.source.util.DocTreePath$1Result, reason: invalid class name */
    /* loaded from: target.jar:com/sun/source/util/DocTreePath$1Result.class */
    public class C1Result extends Error {
        static final long serialVersionUID = -5942088234594905625L;
        DocTreePath path;

        C1Result(DocTreePath docTreePath) {
            this.path = docTreePath;
        }
    }

    public DocTreePath(TreePath treePath, DocCommentTree docCommentTree) {
        treePath.getClass();
        docCommentTree.getClass();
        this.treePath = treePath;
        this.docComment = docCommentTree;
        this.parent = null;
        this.leaf = docCommentTree;
    }

    public DocTreePath(DocTreePath docTreePath, DocTree docTree) {
        if (docTree.getKind() == DocTree.Kind.DOC_COMMENT) {
            throw new IllegalArgumentException("Use DocTreePath(TreePath, DocCommentTree) to construct DocTreePath for a DocCommentTree.");
        }
        this.treePath = docTreePath.treePath;
        this.docComment = docTreePath.docComment;
        this.parent = docTreePath;
        this.leaf = docTree;
    }

    public TreePath getTreePath() {
        return this.treePath;
    }

    public DocCommentTree getDocComment() {
        return this.docComment;
    }

    public DocTree getLeaf() {
        return this.leaf;
    }

    public DocTreePath getParentPath() {
        return this.parent;
    }

    @Override // java.lang.Iterable
    public Iterator<DocTree> iterator() {
        return new Iterator<DocTree>() { // from class: com.sun.source.util.DocTreePath.1
            private DocTreePath next;

            {
                this.next = DocTreePath.this;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next != null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public DocTree next() {
                DocTree docTree = this.next.leaf;
                this.next = this.next.parent;
                return docTree;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
