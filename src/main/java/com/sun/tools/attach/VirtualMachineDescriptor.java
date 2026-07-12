package com.sun.tools.attach;

import com.sun.tools.attach.spi.AttachProvider;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/tools/attach/VirtualMachineDescriptor.class */
public class VirtualMachineDescriptor {
    private AttachProvider provider;
    private String id;
    private String displayName;
    private volatile int hash;

    public VirtualMachineDescriptor(AttachProvider attachProvider, String str, String str2) {
        if (attachProvider == null) {
            throw new NullPointerException("provider cannot be null");
        }
        if (str == null) {
            throw new NullPointerException("identifier cannot be null");
        }
        if (str2 == null) {
            throw new NullPointerException("display name cannot be null");
        }
        this.provider = attachProvider;
        this.id = str;
        this.displayName = str2;
    }

    public VirtualMachineDescriptor(AttachProvider attachProvider, String str) {
        this(attachProvider, str, str);
    }

    public AttachProvider provider() {
        return this.provider;
    }

    public String id() {
        return this.id;
    }

    public String displayName() {
        return this.displayName;
    }

    public int hashCode() {
        if (this.hash != 0) {
            return this.hash;
        }
        this.hash = (this.provider.hashCode() * 127) + this.id.hashCode();
        return this.hash;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VirtualMachineDescriptor)) {
            return false;
        }
        VirtualMachineDescriptor virtualMachineDescriptor = (VirtualMachineDescriptor) obj;
        if (virtualMachineDescriptor.provider() != provider() || !virtualMachineDescriptor.id().equals(id())) {
            return false;
        }
        return true;
    }

    public String toString() {
        String str = this.provider.toString() + ": " + this.id;
        if (this.displayName != this.id) {
            str = str + " " + this.displayName;
        }
        return str;
    }
}
