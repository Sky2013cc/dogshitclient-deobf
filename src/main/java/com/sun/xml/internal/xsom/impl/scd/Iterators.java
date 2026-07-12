package com.sun.xml.internal.xsom.impl.scd;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/scd/Iterators.class */
public class Iterators {
    private static final Iterator EMPTY = Collections.EMPTY_LIST.iterator();

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/scd/Iterators$ReadOnly.class */
    static abstract class ReadOnly<T> implements Iterator<T> {
        ReadOnly() {
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static <T> Iterator<T> empty() {
        return EMPTY;
    }

    public static <T> Iterator<T> singleton(T value) {
        return new Singleton(value);
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/scd/Iterators$Singleton.class */
    static final class Singleton<T> extends ReadOnly<T> {
        private T next;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Singleton(T next) {
            this.next = next;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != null;
        }

        @Override // java.util.Iterator
        public T next() {
            T r = this.next;
            this.next = null;
            return r;
        }
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/scd/Iterators$Adapter.class */
    public static abstract class Adapter<T, U> extends ReadOnly<T> {
        private final Iterator<? extends U> core;

        protected abstract T filter(U u);

        public Adapter(Iterator<? extends U> core) {
            this.core = core;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.core.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            return filter(this.core.next());
        }
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/scd/Iterators$Map.class */
    public static abstract class Map<T, U> extends ReadOnly<T> {
        private final Iterator<? extends U> core;
        private Iterator<? extends T> current;

        protected abstract Iterator<? extends T> apply(U u);

        /* JADX INFO: Access modifiers changed from: protected */
        public Map(Iterator<? extends U> core) {
            this.core = core;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (true) {
                if (this.current == null || !this.current.hasNext()) {
                    if (!this.core.hasNext()) {
                        return false;
                    }
                    this.current = apply(this.core.next());
                } else {
                    return true;
                }
            }
        }

        @Override // java.util.Iterator
        public T next() {
            return this.current.next();
        }
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/scd/Iterators$Filter.class */
    public static abstract class Filter<T> extends ReadOnly<T> {
        private final Iterator<? extends T> core;
        private T next;

        protected abstract boolean matches(T t);

        /* JADX INFO: Access modifiers changed from: protected */
        public Filter(Iterator<? extends T> core) {
            this.core = core;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (this.core.hasNext() && this.next == null) {
                this.next = this.core.next();
                if (!matches(this.next)) {
                    this.next = null;
                }
            }
            return this.next != null;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.next == null) {
                throw new NoSuchElementException();
            }
            T r = this.next;
            this.next = null;
            return r;
        }
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/scd/Iterators$Unique.class */
    static final class Unique<T> extends Filter<T> {
        private Set<T> values;

        public Unique(Iterator<? extends T> core) {
            super(core);
            this.values = new HashSet();
        }

        @Override // com.sun.xml.internal.xsom.impl.scd.Iterators.Filter
        protected boolean matches(T value) {
            return this.values.add(value);
        }
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/scd/Iterators$Union.class */
    public static final class Union<T> extends ReadOnly<T> {
        private final Iterator<? extends T> first;
        private final Iterator<? extends T> second;

        public Union(Iterator<? extends T> first, Iterator<? extends T> second) {
            this.first = first;
            this.second = second;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.first.hasNext() || this.second.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            return this.first.hasNext() ? this.first.next() : this.second.next();
        }
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/scd/Iterators$Array.class */
    public static final class Array<T> extends ReadOnly<T> {
        private final T[] items;
        private int index = 0;

        public Array(T[] items) {
            this.items = items;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.items.length;
        }

        @Override // java.util.Iterator
        public T next() {
            T[] tArr = this.items;
            int i = this.index;
            this.index = i + 1;
            return tArr[i];
        }
    }
}
