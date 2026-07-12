package com.sun.tools.javac.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: target.jar:com/sun/tools/javac/util/Context.class */
public class Context {
    private Map<Key<?>, Object> ht = new HashMap();
    private Map<Key<?>, Factory<?>> ft = new HashMap();
    private Map<Class<?>, Key<?>> kt = new HashMap();

    /* loaded from: target.jar:com/sun/tools/javac/util/Context$Factory.class */
    public interface Factory<T> {
        T make(Context context);
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/Context$Key.class */
    public static class Key<T> {
    }

    public <T> void put(Key<T> key, Factory<T> factory) {
        checkState(this.ht);
        if (this.ht.put(key, factory) != null) {
            throw new AssertionError("duplicate context value");
        }
        checkState(this.ft);
        this.ft.put(key, factory);
    }

    public <T> void put(Key<T> key, T t) {
        if (t instanceof Factory) {
            throw new AssertionError("T extends Context.Factory");
        }
        checkState(this.ht);
        Object put = this.ht.put(key, t);
        if (put != null && !(put instanceof Factory) && put != t && t != null) {
            throw new AssertionError("duplicate context value");
        }
    }

    public <T> T get(Key<T> key) {
        checkState(this.ht);
        Object obj = this.ht.get(key);
        if (obj instanceof Factory) {
            obj = ((Factory) obj).make(this);
            if (obj instanceof Factory) {
                throw new AssertionError("T extends Context.Factory");
            }
            Assert.check(this.ht.get(key) == obj);
        }
        return (T) uncheckedCast(obj);
    }

    public Context() {
    }

    public Context(Context context) {
        this.kt.putAll(context.kt);
        this.ft.putAll(context.ft);
        this.ht.putAll(context.ft);
    }

    private <T> Key<T> key(Class<T> cls) {
        checkState(this.kt);
        Key<T> key = (Key) uncheckedCast(this.kt.get(cls));
        if (key == null) {
            key = new Key<>();
            this.kt.put(cls, key);
        }
        return key;
    }

    public <T> T get(Class<T> cls) {
        return (T) get(key(cls));
    }

    public <T> void put(Class<T> cls, T t) {
        put((Key<Key<T>>) key(cls), (Key<T>) t);
    }

    public <T> void put(Class<T> cls, Factory<T> factory) {
        put((Key) key(cls), (Factory) factory);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T uncheckedCast(Object obj) {
        return obj;
    }

    public void dump() {
        Iterator<Object> it = this.ht.values().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            System.err.println(next == null ? null : next.getClass());
        }
    }

    public void clear() {
        this.ht = null;
        this.kt = null;
        this.ft = null;
    }

    private static void checkState(Map<?, ?> map) {
        if (map == null) {
            throw new IllegalStateException();
        }
    }
}
