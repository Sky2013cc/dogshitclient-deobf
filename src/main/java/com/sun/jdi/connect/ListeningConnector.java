package com.sun.jdi.connect;

import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.Connector;
import java.io.IOException;
import java.util.Map;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/connect/ListeningConnector.class */
public interface ListeningConnector extends Connector {
    boolean supportsMultipleConnections();

    String startListening(Map<String, ? extends Connector.Argument> map) throws IOException, IllegalConnectorArgumentsException;

    void stopListening(Map<String, ? extends Connector.Argument> map) throws IOException, IllegalConnectorArgumentsException;

    VirtualMachine accept(Map<String, ? extends Connector.Argument> map) throws IOException, IllegalConnectorArgumentsException;
}
