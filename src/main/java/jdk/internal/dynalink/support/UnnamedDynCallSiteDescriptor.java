package jdk.internal.dynalink.support;

import java.lang.invoke.MethodType;
import jdk.internal.dynalink.CallSiteDescriptor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:jdk/internal/dynalink/support/UnnamedDynCallSiteDescriptor.class */
public class UnnamedDynCallSiteDescriptor extends AbstractCallSiteDescriptor {
    private final MethodType methodType;
    private final String op;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnnamedDynCallSiteDescriptor(String op, MethodType methodType) {
        this.op = op;
        this.methodType = methodType;
    }

    @Override // jdk.internal.dynalink.CallSiteDescriptor
    public int getNameTokenCount() {
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getOp() {
        return this.op;
    }

    @Override // jdk.internal.dynalink.CallSiteDescriptor
    public String getNameToken(int i) {
        switch (i) {
            case 0:
                return "dyn";
            case 1:
                return this.op;
            default:
                throw new IndexOutOfBoundsException(String.valueOf(i));
        }
    }

    @Override // jdk.internal.dynalink.CallSiteDescriptor
    public MethodType getMethodType() {
        return this.methodType;
    }

    @Override // jdk.internal.dynalink.CallSiteDescriptor
    public CallSiteDescriptor changeMethodType(MethodType newMethodType) {
        return CallSiteDescriptorFactory.getCanonicalPublicDescriptor(new UnnamedDynCallSiteDescriptor(this.op, newMethodType));
    }
}
