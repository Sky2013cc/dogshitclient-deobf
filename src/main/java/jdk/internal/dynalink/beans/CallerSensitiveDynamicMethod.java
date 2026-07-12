package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import jdk.internal.dynalink.support.Lookup;

/* loaded from: target.jar:jdk/internal/dynalink/beans/CallerSensitiveDynamicMethod.class */
class CallerSensitiveDynamicMethod extends SingleDynamicMethod {
    private final AccessibleObject target;
    private final MethodType type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallerSensitiveDynamicMethod(AccessibleObject target) {
        super(getName(target));
        this.target = target;
        this.type = getMethodType(target);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static String getName(AccessibleObject accessibleObject) {
        Member m = (Member) accessibleObject;
        boolean constructor = m instanceof Constructor;
        return getMethodNameWithSignature(getMethodType(accessibleObject), constructor ? m.getName() : getClassAndMethodName(m.getDeclaringClass(), m.getName()), !constructor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jdk.internal.dynalink.beans.SingleDynamicMethod
    public MethodType getMethodType() {
        return this.type;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static MethodType getMethodType(AccessibleObject accessibleObject) {
        Class cls;
        boolean isMethod = accessibleObject instanceof Method;
        Class<?> rtype = isMethod ? ((Method) accessibleObject).getReturnType() : ((Constructor) accessibleObject).getDeclaringClass();
        Class<?>[] ptypes = isMethod ? ((Method) accessibleObject).getParameterTypes() : ((Constructor) accessibleObject).getParameterTypes();
        MethodType type = MethodType.methodType(rtype, ptypes);
        Member m = (Member) accessibleObject;
        Class<?>[] clsArr = new Class[1];
        if (isMethod) {
            cls = Modifier.isStatic(m.getModifiers()) ? Object.class : m.getDeclaringClass();
        } else {
            cls = StaticClass.class;
        }
        clsArr[0] = cls;
        return type.insertParameterTypes(0, clsArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jdk.internal.dynalink.beans.SingleDynamicMethod
    public boolean isVarArgs() {
        return this.target instanceof Method ? ((Method) this.target).isVarArgs() : ((Constructor) this.target).isVarArgs();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jdk.internal.dynalink.beans.SingleDynamicMethod
    public MethodHandle getTarget(MethodHandles.Lookup lookup) {
        if (this.target instanceof Method) {
            MethodHandle mh = Lookup.unreflect(lookup, (Method) this.target);
            if (Modifier.isStatic(((Member) this.target).getModifiers())) {
                return StaticClassIntrospector.editStaticMethodHandle(mh);
            }
            return mh;
        }
        return StaticClassIntrospector.editConstructorMethodHandle(Lookup.unreflectConstructor(lookup, (Constructor) this.target));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jdk.internal.dynalink.beans.DynamicMethod
    public boolean isConstructor() {
        return this.target instanceof Constructor;
    }
}
