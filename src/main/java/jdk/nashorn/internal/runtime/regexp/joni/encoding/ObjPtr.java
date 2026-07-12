package jdk.nashorn.internal.runtime.regexp.joni.encoding;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/regexp/joni/encoding/ObjPtr.class */
public final class ObjPtr<T> {
    public T p;

    public ObjPtr() {
        this(null);
    }

    public ObjPtr(T p) {
        this.p = p;
    }
}
