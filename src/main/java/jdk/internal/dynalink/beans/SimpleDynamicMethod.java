package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/* loaded from: target.jar:jdk/internal/dynalink/beans/SimpleDynamicMethod.class */
class SimpleDynamicMethod extends SingleDynamicMethod {
    private final MethodHandle target;
    private final boolean constructor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleDynamicMethod(MethodHandle target, Class<?> clazz, String name) {
        this(target, clazz, name, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleDynamicMethod(MethodHandle target, Class<?> clazz, String name, boolean constructor) {
        super(getName(target, clazz, name, constructor));
        this.target = target;
        this.constructor = constructor;
    }

    private static String getName(MethodHandle target, Class<?> clazz, String name, boolean constructor) {
        return getMethodNameWithSignature(target.type(), constructor ? name : getClassAndMethodName(clazz, name), !constructor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jdk.internal.dynalink.beans.SingleDynamicMethod
    public boolean isVarArgs() {
        return this.target.isVarargsCollector();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jdk.internal.dynalink.beans.SingleDynamicMethod
    public MethodType getMethodType() {
        return this.target.type();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jdk.internal.dynalink.beans.SingleDynamicMethod
    public MethodHandle getTarget(MethodHandles.Lookup lookup) {
        return this.target;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jdk.internal.dynalink.beans.DynamicMethod
    public boolean isConstructor() {
        return this.constructor;
    }
}
