package com.sun.jdi.connect.spi;

import java.io.IOException;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/connect/spi/Connection.class */
public abstract class Connection {
    public abstract byte[] readPacket() throws IOException;

    public abstract void writePacket(byte[] bArr) throws IOException;

    public abstract void close() throws IOException;

    public abstract boolean isOpen();
}
