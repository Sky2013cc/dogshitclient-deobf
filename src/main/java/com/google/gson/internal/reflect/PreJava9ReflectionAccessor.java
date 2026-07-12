package com.google.gson.internal.reflect;

import java.lang.reflect.AccessibleObject;

/* loaded from: target.jar:com/google/gson/internal/reflect/PreJava9ReflectionAccessor.class */
final class PreJava9ReflectionAccessor extends ReflectionAccessor {
    @Override // com.google.gson.internal.reflect.ReflectionAccessor
    public void makeAccessible(AccessibleObject ao) {
        ao.setAccessible(true);
    }
}
