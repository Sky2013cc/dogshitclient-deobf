package jdk.nashorn.internal.runtime.arrays;

import java.util.NoSuchElementException;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/arrays/EmptyArrayLikeIterator.class */
final class EmptyArrayLikeIterator extends ArrayLikeIterator<Object> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public EmptyArrayLikeIterator() {
        super(false);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return false;
    }

    @Override // java.util.Iterator
    public Object next() {
        throw new NoSuchElementException();
    }

    @Override // jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator
    public long getLength() {
        return 0L;
    }
}
