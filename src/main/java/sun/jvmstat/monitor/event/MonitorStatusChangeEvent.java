package sun.jvmstat.monitor.event;

import java.util.List;
import sun.jvmstat.monitor.MonitoredVm;

/* loaded from: target.jar:sun/jvmstat/monitor/event/MonitorStatusChangeEvent.class */
public class MonitorStatusChangeEvent extends VmEvent {
    protected List inserted;
    protected List removed;

    public MonitorStatusChangeEvent(MonitoredVm monitoredVm, List list, List list2) {
        super(monitoredVm);
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
