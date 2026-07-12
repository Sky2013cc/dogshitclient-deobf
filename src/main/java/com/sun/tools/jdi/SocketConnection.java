package com.sun.tools.jdi;

import com.sun.jdi.connect.spi.ClosedConnectionException;
import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* compiled from: SocketTransportService.java */
/* loaded from: target.jar:com/sun/tools/jdi/SocketConnection.class */
class SocketConnection extends Connection {
    private Socket socket;
    private OutputStream socketOutput;
    private InputStream socketInput;
    private boolean closed = false;
    private Object receiveLock = new Object();
    private Object sendLock = new Object();
    private Object closeLock = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public SocketConnection(Socket socket) throws IOException {
        this.socket = socket;
        socket.setTcpNoDelay(true);
        this.socketInput = socket.getInputStream();
        this.socketOutput = socket.getOutputStream();
    }

    @Override // com.sun.jdi.connect.spi.Connection
    public void close() throws IOException {
        synchronized (this.closeLock) {
            if (this.closed) {
                return;
            }
            this.socketOutput.close();
            this.socketInput.close();
            this.socket.close();
            this.closed = true;
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
        if (!isOpen()) {
            throw new ClosedConnectionException("connection is closed");
        }
        synchronized (this.receiveLock) {
            try {
                int read = this.socketInput.read();
                int read2 = this.socketInput.read();
                int read3 = this.socketInput.read();
                int read4 = this.socketInput.read();
                if (read < 0) {
                    return new byte[0];
                }
                if (read2 < 0 || read3 < 0 || read4 < 0) {
                    throw new IOException("protocol error - premature EOF");
                }
                int i = (read << 24) | (read2 << 16) | (read3 << 8) | (read4 << 0);
                if (i < 0) {
                    throw new IOException("protocol error - invalid length");
                }
                byte[] bArr = new byte[i];
                bArr[0] = (byte) read;
                bArr[1] = (byte) read2;
                bArr[2] = (byte) read3;
                bArr[3] = (byte) read4;
                int i2 = 4;
                int i3 = i - 4;
                while (i3 > 0) {
                    try {
                        int read5 = this.socketInput.read(bArr, i2, i3);
                        if (read5 < 0) {
                            throw new IOException("protocol error - premature EOF");
                        }
                        i3 -= read5;
                        i2 += read5;
                    } catch (IOException e) {
                        if (!isOpen()) {
                            throw new ClosedConnectionException("connection is closed");
                        }
                        throw e;
                    }
                }
                return bArr;
            } catch (IOException e2) {
                if (!isOpen()) {
                    throw new ClosedConnectionException("connection is closed");
                }
                throw e2;
            }
        }
    }

    @Override // com.sun.jdi.connect.spi.Connection
    public void writePacket(byte[] bArr) throws IOException {
        if (!isOpen()) {
            throw new ClosedConnectionException("connection is closed");
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
        synchronized (this.sendLock) {
            try {
                this.socketOutput.write(bArr, 0, i);
            } catch (IOException e) {
                if (!isOpen()) {
                    throw new ClosedConnectionException("connection is closed");
                }
                throw e;
            }
        }
    }
}
