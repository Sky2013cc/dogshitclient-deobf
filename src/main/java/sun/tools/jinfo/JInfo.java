package sun.tools.jinfo;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import sun.tools.attach.HotSpotVirtualMachine;

/* loaded from: target.jar:sun/tools/jinfo/JInfo.class */
public class JInfo {
    public static void main(String[] strArr) throws Exception {
        int i;
        int i2;
        if (strArr.length == 0) {
            usage(1);
        }
        boolean z = true;
        String str = strArr[0];
        if (str.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
            if (str.equals("-flags") || str.equals("-sysprops")) {
                if (strArr.length != 2 && strArr.length != 3) {
                    usage(1);
                }
            } else if (str.equals("-flag")) {
                z = false;
            } else {
                if (str.equals("-help") || str.equals("-h")) {
                    i2 = 0;
                } else {
                    i2 = 1;
                }
                usage(i2);
            }
        }
        if (z) {
            runTool(strArr);
            return;
        }
        if (strArr.length == 3) {
            flag(strArr[2], strArr[1]);
            return;
        }
        if (str.equals("-help") || str.equals("-h")) {
            i = 0;
        } else {
            i = 1;
        }
        usage(i);
    }

    private static void runTool(String[] strArr) throws Exception {
        Class<?> loadClass = loadClass("sun.jvm.hotspot.tools.JInfo");
        if (loadClass == null) {
            usage(1);
        }
        loadClass.getDeclaredMethod("main", String[].class).invoke(null, strArr);
    }

    private static Class<?> loadClass(String str) {
        try {
            return Class.forName(str, true, ClassLoader.getSystemClassLoader());
        } catch (Exception e) {
            return null;
        }
    }

    private static void flag(String str, String str2) throws IOException {
        InputStream printFlag;
        VirtualMachine attach = attach(str);
        int indexOf = str2.indexOf(61);
        if (indexOf != -1) {
            printFlag = ((HotSpotVirtualMachine) attach).setFlag(str2.substring(0, indexOf), str2.substring(indexOf + 1));
        } else {
            switch (str2.charAt(0)) {
                case '+':
                    printFlag = ((HotSpotVirtualMachine) attach).setFlag(str2.substring(1), "1");
                    break;
                case '-':
                    printFlag = ((HotSpotVirtualMachine) attach).setFlag(str2.substring(1), PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
                    break;
                default:
                    printFlag = ((HotSpotVirtualMachine) attach).printFlag(str2);
                    break;
            }
        }
        drain(attach, printFlag);
    }

    private static VirtualMachine attach(String str) {
        try {
            return VirtualMachine.attach(str);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                System.err.println(str + ": " + message);
            } else {
                e.printStackTrace();
            }
            System.exit(1);
            return null;
        }
    }

    private static void drain(VirtualMachine virtualMachine, InputStream inputStream) throws IOException {
        int read;
        byte[] bArr = new byte[256];
        do {
            read = inputStream.read(bArr);
            if (read > 0) {
                System.out.print(new String(bArr, 0, read, "UTF-8"));
            }
        } while (read > 0);
        inputStream.close();
        virtualMachine.detach();
    }

    private static void usage(int i) {
        boolean z = loadClass("sun.jvm.hotspot.tools.JInfo") != null;
        System.err.println("Usage:");
        if (z) {
            System.err.println("    jinfo [option] <pid>");
            System.err.println("        (to connect to running process)");
            System.err.println("    jinfo [option] <executable <core>");
            System.err.println("        (to connect to a core file)");
            System.err.println("    jinfo [option] [server_id@]<remote server IP or hostname>");
            System.err.println("        (to connect to remote debug server)");
            System.err.println("");
            System.err.println("where <option> is one of:");
            System.err.println("    -flag <name>         to print the value of the named VM flag");
            System.err.println("    -flag [+|-]<name>    to enable or disable the named VM flag");
            System.err.println("    -flag <name>=<value> to set the named VM flag to the given value");
            System.err.println("    -flags               to print VM flags");
            System.err.println("    -sysprops            to print Java system properties");
            System.err.println("    <no option>          to print both of the above");
            System.err.println("    -h | -help           to print this help message");
        } else {
            System.err.println("    jinfo <option> <pid>");
            System.err.println("       (to connect to a running process)");
            System.err.println("");
            System.err.println("where <option> is one of:");
            System.err.println("    -flag <name>         to print the value of the named VM flag");
            System.err.println("    -flag [+|-]<name>    to enable or disable the named VM flag");
            System.err.println("    -flag <name>=<value> to set the named VM flag to the given value");
            System.err.println("    -h | -help           to print this help message");
        }
        System.exit(i);
    }
}
