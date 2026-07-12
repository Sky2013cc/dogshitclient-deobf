package org.spongepowered.asm.service.mojang;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import net.minecraft.launchwrapper.LaunchClassLoader;

/* loaded from: target.jar:org/spongepowered/asm/service/mojang/LaunchClassLoaderUtil.class */
final class LaunchClassLoaderUtil {
    private static final String CACHED_CLASSES_FIELD = "cachedClasses";
    private static final String INVALID_CLASSES_FIELD = "invalidClasses";
    private static final String CLASS_LOADER_EXCEPTIONS_FIELD = "classLoaderExceptions";
    private static final String TRANSFORMER_EXCEPTIONS_FIELD = "transformerExceptions";
    private final LaunchClassLoader classLoader;
    private final Map<String, Class<?>> cachedClasses;
    private final Set<String> invalidClasses;
    private final Set<String> classLoaderExceptions;
    private final Set<String> transformerExceptions;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LaunchClassLoaderUtil(LaunchClassLoader classLoader) {
        this.classLoader = classLoader;
        this.cachedClasses = (Map) getField(classLoader, CACHED_CLASSES_FIELD);
        this.invalidClasses = (Set) getField(classLoader, INVALID_CLASSES_FIELD);
        this.classLoaderExceptions = (Set) getField(classLoader, CLASS_LOADER_EXCEPTIONS_FIELD);
        this.transformerExceptions = (Set) getField(classLoader, TRANSFORMER_EXCEPTIONS_FIELD);
    }

    LaunchClassLoader getClassLoader() {
        return this.classLoader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isClassLoaded(String name) {
        return this.cachedClasses.containsKey(name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isClassExcluded(String name, String transformedName) {
        return isClassClassLoaderExcluded(name, transformedName) || isClassTransformerExcluded(name, transformedName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isClassClassLoaderExcluded(String name, String transformedName) {
        for (String exception : getClassLoaderExceptions()) {
            if ((transformedName != null && transformedName.startsWith(exception)) || name.startsWith(exception)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isClassTransformerExcluded(String name, String transformedName) {
        for (String exception : getTransformerExceptions()) {
            if ((transformedName != null && transformedName.startsWith(exception)) || name.startsWith(exception)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerInvalidClass(String name) {
        if (this.invalidClasses != null) {
            this.invalidClasses.add(name);
        }
    }

    Set<String> getClassLoaderExceptions() {
        if (this.classLoaderExceptions != null) {
            return this.classLoaderExceptions;
        }
        return Collections.emptySet();
    }

    Set<String> getTransformerExceptions() {
        if (this.transformerExceptions != null) {
            return this.transformerExceptions;
        }
        return Collections.emptySet();
    }

    private static <T> T getField(LaunchClassLoader launchClassLoader, String str) {
        try {
            Field declaredField = LaunchClassLoader.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return (T) declaredField.get(launchClassLoader);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
