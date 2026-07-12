package sun.tools.attach;

import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.AttachOperationFailedException;
import com.sun.tools.attach.spi.AttachProvider;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/* loaded from: target.jar:sun/tools/attach/WindowsVirtualMachine.class */
public class WindowsVirtualMachine extends HotSpotVirtualMachine {
    private static byte[] stub;
    private volatile long hProcess;
    static final /* synthetic */ boolean $assertionsDisabled;

    static native void init();

    static native byte[] generateStub();

    static native long openProcess(int i) throws IOException;

    static native void closeProcess(long j) throws IOException;

    static native long createPipe(String str) throws IOException;

    static native void closePipe(long j) throws IOException;

    static native void connectPipe(long j) throws IOException;

    static native int readPipe(long j, byte[] bArr, int i, int i2) throws IOException;

    static native void enqueue(long j, byte[] bArr, String str, String str2, Object... objArr) throws IOException;

    static {
        $assertionsDisabled = !WindowsVirtualMachine.class.desiredAssertionStatus();
        System.loadLibrary("attach");
        init();
        stub = generateStub();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WindowsVirtualMachine(AttachProvider attachProvider, String str) throws AttachNotSupportedException, IOException {
        super(attachProvider, str);
        try {
            this.hProcess = openProcess(Integer.parseInt(str));
            try {
                enqueue(this.hProcess, stub, null, null, new Object[0]);
            } catch (IOException e) {
                throw new AttachNotSupportedException(e.getMessage());
            }
        } catch (NumberFormatException e2) {
            throw new AttachNotSupportedException("Invalid process identifier");
        }
    }

    @Override // com.sun.tools.attach.VirtualMachine
    public void detach() throws IOException {
        synchronized (this) {
            if (this.hProcess != -1) {
                closeProcess(this.hProcess);
                this.hProcess = -1L;
            }
        }
    }

    @Override // sun.tools.attach.HotSpotVirtualMachine
    InputStream execute(String str, Object... objArr) throws AgentLoadException, IOException {
        if (!$assertionsDisabled && objArr.length > 3) {
            throw new AssertionError();
        }
        String str2 = "\\\\.\\pipe\\javatool" + new Random().nextInt();
        long createPipe = createPipe(str2);
        if (this.hProcess == -1) {
            closePipe(createPipe);
            throw new IOException("Detached from target VM");
        }
        try {
            enqueue(this.hProcess, stub, str, str2, objArr);
            connectPipe(createPipe);
            PipedInputStream pipedInputStream = new PipedInputStream(createPipe);
            if (readInt(pipedInputStream) != 0) {
                String readErrorMessage = readErrorMessage(pipedInputStream);
                if (str.equals("load")) {
                    throw new AgentLoadException("Failed to load agent library");
                }
                if (readErrorMessage == null) {
                    throw new AttachOperationFailedException("Command failed in target VM");
                }
                throw new AttachOperationFailedException(readErrorMessage);
            }
            return pipedInputStream;
        } catch (IOException e) {
            closePipe(createPipe);
            throw e;
        }
    }

    /* loaded from: target.jar:sun/tools/attach/WindowsVirtualMachine$PipedInputStream.class */
    private class PipedInputStream extends InputStream {
        private long hPipe;

        public PipedInputStream(long j) {
            this.hPipe = j;
        }

        @Override // java.io.InputStream
        public synchronized int read() throws IOException {
            byte[] bArr = new byte[1];
            if (read(bArr, 0, 1) == 1) {
                return bArr[0] & 255;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
            if (i < 0 || i > bArr.length || i2 < 0 || i + i2 > bArr.length || i + i2 < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (i2 == 0) {
                return 0;
            }
            return WindowsVirtualMachine.readPipe(this.hPipe, bArr, i, i2);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.hPipe != -1) {
                WindowsVirtualMachine.closePipe(this.hPipe);
                this.hPipe = -1L;
            }
        }
    }
}
