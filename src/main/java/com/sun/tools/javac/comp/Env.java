package com.sun.tools.javac.comp;

import com.sun.tools.javac.tree.JCTree;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: target.jar:com/sun/tools/javac/comp/Env.class */
public class Env<A> implements Iterable<Env<A>> {
    public JCTree tree;
    public A info;
    public boolean baseClause = false;
    public Env<A> next = null;
    public Env<A> outer = null;
    public JCTree.JCCompilationUnit toplevel = null;
    public JCTree.JCClassDecl enclClass = null;
    public JCTree.JCMethodDecl enclMethod = null;

    public Env(JCTree jCTree, A a) {
        this.tree = jCTree;
        this.info = a;
    }

    public Env<A> dup(JCTree jCTree, A a) {
        return dupto(new Env<>(jCTree, a));
    }

    public Env<A> dupto(Env<A> env) {
        env.next = this;
        env.outer = this.outer;
        env.toplevel = this.toplevel;
        env.enclClass = this.enclClass;
        env.enclMethod = this.enclMethod;
        return env;
    }

    public Env<A> dup(JCTree jCTree) {
        return dup(jCTree, this.info);
    }

    public Env<A> enclosing(JCTree.Tag tag) {
        Env<A> env;
        Env<A> env2 = this;
        while (true) {
            env = env2;
            if (env == null || env.tree.hasTag(tag)) {
                break;
            }
            env2 = env.next;
        }
        return env;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Env[").append(this.info);
        if (this.outer != null) {
            sb.append(",outer=").append(this.outer);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.lang.Iterable
    public Iterator<Env<A>> iterator() {
        return new Iterator<Env<A>>() { // from class: com.sun.tools.javac.comp.Env.1
            Env<A> next;

            {
                this.next = Env.this;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next.outer != null;
            }

            @Override // java.util.Iterator
            public Env<A> next() {
                if (hasNext()) {
                    Env<A> env = this.next;
                    this.next = env.outer;
                    return env;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
