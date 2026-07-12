package com.sun.jdi;

import com.sun.tools.jdi.VirtualMachineManagerImpl;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/Bootstrap.class */
public class Bootstrap {
    public static synchronized VirtualMachineManager virtualMachineManager() {
        return VirtualMachineManagerImpl.virtualMachineManager();
    }
}
