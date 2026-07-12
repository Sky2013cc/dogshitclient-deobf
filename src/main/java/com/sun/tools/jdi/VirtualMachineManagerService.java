package com.sun.tools.jdi;

import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.LaunchingConnector;

/* loaded from: target.jar:com/sun/tools/jdi/VirtualMachineManagerService.class */
public interface VirtualMachineManagerService extends VirtualMachineManager {
    void setDefaultConnector(LaunchingConnector launchingConnector);

    void addConnector(Connector connector);

    void removeConnector(Connector connector);
}
