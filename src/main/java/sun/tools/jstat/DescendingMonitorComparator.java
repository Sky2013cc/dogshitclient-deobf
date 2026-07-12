package sun.tools.jstat;

import java.util.Comparator;
import sun.jvmstat.monitor.Monitor;

/* loaded from: target.jar:sun/tools/jstat/DescendingMonitorComparator.class */
class DescendingMonitorComparator implements Comparator<Monitor> {
    @Override // java.util.Comparator
    public int compare(Monitor monitor, Monitor monitor2) {
        return monitor2.getName().compareTo(monitor.getName());
    }
}
