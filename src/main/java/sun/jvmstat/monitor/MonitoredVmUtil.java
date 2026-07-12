package sun.jvmstat.monitor;

import org.apache.fontbox.ttf.OpenTypeScript;

/* loaded from: target.jar:sun/jvmstat/monitor/MonitoredVmUtil.class */
public class MonitoredVmUtil {
    private static int IS_ATTACHABLE = 0;
    private static int IS_KERNEL_VM = 1;

    private MonitoredVmUtil() {
    }

    public static String vmVersion(MonitoredVm monitoredVm) throws MonitorException {
        StringMonitor stringMonitor = (StringMonitor) monitoredVm.findByName("java.property.java.vm.version");
        return stringMonitor == null ? OpenTypeScript.UNKNOWN : stringMonitor.stringValue();
    }

    public static String commandLine(MonitoredVm monitoredVm) throws MonitorException {
        StringMonitor stringMonitor = (StringMonitor) monitoredVm.findByName("sun.rt.javaCommand");
        return stringMonitor == null ? OpenTypeScript.UNKNOWN : stringMonitor.stringValue();
    }

    public static String mainArgs(MonitoredVm monitoredVm) throws MonitorException {
        String commandLine = commandLine(monitoredVm);
        int indexOf = commandLine.indexOf(32);
        if (indexOf > 0) {
            return commandLine.substring(indexOf + 1);
        }
        if (commandLine.compareTo(OpenTypeScript.UNKNOWN) == 0) {
            return commandLine;
        }
        return null;
    }

    public static String mainClass(MonitoredVm monitoredVm, boolean z) throws MonitorException {
        String commandLine = commandLine(monitoredVm);
        String str = commandLine;
        int indexOf = commandLine.indexOf(32);
        if (indexOf > 0) {
            str = commandLine.substring(0, indexOf);
        }
        if (!z) {
            int lastIndexOf = str.lastIndexOf(47);
            if (lastIndexOf > 0) {
                return str.substring(lastIndexOf + 1);
            }
            int lastIndexOf2 = str.lastIndexOf(92);
            if (lastIndexOf2 > 0) {
                return str.substring(lastIndexOf2 + 1);
            }
            int lastIndexOf3 = str.lastIndexOf(46);
            if (lastIndexOf3 > 0) {
                return str.substring(lastIndexOf3 + 1);
            }
        }
        return str;
    }

    public static String jvmArgs(MonitoredVm monitoredVm) throws MonitorException {
        StringMonitor stringMonitor = (StringMonitor) monitoredVm.findByName("java.rt.vmArgs");
        return stringMonitor == null ? OpenTypeScript.UNKNOWN : stringMonitor.stringValue();
    }

    public static String jvmFlags(MonitoredVm monitoredVm) throws MonitorException {
        StringMonitor stringMonitor = (StringMonitor) monitoredVm.findByName("java.rt.vmFlags");
        return stringMonitor == null ? OpenTypeScript.UNKNOWN : stringMonitor.stringValue();
    }

    public static boolean isAttachable(MonitoredVm monitoredVm) throws MonitorException {
        StringMonitor stringMonitor = (StringMonitor) monitoredVm.findByName("sun.rt.jvmCapabilities");
        return stringMonitor != null && stringMonitor.stringValue().charAt(IS_ATTACHABLE) == '1';
    }
}
