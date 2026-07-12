package com.sun.tools.jdi;

import com.sun.jdi.connect.spi.ClosedConnectionException;
import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/jdi/SharedMemoryConnection.class */
class SharedMemoryConnection extends Connection {
    private long id;
    private Object receiveLock = new Object();
    private Object sendLock = new Object();
    private Object closeLock = new Object();
    private boolean closed = false;

    private native byte receiveByte0(long j) throws IOException;

    private native void sendByte0(long j, byte b) throws IOException;

    private native void close0(long j);

    private native byte[] receivePacket0(long j) throws IOException;

    private native void sendPacket0(long j, byte[] bArr) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handshake(long j) throws IOException {
        byte[] bytes = "JDWP-Handshake".getBytes("UTF-8");
        for (byte b : bytes) {
            sendByte0(this.id, b);
        }
        for (byte b2 : bytes) {
            if (receiveByte0(this.id) != b2) {
                throw new IOException("handshake failed - unrecognized message from target VM");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SharedMemoryConnection(long j) throws IOException {
        this.id = j;
    }

    @Override // com.sun.jdi.connect.spi.Connection
    public void close() {
        synchronized (this.closeLock) {
            if (!this.closed) {
                close0(this.id);
                this.closed = true;
            }
        }
    }

    @Override // com.sun.jdi.connect.spi.Connection
    public boolean isOpen() {
        boolean z;
        synchronized (this.closeLock) {
            z = !this.closed;
        }
        return z;
    }

    @Override // com.sun.jdi.connect.spi.Connection
    public byte[] readPacket() throws IOException {
        byte[] receivePacket0;
        if (!isOpen()) {
            throw new ClosedConnectionException("Connection closed");
        }
        try {
            synchronized (this.receiveLock) {
                receivePacket0 = receivePacket0(this.id);
            }
            return receivePacket0;
        } catch (IOException e) {
            if (!isOpen()) {
                throw new ClosedConnectionException("Connection closed");
            }
            throw e;
        }
    }

    @Override // com.sun.jdi.connect.spi.Connection
    public void writePacket(byte[] bArr) throws IOException {
        if (!isOpen()) {
            throw new ClosedConnectionException("Connection closed");
        }
        if (bArr.length < 11) {
            throw new IllegalArgumentException("packet is insufficient size");
        }
        int i = ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | ((bArr[3] & 255) << 0);
        if (i < 11) {
            throw new IllegalArgumentException("packet is insufficient size");
        }
        if (i > bArr.length) {
            throw new IllegalArgumentException("length mis-match");
        }
        try {
            synchronized (this.sendLock) {
                sendPacket0(this.id, bArr);
            }
        } catch (IOException e) {
            if (!isOpen()) {
                throw new ClosedConnectionException("Connection closed");
            }
            throw e;
        }
    }
}
