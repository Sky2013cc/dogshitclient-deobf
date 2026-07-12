package com.sun.jdi.connect;

import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.Connector;
import java.io.IOException;
import java.util.Map;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/connect/LaunchingConnector.class */
public interface LaunchingConnector extends Connector {
    VirtualMachine launch(Map<String, ? extends Connector.Argument> map) throws IOException, IllegalConnectorArgumentsException, VMStartException;
}
