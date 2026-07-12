package com.sun.istack.internal.tools;

import java.util.Collection;

/* loaded from: target.jar:com/sun/istack/internal/tools/MaskingClassLoader.class */
public class MaskingClassLoader extends ClassLoader {
    private final String[] masks;

    public MaskingClassLoader(String... masks) {
        this.masks = masks;
    }

    public MaskingClassLoader(Collection<String> masks) {
        this((String[]) masks.toArray(new String[masks.size()]));
    }

    public MaskingClassLoader(ClassLoader parent, String... masks) {
        super(parent);
        this.masks = masks;
    }

    public MaskingClassLoader(ClassLoader parent, Collection<String> masks) {
        this(parent, (String[]) masks.toArray(new String[masks.size()]));
    }

    @Override // java.lang.ClassLoader
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        for (String mask : this.masks) {
            if (name.startsWith(mask)) {
                throw new ClassNotFoundException();
            }
        }
        return super.loadClass(name, resolve);
    }
}
