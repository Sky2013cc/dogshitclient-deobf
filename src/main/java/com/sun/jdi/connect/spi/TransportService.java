package com.sun.jdi.connect.spi;

import java.io.IOException;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/connect/spi/TransportService.class */
public abstract class TransportService {

    @Exported
    /* loaded from: target.jar:com/sun/jdi/connect/spi/TransportService$Capabilities.class */
    public static abstract class Capabilities {
        public abstract boolean supportsMultipleConnections();

        public abstract boolean supportsAttachTimeout();

        public abstract boolean supportsAcceptTimeout();

        public abstract boolean supportsHandshakeTimeout();
    }

    @Exported
    /* loaded from: target.jar:com/sun/jdi/connect/spi/TransportService$ListenKey.class */
    public static abstract class ListenKey {
        public abstract String address();
    }

    public abstract String name();

    public abstract String description();

    public abstract Capabilities capabilities();

    public abstract Connection attach(String str, long j, long j2) throws IOException;

    public abstract ListenKey startListening(String str) throws IOException;

    public abstract ListenKey startListening() throws IOException;

    public abstract void stopListening(ListenKey listenKey) throws IOException;

    public abstract Connection accept(ListenKey listenKey, long j, long j2) throws IOException;
}
