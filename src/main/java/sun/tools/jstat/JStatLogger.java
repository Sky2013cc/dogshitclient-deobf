package sun.tools.jstat;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.jvmstat.monitor.Monitor;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.StringMonitor;

/* loaded from: target.jar:sun/tools/jstat/JStatLogger.class */
public class JStatLogger {
    private MonitoredVm monitoredVm;
    private volatile boolean active = true;

    public JStatLogger(MonitoredVm monitoredVm) {
        this.monitoredVm = monitoredVm;
    }

    public void printNames(String str, Comparator<Monitor> comparator, boolean z, PrintStream printStream) throws MonitorException, PatternSyntaxException {
        List<Monitor> findByPattern = this.monitoredVm.findByPattern(str);
        Collections.sort(findByPattern, comparator);
        for (Monitor monitor : findByPattern) {
            if (monitor.isSupported() || z) {
                printStream.println(monitor.getName());
            }
        }
    }

    public void printSnapShot(String str, Comparator<Monitor> comparator, boolean z, boolean z2, PrintStream printStream) throws MonitorException, PatternSyntaxException {
        List<Monitor> findByPattern = this.monitoredVm.findByPattern(str);
        Collections.sort(findByPattern, comparator);
        printList(findByPattern, z, z2, printStream);
    }

    public void printList(List<Monitor> list, boolean z, boolean z2, PrintStream printStream) throws MonitorException {
        for (Monitor monitor : list) {
            if (monitor.isSupported() || z2) {
                StringBuilder sb = new StringBuilder();
                sb.append(monitor.getName()).append("=");
                if (monitor instanceof StringMonitor) {
                    sb.append(OperatorName.SHOW_TEXT_LINE_AND_SPACE).append(monitor.getValue()).append(OperatorName.SHOW_TEXT_LINE_AND_SPACE);
                } else {
                    sb.append(monitor.getValue());
                }
                if (z) {
                    sb.append(" ").append(monitor.getUnits());
                    sb.append(" ").append(monitor.getVariability());
                    sb.append(" ").append(monitor.isSupported() ? "Supported" : "Unsupported");
                }
                printStream.println(sb);
            }
        }
    }

    public void stopLogging() {
        this.active = false;
    }

    public void logSamples(OutputFormatter outputFormatter, int i, int i2, int i3, PrintStream printStream) throws MonitorException {
        long j = 0;
        int i4 = 0;
        int i5 = i;
        if (i5 == 0) {
            printStream.println(outputFormatter.getHeader());
            i5 = -1;
        }
        while (this.active) {
            if (i5 > 0) {
                i4--;
                if (i4 <= 0) {
                    i4 = i5;
                    printStream.println(outputFormatter.getHeader());
                }
            }
            printStream.println(outputFormatter.getRow());
            if (i3 > 0) {
                long j2 = j + 1;
                j = j2;
                if (j2 >= i3) {
                    return;
                }
            }
            try {
                Thread.sleep(i2);
            } catch (Exception e) {
            }
        }
    }
}
