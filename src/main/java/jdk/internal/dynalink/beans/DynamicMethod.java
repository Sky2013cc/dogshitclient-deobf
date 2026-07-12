package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.linker.LinkerServices;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:jdk/internal/dynalink/beans/DynamicMethod.class */
public abstract class DynamicMethod {
    private final String name;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract MethodHandle getInvocation(CallSiteDescriptor callSiteDescriptor, LinkerServices linkerServices);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract SingleDynamicMethod getMethodForExactParamTypes(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean contains(SingleDynamicMethod singleDynamicMethod);

    /* JADX INFO: Access modifiers changed from: package-private */
    public DynamicMethod(String name) {
        this.name = name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getName() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getClassAndMethodName(Class<?> clazz, String name) {
        String clazzName = clazz.getCanonicalName();
        return (clazzName == null ? clazz.getName() : clazzName) + Constants.NAME_SEPARATOR + name;
    }

    public String toString() {
        return RuntimeConstants.SIG_ARRAY + getClass().getName() + " " + getName() + "]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isConstructor() {
        return false;
    }
}
