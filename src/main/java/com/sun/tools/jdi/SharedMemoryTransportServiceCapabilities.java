package com.sun.tools.jdi;

import com.sun.jdi.connect.spi.TransportService;

/* compiled from: SharedMemoryTransportService.java */
/* loaded from: target.jar:com/sun/tools/jdi/SharedMemoryTransportServiceCapabilities.class */
class SharedMemoryTransportServiceCapabilities extends TransportService.Capabilities {
    @Override // com.sun.jdi.connect.spi.TransportService.Capabilities
    public boolean supportsMultipleConnections() {
        return false;
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
        return false;
    }
}
