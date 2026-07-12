package com.sun.tools.jdi;

import com.sun.jdi.connect.TransportTimeoutException;
import com.sun.jdi.connect.spi.Connection;
import com.sun.jdi.connect.spi.TransportService;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import jdk.internal.dynalink.CallSiteDescriptor;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/jdi/SocketTransportService.class */
public class SocketTransportService extends TransportService {
    private ResourceBundle messages = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/jdi/SocketTransportService$SocketListenKey.class */
    public static class SocketListenKey extends TransportService.ListenKey {
        ServerSocket ss;

        SocketListenKey(ServerSocket serverSocket) {
            this.ss = serverSocket;
        }

        ServerSocket socket() {
            return this.ss;
        }

        @Override // com.sun.jdi.connect.spi.TransportService.ListenKey
        public String address() {
            String str;
            InetAddress inetAddress = this.ss.getInetAddress();
            if (inetAddress.isAnyLocalAddress()) {
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    try {
                        inetAddress = InetAddress.getByAddress("127.0.0.1", new byte[]{Byte.MAX_VALUE, 0, 0, 1});
                    } catch (UnknownHostException e2) {
                        throw new InternalError("unable to get local hostname");
                    }
                }
            }
            String hostName = inetAddress.getHostName();
            String hostAddress = inetAddress.getHostAddress();
            if (hostName.equals(hostAddress)) {
                if (inetAddress instanceof Inet6Address) {
                    str = RuntimeConstants.SIG_ARRAY + hostAddress + "]";
                } else {
                    str = hostAddress;
                }
            } else {
                str = hostName;
            }
            return str + CallSiteDescriptor.TOKEN_DELIMITER + this.ss.getLocalPort();
        }

        public String toString() {
            return address();
        }
    }

    void handshake(Socket socket, long j) throws IOException {
        socket.setSoTimeout((int) j);
        byte[] bytes = "JDWP-Handshake".getBytes("UTF-8");
        socket.getOutputStream().write(bytes);
        byte[] bArr = new byte[bytes.length];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < bytes.length) {
                try {
                    int read = socket.getInputStream().read(bArr, i2, bytes.length - i2);
                    if (read < 0) {
                        socket.close();
                        throw new IOException("handshake failed - connection prematurally closed");
                    }
                    i = i2 + read;
                } catch (SocketTimeoutException e) {
                    throw new IOException("handshake timeout");
                }
            } else {
                for (int i3 = 0; i3 < bytes.length; i3++) {
                    if (bArr[i3] != bytes[i3]) {
                        throw new IOException("handshake failed - unrecognized message from target VM");
                    }
                }
                socket.setSoTimeout(0);
                return;
            }
        }
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public String name() {
        return "Socket";
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public String description() {
        synchronized (this) {
            if (this.messages == null) {
                this.messages = ResourceBundle.getBundle("com.sun.tools.jdi.resources.jdi");
            }
        }
        return this.messages.getString("socket_transportservice.description");
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public TransportService.Capabilities capabilities() {
        return new SocketTransportServiceCapabilities();
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public Connection attach(String str, long j, long j2) throws IOException {
        String substring;
        String substring2;
        if (str == null) {
            throw new NullPointerException("address is null");
        }
        if (j < 0 || j2 < 0) {
            throw new IllegalArgumentException("timeout is negative");
        }
        int indexOf = str.indexOf(58);
        if (indexOf < 0) {
            substring = InetAddress.getLocalHost().getHostName();
            substring2 = str;
        } else {
            substring = str.substring(0, indexOf);
            substring2 = str.substring(indexOf + 1);
        }
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(substring, Integer.decode(substring2).intValue());
            Socket socket = new Socket();
            try {
                socket.connect(inetSocketAddress, (int) j);
                try {
                    handshake(socket, j2);
                    return new SocketConnection(socket);
                } catch (IOException e) {
                    try {
                        socket.close();
                    } catch (IOException e2) {
                    }
                    throw e;
                }
            } catch (SocketTimeoutException e3) {
                try {
                    socket.close();
                } catch (IOException e4) {
                }
                throw new TransportTimeoutException("timed out trying to establish connection");
            }
        } catch (NumberFormatException e5) {
            throw new IllegalArgumentException("unable to parse port number in address");
        }
    }

    TransportService.ListenKey startListening(String str, int i) throws IOException {
        InetSocketAddress inetSocketAddress;
        if (str == null) {
            inetSocketAddress = new InetSocketAddress(i);
        } else {
            inetSocketAddress = new InetSocketAddress(str, i);
        }
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(inetSocketAddress);
        return new SocketListenKey(serverSocket);
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public TransportService.ListenKey startListening(String str) throws IOException {
        if (str == null || str.length() == 0) {
            str = PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
        }
        int indexOf = str.indexOf(58);
        String str2 = null;
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
        }
        try {
            return startListening(str2, Integer.decode(str).intValue());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("unable to parse port number in address");
        }
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public TransportService.ListenKey startListening() throws IOException {
        return startListening(null, 0);
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public void stopListening(TransportService.ListenKey listenKey) throws IOException {
        if (!(listenKey instanceof SocketListenKey)) {
            throw new IllegalArgumentException("Invalid listener");
        }
        synchronized (listenKey) {
            ServerSocket socket = ((SocketListenKey) listenKey).socket();
            if (socket.isClosed()) {
                throw new IllegalArgumentException("Invalid listener");
            }
            socket.close();
        }
    }

    @Override // com.sun.jdi.connect.spi.TransportService
    public Connection accept(TransportService.ListenKey listenKey, long j, long j2) throws IOException {
        ServerSocket socket;
        if (j < 0 || j2 < 0) {
            throw new IllegalArgumentException("timeout is negative");
        }
        if (!(listenKey instanceof SocketListenKey)) {
            throw new IllegalArgumentException("Invalid listener");
        }
        synchronized (listenKey) {
            socket = ((SocketListenKey) listenKey).socket();
            if (socket.isClosed()) {
                throw new IllegalArgumentException("Invalid listener");
            }
        }
        socket.setSoTimeout((int) j);
        try {
            Socket accept = socket.accept();
            handshake(accept, j2);
            return new SocketConnection(accept);
        } catch (SocketTimeoutException e) {
            throw new TransportTimeoutException("timeout waiting for connection");
        }
    }

    public String toString() {
        return name();
    }
}
