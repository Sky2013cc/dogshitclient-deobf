package com.sun.tools.javac.util;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: target.jar:com/sun/tools/javac/util/ListBuffer.class */
public class ListBuffer<A> extends AbstractQueue<A> {
    private List<A> elems;
    private List<A> last;
    private int count;
    private boolean shared;

    public static <T> ListBuffer<T> of(T t) {
        ListBuffer<T> listBuffer = new ListBuffer<>();
        listBuffer.add(t);
        return listBuffer;
    }

    public ListBuffer() {
        clear();
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.elems = List.nil();
        this.last = null;
        this.count = 0;
        this.shared = false;
    }

    public int length() {
        return this.count;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.count;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean nonEmpty() {
        return this.count != 0;
    }

    private void copy() {
        if (this.elems.nonEmpty()) {
            List<A> list = this.elems;
            List<A> of = List.of((Object) list.head);
            this.last = of;
            this.elems = of;
            while (true) {
                List<A> list2 = list.tail;
                list = list2;
                if (list2.nonEmpty()) {
                    this.last.tail = List.of((Object) list.head);
                    this.last = this.last.tail;
                } else {
                    return;
                }
            }
        }
    }

    public ListBuffer<A> prepend(A a) {
        this.elems = this.elems.prepend(a);
        if (this.last == null) {
            this.last = this.elems;
        }
        this.count++;
        return this;
    }

    public ListBuffer<A> append(A a) {
        a.getClass();
        if (this.shared) {
            copy();
        }
        List<A> of = List.of((Object) a);
        if (this.last != null) {
            this.last.tail = of;
            this.last = of;
        } else {
            this.last = of;
            this.elems = of;
        }
        this.count++;
        return this;
    }

    public ListBuffer<A> appendList(List<A> list) {
        while (list.nonEmpty()) {
            append(list.head);
            list = list.tail;
        }
        return this;
    }

    public ListBuffer<A> appendList(ListBuffer<A> listBuffer) {
        return appendList(listBuffer.toList());
    }

    public ListBuffer<A> appendArray(A[] aArr) {
        for (A a : aArr) {
            append(a);
        }
        return this;
    }

    public List<A> toList() {
        this.shared = true;
        return this.elems;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        return this.elems.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.elems.toArray(tArr);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    public A first() {
        return this.elems.head;
    }

    public A next() {
        A a = this.elems.head;
        if (!this.elems.isEmpty()) {
            this.elems = this.elems.tail;
            if (this.elems.isEmpty()) {
                this.last = null;
            }
            this.count--;
        }
        return a;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<A> iterator() {
        return new Iterator<A>() { // from class: com.sun.tools.javac.util.ListBuffer.1
            List<A> elems;

            {
                this.elems = ListBuffer.this.elems;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return !this.elems.isEmpty();
            }

            @Override // java.util.Iterator
            public A next() {
                if (this.elems.isEmpty()) {
                    throw new NoSuchElementException();
                }
                A a = this.elems.head;
                this.elems = this.elems.tail;
                return a;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue
    public boolean add(A a) {
        append(a);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection<? extends A> collection) {
        Iterator<? extends A> it = collection.iterator();
        while (it.hasNext()) {
            append(it.next());
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Queue
    public boolean offer(A a) {
        append(a);
        return true;
    }

    @Override // java.util.Queue
    public A poll() {
        return next();
    }

    @Override // java.util.Queue
    public A peek() {
        return first();
    }

    public A last() {
        if (this.last != null) {
            return this.last.head;
        }
        return null;
    }
}
