package com.sun.tools.internal.xjc.reader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/Ring.class */
public final class Ring {
    private final Map<Class, Object> components = new HashMap();
    private static final ThreadLocal<Ring> instances;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !Ring.class.desiredAssertionStatus();
        instances = new ThreadLocal<>();
    }

    private Ring() {
    }

    public static <T> void add(Class<T> clazz, T instance) {
        if (!$assertionsDisabled && get().components.containsKey(clazz)) {
            throw new AssertionError();
        }
        get().components.put(clazz, instance);
    }

    public static <T> void add(T o) {
        add(o.getClass(), o);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T get(Class<T> key) {
        T t = get().components.get(key);
        if (t == null) {
            try {
                Constructor<T> c = key.getDeclaredConstructor(new Class[0]);
                c.setAccessible(true);
                t = c.newInstance(new Object[0]);
                if (!get().components.containsKey(key)) {
                    add(key, t);
                }
            } catch (IllegalAccessException e) {
                throw new Error(e);
            } catch (InstantiationException e2) {
                throw new Error(e2);
            } catch (NoSuchMethodException e3) {
                throw new Error(e3);
            } catch (InvocationTargetException e4) {
                throw new Error(e4);
            }
        }
        if ($assertionsDisabled || t != null) {
            return t;
        }
        throw new AssertionError();
    }

    public static Ring get() {
        return instances.get();
    }

    public static Ring begin() {
        Ring r;
        synchronized (instances) {
            r = instances.get();
            instances.set(new Ring());
        }
        return r;
    }

    public static void end(Ring old) {
        synchronized (instances) {
            instances.remove();
            instances.set(old);
        }
    }
}
