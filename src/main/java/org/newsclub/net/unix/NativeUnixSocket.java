package org.newsclub.net.unix;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:org/newsclub/net/unix/NativeUnixSocket.class */
public final class NativeUnixSocket {
    private static boolean loaded;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void bind(String str, FileDescriptor fileDescriptor, int i) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void listen(FileDescriptor fileDescriptor, int i) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void accept(String str, FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void connect(String str, FileDescriptor fileDescriptor) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int read(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int write(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void close(FileDescriptor fileDescriptor) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void shutdown(FileDescriptor fileDescriptor, int i) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getSocketOptionInt(FileDescriptor fileDescriptor, int i) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setSocketOptionInt(FileDescriptor fileDescriptor, int i, int i2) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void unlink(String str) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int available(FileDescriptor fileDescriptor) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void initServerImpl(AFUNIXServerSocket aFUNIXServerSocket, AFUNIXSocketImpl aFUNIXSocketImpl);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setCreated(AFUNIXSocket aFUNIXSocket);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setConnected(AFUNIXSocket aFUNIXSocket);

    static native void setBound(AFUNIXSocket aFUNIXSocket);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setCreatedServer(AFUNIXServerSocket aFUNIXServerSocket);

    static native void setBoundServer(AFUNIXServerSocket aFUNIXServerSocket);

    static native void setPort(AFUNIXSocketAddress aFUNIXSocketAddress, int i);

    NativeUnixSocket() {
    }

    static {
        loaded = false;
        try {
            Class.forName("org.newsclub.net.unix.NarSystem").getMethod("loadLibrary", new Class[0]).invoke(null, new Object[0]);
            loaded = true;
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Could not find NarSystem class.\n\n*** ECLIPSE USERS ***\nIf you're running from within Eclipse, please try closing the \"junixsocket-native-common\" project\n", e);
        } catch (Exception e2) {
            throw new IllegalStateException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isLoaded() {
        return loaded;
    }

    static void checkSupported() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setPort1(AFUNIXSocketAddress addr, int port) throws AFUNIXSocketException {
        Field portField;
        if (port < 0) {
            throw new IllegalArgumentException("port out of range:" + port);
        }
        boolean setOk = false;
        try {
            Field holderField = InetSocketAddress.class.getDeclaredField("holder");
            if (holderField != null) {
                holderField.setAccessible(true);
                Object holder = holderField.get(addr);
                if (holder != null && (portField = holder.getClass().getDeclaredField(Constants.TAG_PORT)) != null) {
                    portField.setAccessible(true);
                    portField.set(holder, Integer.valueOf(port));
                    setOk = true;
                }
            } else {
                setPort(addr, port);
            }
            if (!setOk) {
                throw new AFUNIXSocketException("Could not set port");
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            if (e2 instanceof AFUNIXSocketException) {
                throw ((AFUNIXSocketException) e2);
            }
            throw new AFUNIXSocketException("Could not set port", e2);
        }
    }
}
