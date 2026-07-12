package com.sun.tools.attach.spi;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.AttachPermission;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/tools/attach/spi/AttachProvider.class */
public abstract class AttachProvider {
    private static final Object lock = new Object();
    private static List<AttachProvider> providers = null;

    public abstract String name();

    public abstract String type();

    public abstract VirtualMachine attachVirtualMachine(String str) throws AttachNotSupportedException, IOException;

    public abstract List<VirtualMachineDescriptor> listVirtualMachines();

    /* JADX INFO: Access modifiers changed from: protected */
    public AttachProvider() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new AttachPermission("createAttachProvider"));
        }
    }

    public VirtualMachine attachVirtualMachine(VirtualMachineDescriptor virtualMachineDescriptor) throws AttachNotSupportedException, IOException {
        if (virtualMachineDescriptor.provider() != this) {
            throw new AttachNotSupportedException("provider mismatch");
        }
        return attachVirtualMachine(virtualMachineDescriptor.id());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static List<AttachProvider> providers() {
        List<AttachProvider> unmodifiableList;
        boolean z;
        ThreadDeath threadDeath;
        synchronized (lock) {
            if (providers == null) {
                providers = new ArrayList();
                Iterator it = ServiceLoader.load(AttachProvider.class, AttachProvider.class.getClassLoader()).iterator();
                while (it.hasNext()) {
                    try {
                        providers.add(it.next());
                    } finally {
                        if (z) {
                        }
                    }
                }
            }
            unmodifiableList = Collections.unmodifiableList(providers);
        }
        return unmodifiableList;
    }
}
