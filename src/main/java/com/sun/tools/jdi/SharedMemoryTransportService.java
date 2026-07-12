package com.sun.tools.jdi;

import com.sun.jdi.connect.spi.Connection;
import com.sun.jdi.connect.spi.TransportService;
import java.io.IOException;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/tools/jdi/SharedMemoryTransportService.class */
class SharedMemoryTransportService extends TransportService {
    private ResourceBundle messages = null;

    private native void initialize();

    private native long startListening0(String str) throws IOException;

    private native long attach0(String str, long j) throws IOException;

    private native void stopListening0(long j) throws IOException;

    private native long accept0(long j, long j2) throws IOException;

    private native String name(long j) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/jdi/SharedMemoryTransportService$SharedMemoryListenKey.class */
    public static class SharedMemoryListenKey extends TransportService.ListenKey {
        long id;
        String name;

        SharedMemoryListenKey(long j, String str) {
            this.id = j;
            this.name = str;
        }

        long id() {
            return this.id;
        }

        void setId(long j) {
            this.id = j;
        }

        @Override // com.sun.jdi.connect.spi.TransportService.ListenKey
        public String address() {
            return this.name;
        }

        public String toString() {
            return address();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SharedMemoryTransportService() {
        System.loadLibrary("dt_shmem");
        initialize();
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public String name() {
        return "SharedMemory";
    }

    public String defaultAddress() {
        return "javadebug";
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public String description() {
        synchronized (this) {
            if (this.messages == null) {
                this.messages = ResourceBundle.getBundle("com.sun.tools.jdi.resources.jdi");
            }
        }
        return this.messages.getString("memory_transportservice.description");
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public TransportService.Capabilities capabilities() {
        return new SharedMemoryTransportServiceCapabilities();
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public Connection attach(String str, long j, long j2) throws IOException {
        if (str == null) {
            throw new NullPointerException("address is null");
        }
        SharedMemoryConnection sharedMemoryConnection = new SharedMemoryConnection(attach0(str, j));
        sharedMemoryConnection.handshake(j2);
        return sharedMemoryConnection;
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public TransportService.ListenKey startListening(String str) throws IOException {
        if (str == null || str.length() == 0) {
            str = defaultAddress();
        }
        long startListening0 = startListening0(str);
        return new SharedMemoryListenKey(startListening0, name(startListening0));
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public TransportService.ListenKey startListening() throws IOException {
        return startListening(null);
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public void stopListening(TransportService.ListenKey listenKey) throws IOException {
        long id;
        if (!(listenKey instanceof SharedMemoryListenKey)) {
            throw new IllegalArgumentException("Invalid listener");
        }
        SharedMemoryListenKey sharedMemoryListenKey = (SharedMemoryListenKey) listenKey;
        synchronized (sharedMemoryListenKey) {
            id = sharedMemoryListenKey.id();
            if (id == 0) {
                throw new IllegalArgumentException("Invalid listener");
            }
            sharedMemoryListenKey.setId(0L);
        }
        stopListening0(id);
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public Connection accept(TransportService.ListenKey listenKey, long j, long j2) throws IOException {
        long id;
        if (!(listenKey instanceof SharedMemoryListenKey)) {
            throw new IllegalArgumentException("Invalid listener");
        }
        SharedMemoryListenKey sharedMemoryListenKey = (SharedMemoryListenKey) listenKey;
        synchronized (sharedMemoryListenKey) {
            id = sharedMemoryListenKey.id();
            if (id == 0) {
                throw new IllegalArgumentException("Invalid listener");
            }
        }
        SharedMemoryConnection sharedMemoryConnection = new SharedMemoryConnection(accept0(id, j));
        sharedMemoryConnection.handshake(j2);
        return sharedMemoryConnection;
    }
}
