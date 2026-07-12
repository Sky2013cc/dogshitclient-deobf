package jdk.internal.dynalink.support;

import java.lang.invoke.MethodType;
import jdk.internal.dynalink.CallSiteDescriptor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:jdk/internal/dynalink/support/DefaultCallSiteDescriptor.class */
public class DefaultCallSiteDescriptor extends AbstractCallSiteDescriptor {
    private final String[] tokenizedName;
    private final MethodType methodType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultCallSiteDescriptor(String[] tokenizedName, MethodType methodType) {
        this.tokenizedName = tokenizedName;
        this.methodType = methodType;
    }

    @Override // jdk.internal.dynalink.CallSiteDescriptor
    public int getNameTokenCount() {
        return this.tokenizedName.length;
    }

    @Override // jdk.internal.dynalink.CallSiteDescriptor
    public String getNameToken(int i) {
        try {
            return this.tokenizedName[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getTokenizedName() {
        return this.tokenizedName;
    }

    @Override // jdk.internal.dynalink.CallSiteDescriptor
    public MethodType getMethodType() {
        return this.methodType;
    }

    @Override // jdk.internal.dynalink.CallSiteDescriptor
    public CallSiteDescriptor changeMethodType(MethodType newMethodType) {
        return CallSiteDescriptorFactory.getCanonicalPublicDescriptor(new DefaultCallSiteDescriptor(this.tokenizedName, newMethodType));
    }
}
