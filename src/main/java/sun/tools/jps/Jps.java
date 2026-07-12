package sun.tools.jps;

import java.net.URISyntaxException;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;

/* loaded from: target.jar:sun/tools/jps/Jps.class */
public class Jps {
    private static Arguments arguments;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !Jps.class.desiredAssertionStatus();
    }

    public static void main(String[] strArr) {
        String jvmFlags;
        String jvmArgs;
        String mainArgs;
        try {
            arguments = new Arguments(strArr);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            Arguments.printUsage(System.err);
            System.exit(1);
        }
        if (arguments.isHelp()) {
            Arguments.printUsage(System.err);
            System.exit(0);
        }
        try {
            MonitoredHost monitoredHost = MonitoredHost.getMonitoredHost(arguments.hostId());
            for (Integer num : monitoredHost.activeVms()) {
                StringBuilder sb = new StringBuilder();
                URISyntaxException uRISyntaxException = null;
                int intValue = num.intValue();
                sb.append(String.valueOf(intValue));
                if (arguments.isQuiet()) {
                    System.out.println(sb);
                } else {
                    String str = null;
                    try {
                        try {
                            MonitoredVm monitoredVm = monitoredHost.getMonitoredVm(new VmIdentifier("//" + intValue + "?mode=r"), 0);
                            str = " -- main class information unavailable";
                            sb.append(" " + MonitoredVmUtil.mainClass(monitoredVm, arguments.showLongPaths()));
                            if (arguments.showMainArgs() && (mainArgs = MonitoredVmUtil.mainArgs(monitoredVm)) != null && mainArgs.length() > 0) {
                                sb.append(" " + mainArgs);
                            }
                            if (arguments.showVmArgs() && (jvmArgs = MonitoredVmUtil.jvmArgs(monitoredVm)) != null && jvmArgs.length() > 0) {
                                sb.append(" " + jvmArgs);
                            }
                            if (arguments.showVmFlags() && (jvmFlags = MonitoredVmUtil.jvmFlags(monitoredVm)) != null && jvmFlags.length() > 0) {
                                sb.append(" " + jvmFlags);
                            }
                            monitoredHost.detach(monitoredVm);
                            System.out.println(sb);
                        } catch (Throwable th) {
                            if (str == null) {
                                throw th;
                            }
                            sb.append(str);
                            if (arguments.isDebug() && uRISyntaxException != null && uRISyntaxException.getMessage() != null) {
                                sb.append("\n\t");
                                sb.append(uRISyntaxException.getMessage());
                            }
                            System.out.println(sb);
                            if (arguments.printStackTrace()) {
                                uRISyntaxException.printStackTrace();
                            }
                        }
                    } catch (URISyntaxException e2) {
                        uRISyntaxException = e2;
                        if (!$assertionsDisabled) {
                            throw new AssertionError();
                            break;
                        }
                        if (str != null) {
                            sb.append(str);
                            if (arguments.isDebug() && uRISyntaxException != null && uRISyntaxException.getMessage() != null) {
                                sb.append("\n\t");
                                sb.append(uRISyntaxException.getMessage());
                            }
                            System.out.println(sb);
                            if (arguments.printStackTrace()) {
                                uRISyntaxException.printStackTrace();
                            }
                        }
                    } catch (Exception e3) {
                        if (str != null) {
                            sb.append(str);
                            if (arguments.isDebug() && e3 != null && e3.getMessage() != null) {
                                sb.append("\n\t");
                                sb.append(e3.getMessage());
                            }
                            System.out.println(sb);
                            if (arguments.printStackTrace()) {
                                e3.printStackTrace();
                            }
                        }
                    }
                    if (0 != 0) {
                        sb.append((String) null);
                        if (arguments.isDebug() && 0 != 0 && uRISyntaxException.getMessage() != null) {
                            sb.append("\n\t");
                            sb.append(uRISyntaxException.getMessage());
                        }
                        System.out.println(sb);
                        if (arguments.printStackTrace()) {
                            uRISyntaxException.printStackTrace();
                        }
                    }
                }
            }
        } catch (MonitorException e4) {
            if (e4.getMessage() != null) {
                System.err.println(e4.getMessage());
            } else {
                Throwable cause = e4.getCause();
                if (cause == null || cause.getMessage() == null) {
                    e4.printStackTrace();
                } else {
                    System.err.println(cause.getMessage());
                }
            }
            System.exit(1);
        }
    }
}
