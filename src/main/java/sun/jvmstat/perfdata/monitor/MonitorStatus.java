package sun.jvmstat.perfdata.monitor;

import java.util.List;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/MonitorStatus.class */
public class MonitorStatus {
    protected List inserted;
    protected List removed;

    public MonitorStatus(List list, List list2) {
        this.inserted = list;
        this.removed = list2;
    }

    public List getInserted() {
        return this.inserted;
    }

    public List getRemoved() {
        return this.removed;
    }
}
