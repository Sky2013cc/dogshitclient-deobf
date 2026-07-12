package com.sun.tools.javac.util;

import com.sun.tools.doclint.DocLint;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* loaded from: target.jar:com/sun/tools/javac/util/List.class */
public class List<A> extends AbstractCollection<A> implements java.util.List<A> {
    public A head;
    public List<A> tail;
    private static final List<?> EMPTY_LIST = new List<Object>(null, null) { // from class: com.sun.tools.javac.util.List.1
        @Override // com.sun.tools.javac.util.List
        public List<Object> setTail(List<Object> list) {
            throw new UnsupportedOperationException();
        }

        @Override // com.sun.tools.javac.util.List, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return true;
        }
    };
    private static final Iterator<?> EMPTYITERATOR = new Iterator<Object>() { // from class: com.sun.tools.javac.util.List.2
        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    };

    List(A a, List<A> list) {
        this.tail = list;
        this.head = a;
    }

    public static <A> List<A> nil() {
        return (List<A>) EMPTY_LIST;
    }

    public static <A> List<A> filter(List<A> list, A a) {
        Assert.checkNonNull(a);
        List nil = nil();
        Iterator<A> it = list.iterator();
        while (it.hasNext()) {
            A next = it.next();
            if (next != null && !next.equals(a)) {
                nil = nil.prepend(next);
            }
        }
        return nil.reverse();
    }

    public List<A> intersect(List<A> list) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<A> it = iterator();
        while (it.hasNext()) {
            A next = it.next();
            if (list.contains(next)) {
                listBuffer.append(next);
            }
        }
        return listBuffer.toList();
    }

    public List<A> diff(List<A> list) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<A> it = iterator();
        while (it.hasNext()) {
            A next = it.next();
            if (!list.contains(next)) {
                listBuffer.append(next);
            }
        }
        return listBuffer.toList();
    }

    public List<A> take(int i) {
        ListBuffer listBuffer = new ListBuffer();
        int i2 = 0;
        Iterator<A> it = iterator();
        while (it.hasNext()) {
            A next = it.next();
            int i3 = i2;
            i2++;
            if (i3 == i) {
                break;
            }
            listBuffer.append(next);
        }
        return listBuffer.toList();
    }

    public static <A> List<A> of(A a) {
        return new List<>(a, nil());
    }

    public static <A> List<A> of(A a, A a2) {
        return new List<>(a, of((Object) a2));
    }

    public static <A> List<A> of(A a, A a2, A a3) {
        return new List<>(a, of((Object) a2, (Object) a3));
    }

    public static <A> List<A> of(A a, A a2, A a3, A... aArr) {
        return new List<>(a, new List(a2, new List(a3, from(aArr))));
    }

    public static <A> List<A> from(A[] aArr) {
        List<A> nil = nil();
        if (aArr != null) {
            for (int length = aArr.length - 1; length >= 0; length--) {
                nil = new List<>(aArr[length], nil);
            }
        }
        return nil;
    }

    public static <A> List<A> from(Iterable<? extends A> iterable) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<? extends A> it = iterable.iterator();
        while (it.hasNext()) {
            listBuffer.append(it.next());
        }
        return listBuffer.toList();
    }

    @Deprecated
    public static <A> List<A> fill(int i, A a) {
        List<A> nil = nil();
        for (int i2 = 0; i2 < i; i2++) {
            nil = new List<>(a, nil);
        }
        return nil;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return this.tail == null;
    }

    public boolean nonEmpty() {
        return this.tail != null;
    }

    public int length() {
        List<A> list = this;
        int i = 0;
        while (list.tail != null) {
            list = list.tail;
            i++;
        }
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return length();
    }

    public List<A> setTail(List<A> list) {
        this.tail = list;
        return list;
    }

    public List<A> prepend(A a) {
        return new List<>(a, this);
    }

    public List<A> prependList(List<A> list) {
        if (isEmpty()) {
            return list;
        }
        if (list.isEmpty()) {
            return this;
        }
        if (list.tail.isEmpty()) {
            return prepend(list.head);
        }
        List<A> list2 = this;
        List<A> reverse = list.reverse();
        Assert.check(reverse != list);
        while (reverse.nonEmpty()) {
            List<A> list3 = reverse;
            reverse = reverse.tail;
            list3.setTail(list2);
            list2 = list3;
        }
        return list2;
    }

    public List<A> reverse() {
        if (isEmpty() || this.tail.isEmpty()) {
            return this;
        }
        List<A> nil = nil();
        List<A> list = this;
        while (true) {
            List<A> list2 = list;
            if (list2.nonEmpty()) {
                nil = new List<>(list2.head, nil);
                list = list2.tail;
            } else {
                return nil;
            }
        }
    }

    public List<A> append(A a) {
        return of((Object) a).prependList(this);
    }

    public List<A> appendList(List<A> list) {
        return list.prependList(this);
    }

    public List<A> appendList(ListBuffer<A> listBuffer) {
        return appendList(listBuffer.toList());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] tArr) {
        int i = 0;
        List<A> list = this;
        while (list.nonEmpty() && i < tArr.length) {
            tArr[i] = list.head;
            list = list.tail;
            i++;
        }
        if (list.isEmpty()) {
            if (i < tArr.length) {
                tArr[i] = 0;
            }
            return tArr;
        }
        return (T[]) toArray((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size()));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    public String toString(String str) {
        if (isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.head);
        List<A> list = this.tail;
        while (true) {
            List<A> list2 = list;
            if (list2.nonEmpty()) {
                sb.append(str);
                sb.append(list2.head);
                list = list2.tail;
            } else {
                return sb.toString();
            }
        }
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return toString(DocLint.TAGS_SEPARATOR);
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        int i = 1;
        for (List<A> list = this; list.tail != null; list = list.tail) {
            i = (i * 31) + (list.head == null ? 0 : list.head.hashCode());
        }
        return i;
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj instanceof List) {
            return equals(this, (List) obj);
        }
        if (obj instanceof java.util.List) {
            List<A> list = this;
            Iterator it = ((java.util.List) obj).iterator();
            while (list.tail != null && it.hasNext()) {
                Object next = it.next();
                if (list.head == null) {
                    if (next != null) {
                        return false;
                    }
                } else if (!list.head.equals(next)) {
                    return false;
                }
                list = list.tail;
            }
            return list.isEmpty() && !it.hasNext();
        }
        return false;
    }

    public static boolean equals(List<?> list, List<?> list2) {
        while (list.tail != null && list2.tail != null) {
            if (list.head == null) {
                if (list2.head != null) {
                    return false;
                }
            } else if (!list.head.equals(list2.head)) {
                return false;
            }
            list = list.tail;
            list2 = list2.tail;
        }
        return list.tail == null && list2.tail == null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        List<A> list = this;
        while (true) {
            List<A> list2 = list;
            if (list2.tail != null) {
                if (obj == null) {
                    if (list2.head == null) {
                        return true;
                    }
                } else if (list2.head.equals(obj)) {
                    return true;
                }
                list = list2.tail;
            } else {
                return false;
            }
        }
    }

    public A last() {
        A a = null;
        List<A> list = this;
        while (true) {
            List<A> list2 = list;
            if (list2.tail != null) {
                a = list2.head;
                list = list2.tail;
            } else {
                return a;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> List<T> convert(Class<T> cls, List<?> list) {
        if (list == 0) {
            return null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            cls.cast(it.next());
        }
        return list;
    }

    private static <A> Iterator<A> emptyIterator() {
        return (Iterator<A>) EMPTYITERATOR;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<A> iterator() {
        if (this.tail == null) {
            return emptyIterator();
        }
        return new Iterator<A>() { // from class: com.sun.tools.javac.util.List.3
            List<A> elems;

            {
                this.elems = List.this;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.elems.tail != null;
            }

            @Override // java.util.Iterator
            public A next() {
                if (this.elems.tail == null) {
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

    @Override // java.util.List
    public A get(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        List<A> list = this;
        int i2 = i;
        while (true) {
            int i3 = i2;
            i2--;
            if (i3 <= 0 || list.isEmpty()) {
                break;
            }
            list = list.tail;
        }
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size());
        }
        return list.head;
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends A> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public A set(int i, A a) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public void add(int i, A a) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public A remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        int i = 0;
        List<A> list = this;
        while (list.tail != null) {
            if (list.head == null) {
                if (obj != null) {
                    list = list.tail;
                    i++;
                } else {
                    return i;
                }
            } else if (!list.head.equals(obj)) {
                list = list.tail;
                i++;
            } else {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        int i = -1;
        int i2 = 0;
        List<A> list = this;
        while (list.tail != null) {
            if (list.head == null) {
                if (obj != null) {
                    list = list.tail;
                    i2++;
                }
                i = i2;
                list = list.tail;
                i2++;
            } else {
                if (!list.head.equals(obj)) {
                    list = list.tail;
                    i2++;
                }
                i = i2;
                list = list.tail;
                i2++;
            }
        }
        return i;
    }

    @Override // java.util.List
    public ListIterator<A> listIterator() {
        return Collections.unmodifiableList(new ArrayList(this)).listIterator();
    }

    @Override // java.util.List
    public ListIterator<A> listIterator(int i) {
        return Collections.unmodifiableList(new ArrayList(this)).listIterator(i);
    }

    @Override // java.util.List
    public java.util.List<A> subList(int i, int i2) {
        if (i < 0 || i2 > size() || i > i2) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i2 - i);
        List<A> list = this;
        for (int i3 = 0; list.tail != null && i3 != i2; i3++) {
            if (i3 >= i) {
                arrayList.add(list.head);
            }
            list = list.tail;
        }
        return Collections.unmodifiableList(arrayList);
    }
}
