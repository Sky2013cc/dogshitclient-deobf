package de.rototor.pdfbox.graphics2d;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: target.jar:de/rototor/pdfbox/graphics2d/PrivateFieldAccessor.class */
class PrivateFieldAccessor {
    private static IAccessableSetter performSetAccessible;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:de/rototor/pdfbox/graphics2d/PrivateFieldAccessor$IAccessableSetter.class */
    public interface IAccessableSetter {
        void setAccessible(AccessibleObject accessibleObject);
    }

    PrivateFieldAccessor() {
    }

    static {
        performSetAccessible = new IAccessableSetter() { // from class: de.rototor.pdfbox.graphics2d.PrivateFieldAccessor.1
            @Override // de.rototor.pdfbox.graphics2d.PrivateFieldAccessor.IAccessableSetter
            public void setAccessible(AccessibleObject obj) {
                obj.setAccessible(true);
            }
        };
        try {
            final Method setAccessible = AccessibleObject.class.getDeclaredMethod("setAccessible0", Boolean.TYPE);
            Field methodModifiers = Method.class.getDeclaredField("modifiers");
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Constructor<?> unsafeConstructor = unsafeClass.getDeclaredConstructor(new Class[0]);
            unsafeConstructor.setAccessible(true);
            Object unsafe = unsafeConstructor.newInstance(new Object[0]);
            Method objectFieldOffsetMethod = unsafeClass.getMethod("objectFieldOffset", Field.class);
            long methodModifiersOffset = ((Long) objectFieldOffsetMethod.invoke(unsafe, methodModifiers)).longValue();
            Method getAndSetInt = unsafeClass.getMethod("getAndSetInt", Object.class, Long.TYPE, Integer.TYPE);
            getAndSetInt.invoke(unsafe, setAccessible, Long.valueOf(methodModifiersOffset), 1);
            performSetAccessible = new IAccessableSetter() { // from class: de.rototor.pdfbox.graphics2d.PrivateFieldAccessor.2
                @Override // de.rototor.pdfbox.graphics2d.PrivateFieldAccessor.IAccessableSetter
                public void setAccessible(AccessibleObject obj) {
                    try {
                        setAccessible.invoke(obj, true);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            };
        } catch (Exception e) {
        }
    }

    private static void setAccessible(AccessibleObject object) {
        performSetAccessible.setAccessible(object);
    }

    public static <T> T getPrivateField(Object obj, String str) {
        Class<?> cls = obj.getClass();
        while (true) {
            Class<?> cls2 = cls;
            if (cls2 != null) {
                for (Field field : cls2.getDeclaredFields()) {
                    if (field.getName().equals(str)) {
                        setAccessible(field);
                        try {
                            return (T) field.get(obj);
                        } catch (RuntimeException e) {
                            throw e;
                        } catch (Exception e2) {
                            throw new RuntimeException("Error while accessing field " + str + " of " + cls2, e2);
                        }
                    }
                }
                cls = cls2.getSuperclass();
            } else {
                throw new NoSuchFieldError(str);
            }
        }
    }
}
