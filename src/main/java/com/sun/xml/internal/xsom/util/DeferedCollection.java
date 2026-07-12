package com.sun.xml.internal.xsom.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: target.jar:com/sun/xml/internal/xsom/util/DeferedCollection.class */
public class DeferedCollection<T> implements Collection<T> {
    private final Iterator<T> result;
    private final List<T> archive = new ArrayList();

    public DeferedCollection(Iterator<T> result) {
        this.result = result;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        if (this.archive.isEmpty()) {
            fetch();
        }
        return this.archive.isEmpty();
    }

    @Override // java.util.Collection
    public int size() {
        fetchAll();
        return this.archive.size();
    }

    @Override // java.util.Collection
    public boolean contains(Object o) {
        if (this.archive.contains(o)) {
            return true;
        }
        while (this.result.hasNext()) {
            T value = this.result.next();
            this.archive.add(value);
            if (value.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return new Iterator<T>() { // from class: com.sun.xml.internal.xsom.util.DeferedCollection.1
            int idx = 0;

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.idx >= DeferedCollection.this.archive.size()) {
                    return DeferedCollection.this.result.hasNext();
                }
                return true;
            }

            @Override // java.util.Iterator
            public T next() {
                if (this.idx == DeferedCollection.this.archive.size()) {
                    DeferedCollection.this.fetch();
                }
                if (this.idx != DeferedCollection.this.archive.size()) {
                    List list = DeferedCollection.this.archive;
                    int i = this.idx;
                    this.idx = i + 1;
                    return (T) list.get(i);
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
            }
        };
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        fetchAll();
        return this.archive.toArray();
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        fetchAll();
        return (T[]) this.archive.toArray(tArr);
    }

    private void fetchAll() {
        while (this.result.hasNext()) {
            this.archive.add(this.result.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetch() {
        if (this.result.hasNext()) {
            this.archive.add(this.result.next());
        }
    }

    @Override // java.util.Collection
    public boolean add(T o) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }
}
