package sun.jvmstat.monitor.event;

import java.util.EventObject;
import sun.jvmstat.monitor.MonitoredVm;

/* loaded from: target.jar:sun/jvmstat/monitor/event/VmEvent.class */
public class VmEvent extends EventObject {
    public VmEvent(MonitoredVm monitoredVm) {
        super(monitoredVm);
    }

    public MonitoredVm getMonitoredVm() {
        return (MonitoredVm) this.source;
    }
}
