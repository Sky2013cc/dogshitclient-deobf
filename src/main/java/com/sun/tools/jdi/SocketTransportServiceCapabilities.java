package com.sun.tools.jdi;

import com.sun.jdi.connect.spi.TransportService;

/* compiled from: SocketTransportService.java */
/* loaded from: target.jar:com/sun/tools/jdi/SocketTransportServiceCapabilities.class */
class SocketTransportServiceCapabilities extends TransportService.Capabilities {
    @Override // com.sun.jdi.connect.spi.TransportService.Capabilities
    public boolean supportsMultipleConnections() {
        return true;
    }

    @Override // com.sun.jdi.connect.spi.TransportService.Capabilities
    public boolean supportsAttachTimeout() {
        return true;
    }

    @Override // com.sun.jdi.connect.spi.TransportService.Capabilities
    public boolean supportsAcceptTimeout() {
        return true;
    }

    @Override // com.sun.jdi.connect.spi.TransportService.Capabilities
    public boolean supportsHandshakeTimeout() {
        return true;
    }
}
