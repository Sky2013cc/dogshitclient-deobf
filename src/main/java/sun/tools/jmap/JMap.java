package sun.tools.jmap;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.doclint.DocLint;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import sun.tools.attach.HotSpotVirtualMachine;

/* loaded from: target.jar:sun/tools/jmap/JMap.class */
public class JMap {
    private static String HISTO_OPTION;
    private static String LIVE_HISTO_OPTION;
    private static String DUMP_OPTION_PREFIX;
    private static String SA_TOOL_OPTIONS;
    private static String FORCE_SA_OPTION;
    private static String DEFAULT_OPTION;
    private static final String LIVE_OBJECTS_OPTION = "-live";
    private static final String ALL_OBJECTS_OPTION = "-all";
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !JMap.class.desiredAssertionStatus();
        HISTO_OPTION = "-histo";
        LIVE_HISTO_OPTION = "-histo:live";
        DUMP_OPTION_PREFIX = "-dump:";
        SA_TOOL_OPTIONS = "-heap|-heap:format=b|-clstats|-finalizerinfo";
        FORCE_SA_OPTION = "-F";
        DEFAULT_OPTION = "-pmap";
    }

    public static void main(String[] strArr) throws Exception {
        if (strArr.length == 0) {
            usage(1);
        }
        boolean z = false;
        String str = null;
        int i = 0;
        while (i < strArr.length) {
            String str2 = strArr[i];
            if (!str2.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                break;
            }
            if (str2.equals("-help") || str2.equals("-h")) {
                usage(0);
            } else if (str2.equals(FORCE_SA_OPTION)) {
                z = true;
            } else {
                if (str != null) {
                    usage(1);
                }
                str = str2;
            }
            i++;
        }
        if (str == null) {
            str = DEFAULT_OPTION;
        }
        if (str.matches(SA_TOOL_OPTIONS)) {
            z = true;
        }
        int length = strArr.length - i;
        if (length == 0 || length > 2) {
            usage(1);
        }
        if (i == 0 || length != 1) {
            z = true;
        } else if (!strArr[i].matches("[0-9]+")) {
            z = true;
        }
        if (z) {
            String[] strArr2 = new String[length];
            for (int i2 = i; i2 < strArr.length; i2++) {
                strArr2[i2 - i] = strArr[i2];
            }
            runTool(str, strArr2);
            return;
        }
        String str3 = strArr[1];
        if (str.equals(HISTO_OPTION)) {
            histo(str3, false);
            return;
        }
        if (str.equals(LIVE_HISTO_OPTION)) {
            histo(str3, true);
        } else if (str.startsWith(DUMP_OPTION_PREFIX)) {
            dump(str3, str);
        } else {
            usage(1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    private static void runTool(String str, String[] strArr) throws Exception {
        ?? r0 = {new String[]{"-pmap", "sun.jvm.hotspot.tools.PMap"}, new String[]{"-heap", "sun.jvm.hotspot.tools.HeapSummary"}, new String[]{"-heap:format=b", "sun.jvm.hotspot.tools.HeapDumper"}, new String[]{"-histo", "sun.jvm.hotspot.tools.ObjectHistogram"}, new String[]{"-clstats", "sun.jvm.hotspot.tools.ClassLoaderStats"}, new String[]{"-finalizerinfo", "sun.jvm.hotspot.tools.FinalizerInfo"}};
        String str2 = null;
        if (str.startsWith(DUMP_OPTION_PREFIX)) {
            String parseDumpOptions = parseDumpOptions(str);
            if (parseDumpOptions == null) {
                usage(1);
            }
            str2 = "sun.jvm.hotspot.tools.HeapDumper";
            strArr = prepend("-f", prepend(parseDumpOptions, strArr));
        } else {
            int i = 0;
            while (true) {
                if (i >= r0.length) {
                    break;
                }
                if (str.equals(r0[i][0])) {
                    str2 = r0[i][1];
                    break;
                }
                i++;
            }
        }
        if (str2 == null) {
            usage(1);
        }
        Class<?> loadClass = loadClass(str2);
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

    private static void histo(String str, boolean z) throws IOException {
        VirtualMachine attach = attach(str);
        HotSpotVirtualMachine hotSpotVirtualMachine = (HotSpotVirtualMachine) attach;
        Object[] objArr = new Object[1];
        objArr[0] = z ? LIVE_OBJECTS_OPTION : ALL_OBJECTS_OPTION;
        drain(attach, hotSpotVirtualMachine.heapHisto(objArr));
    }

    private static void dump(String str, String str2) throws IOException {
        String parseDumpOptions = parseDumpOptions(str2);
        if (parseDumpOptions == null) {
            usage(1);
        }
        String canonicalPath = new File(parseDumpOptions).getCanonicalPath();
        boolean isDumpLiveObjects = isDumpLiveObjects(str2);
        VirtualMachine attach = attach(str);
        System.out.println("Dumping heap to " + canonicalPath + " ...");
        HotSpotVirtualMachine hotSpotVirtualMachine = (HotSpotVirtualMachine) attach;
        Object[] objArr = new Object[2];
        objArr[0] = canonicalPath;
        objArr[1] = isDumpLiveObjects ? LIVE_OBJECTS_OPTION : ALL_OBJECTS_OPTION;
        drain(attach, hotSpotVirtualMachine.dumpHeap(objArr));
    }

    private static String parseDumpOptions(String str) {
        if (!$assertionsDisabled && !str.startsWith(DUMP_OPTION_PREFIX)) {
            throw new AssertionError();
        }
        String str2 = null;
        for (String str3 : str.substring(DUMP_OPTION_PREFIX.length()).split(DocLint.TAGS_SEPARATOR)) {
            if (!str3.equals("format=b") && !str3.equals("live")) {
                if (str3.startsWith("file=")) {
                    str2 = str3.substring(5);
                    if (str2.length() == 0) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        }
        return str2;
    }

    private static boolean isDumpLiveObjects(String str) {
        for (String str2 : str.substring(DUMP_OPTION_PREFIX.length()).split(DocLint.TAGS_SEPARATOR)) {
            if (str2.equals("live")) {
                return true;
            }
        }
        return false;
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
            if ((e instanceof AttachNotSupportedException) && haveSA()) {
                System.err.println("The -F option can be used when the target process is not responding");
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

    private static String[] prepend(String str, String[] strArr) {
        String[] strArr2 = new String[strArr.length + 1];
        strArr2[0] = str;
        System.arraycopy(strArr, 0, strArr2, 1, strArr.length);
        return strArr2;
    }

    private static boolean haveSA() {
        return loadClass("sun.jvm.hotspot.tools.HeapSummary") != null;
    }

    private static void usage(int i) {
        System.err.println("Usage:");
        if (haveSA()) {
            System.err.println("    jmap [option] <pid>");
            System.err.println("        (to connect to running process)");
            System.err.println("    jmap [option] <executable <core>");
            System.err.println("        (to connect to a core file)");
            System.err.println("    jmap [option] [server_id@]<remote server IP or hostname>");
            System.err.println("        (to connect to remote debug server)");
            System.err.println("");
            System.err.println("where <option> is one of:");
            System.err.println("    <none>               to print same info as Solaris pmap");
            System.err.println("    -heap                to print java heap summary");
            System.err.println("    -histo[:live]        to print histogram of java object heap; if the \"live\"");
            System.err.println("                         suboption is specified, only count live objects");
            System.err.println("    -clstats             to print class loader statistics");
            System.err.println("    -finalizerinfo       to print information on objects awaiting finalization");
            System.err.println("    -dump:<dump-options> to dump java heap in hprof binary format");
            System.err.println("                         dump-options:");
            System.err.println("                           live         dump only live objects; if not specified,");
            System.err.println("                                        all objects in the heap are dumped.");
            System.err.println("                           format=b     binary format");
            System.err.println("                           file=<file>  dump heap to <file>");
            System.err.println("                         Example: jmap -dump:live,format=b,file=heap.bin <pid>");
            System.err.println("    -F                   force. Use with -dump:<dump-options> <pid> or -histo");
            System.err.println("                         to force a heap dump or histogram when <pid> does not");
            System.err.println("                         respond. The \"live\" suboption is not supported");
            System.err.println("                         in this mode.");
            System.err.println("    -h | -help           to print this help message");
            System.err.println("    -J<flag>             to pass <flag> directly to the runtime system");
        } else {
            System.err.println("    jmap -histo <pid>");
            System.err.println("      (to connect to running process and print histogram of java object heap");
            System.err.println("    jmap -dump:<dump-options> <pid>");
            System.err.println("      (to connect to running process and dump java heap)");
            System.err.println("");
            System.err.println("    dump-options:");
            System.err.println("      format=b     binary default");
            System.err.println("      file=<file>  dump heap to <file>");
            System.err.println("");
            System.err.println("    Example:       jmap -dump:format=b,file=heap.bin <pid>");
        }
        System.exit(i);
    }
}
