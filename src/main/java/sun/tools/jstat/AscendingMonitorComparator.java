package sun.tools.jstat;

import java.util.Comparator;
import sun.jvmstat.monitor.Monitor;

/* loaded from: target.jar:sun/tools/jstat/AscendingMonitorComparator.class */
class AscendingMonitorComparator implements Comparator<Monitor> {
    @Override // java.util.Comparator
    public int compare(Monitor monitor, Monitor monitor2) {
        return monitor.getName().compareTo(monitor2.getName());
    }
}
