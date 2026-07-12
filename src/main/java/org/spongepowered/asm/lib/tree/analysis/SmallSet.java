package org.spongepowered.asm.lib.tree.analysis;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/* loaded from: target.jar:org/spongepowered/asm/lib/tree/analysis/SmallSet.class */
class SmallSet<E> extends AbstractSet<E> implements Iterator<E> {
    E e1;
    E e2;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final <T> Set<T> emptySet() {
        return new SmallSet(null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmallSet(E e1, E e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return new SmallSet(this.e1, this.e2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        if (this.e1 == null) {
            return 0;
        }
        return this.e2 == null ? 1 : 2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.e1 != null;
    }

    @Override // java.util.Iterator
    public E next() {
        if (this.e1 == null) {
            throw new NoSuchElementException();
        }
        E e = this.e1;
        this.e1 = this.e2;
        this.e2 = null;
        return e;
    }

    @Override // java.util.Iterator
    public void remove() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<E> union(SmallSet<E> s) {
        if ((s.e1 == this.e1 && s.e2 == this.e2) || (s.e1 == this.e2 && s.e2 == this.e1)) {
            return this;
        }
        if (s.e1 == null) {
            return this;
        }
        if (this.e1 == null) {
            return s;
        }
        if (s.e2 == null) {
            if (this.e2 == null) {
                return new SmallSet(this.e1, s.e1);
            }
            if (s.e1 == this.e1 || s.e1 == this.e2) {
                return this;
            }
        }
        if (this.e2 == null && (this.e1 == s.e1 || this.e1 == s.e2)) {
            return s;
        }
        HashSet<E> r = new HashSet<>(4);
        r.add(this.e1);
        if (this.e2 != null) {
            r.add(this.e2);
        }
        r.add(s.e1);
        if (s.e2 != null) {
            r.add(s.e2);
        }
        return r;
    }
}
