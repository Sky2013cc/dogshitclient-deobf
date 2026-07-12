package sun.tools.jstack;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.io.InputStream;
import sun.tools.attach.HotSpotVirtualMachine;

/* loaded from: target.jar:sun/tools/jstack/JStack.class */
public class JStack {
    public static void main(String[] strArr) throws Exception {
        String[] strArr2;
        if (strArr.length == 0) {
            usage(1);
        }
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            if (!str.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                break;
            }
            if (str.equals("-help") || str.equals("-h")) {
                usage(0);
            } else if (str.equals("-F")) {
                z = true;
            } else if (str.equals("-m")) {
                z2 = true;
            } else if (str.equals("-l")) {
                z3 = true;
            } else {
                usage(1);
            }
            i++;
        }
        if (z2) {
            z = true;
        }
        int length = strArr.length - i;
        if (length == 0 || length > 2) {
            usage(1);
        }
        if (length == 2) {
            z = true;
        } else if (!strArr[i].matches("[0-9]+")) {
            z = true;
        }
        if (z) {
            String[] strArr3 = new String[length];
            for (int i2 = i; i2 < strArr.length; i2++) {
                strArr3[i2 - i] = strArr[i2];
            }
            runJStackTool(z2, z3, strArr3);
            return;
        }
        String str2 = strArr[i];
        if (z3) {
            strArr2 = new String[]{"-l"};
        } else {
            strArr2 = new String[0];
        }
        runThreadDump(str2, strArr2);
    }

    private static void runJStackTool(boolean z, boolean z2, String[] strArr) throws Exception {
        Class<?> loadSAClass = loadSAClass();
        if (loadSAClass == null) {
            usage(1);
        }
        if (z) {
            strArr = prepend("-m", strArr);
        }
        if (z2) {
            strArr = prepend("-l", strArr);
        }
        loadSAClass.getDeclaredMethod("main", String[].class).invoke(null, strArr);
    }

    private static Class<?> loadSAClass() {
        try {
            return Class.forName("sun.jvm.hotspot.tools.JStack", true, ClassLoader.getSystemClassLoader());
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v25, types: [com.sun.tools.attach.VirtualMachine] */
    private static void runThreadDump(String str, String[] strArr) throws Exception {
        int read;
        HotSpotVirtualMachine hotSpotVirtualMachine = null;
        try {
            hotSpotVirtualMachine = VirtualMachine.attach(str);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                System.err.println(str + ": " + message);
            } else {
                e.printStackTrace();
            }
            if ((e instanceof AttachNotSupportedException) && loadSAClass() != null) {
                System.err.println("The -F option can be used when the target process is not responding");
            }
            System.exit(1);
        }
        InputStream remoteDataDump = hotSpotVirtualMachine.remoteDataDump(strArr);
        byte[] bArr = new byte[256];
        do {
            read = remoteDataDump.read(bArr);
            if (read > 0) {
                System.out.print(new String(bArr, 0, read, "UTF-8"));
            }
        } while (read > 0);
        remoteDataDump.close();
        hotSpotVirtualMachine.detach();
    }

    private static String[] prepend(String str, String[] strArr) {
        String[] strArr2 = new String[strArr.length + 1];
        strArr2[0] = str;
        System.arraycopy(strArr, 0, strArr2, 1, strArr.length);
        return strArr2;
    }

    private static void usage(int i) {
        System.err.println("Usage:");
        System.err.println("    jstack [-l] <pid>");
        System.err.println("        (to connect to running process)");
        if (loadSAClass() != null) {
            System.err.println("    jstack -F [-m] [-l] <pid>");
            System.err.println("        (to connect to a hung process)");
            System.err.println("    jstack [-m] [-l] <executable> <core>");
            System.err.println("        (to connect to a core file)");
            System.err.println("    jstack [-m] [-l] [server_id@]<remote server IP or hostname>");
            System.err.println("        (to connect to a remote debug server)");
        }
        System.err.println("");
        System.err.println("Options:");
        if (loadSAClass() != null) {
            System.err.println("    -F  to force a thread dump. Use when jstack <pid> does not respond (process is hung)");
            System.err.println("    -m  to print both java and native frames (mixed mode)");
        }
        System.err.println("    -l  long listing. Prints additional information about locks");
        System.err.println("    -h or -help to print this help message");
        System.exit(i);
    }
}
