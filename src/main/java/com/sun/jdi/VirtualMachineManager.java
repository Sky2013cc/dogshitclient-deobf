package com.sun.jdi;

import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.connect.ListeningConnector;
import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/VirtualMachineManager.class */
public interface VirtualMachineManager {
    LaunchingConnector defaultConnector();

    List<LaunchingConnector> launchingConnectors();

    List<AttachingConnector> attachingConnectors();

    List<ListeningConnector> listeningConnectors();

    List<Connector> allConnectors();

    List<VirtualMachine> connectedVirtualMachines();

    int majorInterfaceVersion();

    int minorInterfaceVersion();

    VirtualMachine createVirtualMachine(Connection connection, Process process) throws IOException;

    VirtualMachine createVirtualMachine(Connection connection) throws IOException;
}
