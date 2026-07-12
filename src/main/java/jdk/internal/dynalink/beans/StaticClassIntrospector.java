package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;

/* loaded from: target.jar:jdk/internal/dynalink/beans/StaticClassIntrospector.class */
class StaticClassIntrospector extends FacetIntrospector {
    /* JADX INFO: Access modifiers changed from: package-private */
    public StaticClassIntrospector(Class<?> clazz) {
        super(clazz, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jdk.internal.dynalink.beans.FacetIntrospector
    public Map<String, MethodHandle> getInnerClassGetters() {
        Map<String, MethodHandle> map = new HashMap<>();
        for (Class<?> innerClass : this.membersLookup.getInnerClasses()) {
            map.put(innerClass.getSimpleName(), editMethodHandle(MethodHandles.constant(StaticClass.class, StaticClass.forClass(innerClass))));
        }
        return map;
    }

    @Override // jdk.internal.dynalink.beans.FacetIntrospector
    MethodHandle editMethodHandle(MethodHandle mh) {
        return editStaticMethodHandle(mh);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MethodHandle editStaticMethodHandle(MethodHandle mh) {
        return dropReceiver(mh, Object.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MethodHandle editConstructorMethodHandle(MethodHandle cmh) {
        return dropReceiver(cmh, StaticClass.class);
    }

    private static MethodHandle dropReceiver(MethodHandle mh, Class<?> receiverClass) {
        MethodHandle newHandle = MethodHandles.dropArguments(mh, 0, (Class<?>[]) new Class[]{receiverClass});
        if (mh.isVarargsCollector() && !newHandle.isVarargsCollector()) {
            MethodType type = mh.type();
            newHandle = newHandle.asVarargsCollector(type.parameterType(type.parameterCount() - 1));
        }
        return newHandle;
    }
}
