package sun.jvmstat.monitor.event;

import java.util.Set;
import sun.jvmstat.monitor.MonitoredHost;

/* loaded from: target.jar:sun/jvmstat/monitor/event/VmStatusChangeEvent.class */
public class VmStatusChangeEvent extends HostEvent {
    protected Set active;
    protected Set started;
    protected Set terminated;

    public VmStatusChangeEvent(MonitoredHost monitoredHost, Set set, Set set2, Set set3) {
        super(monitoredHost);
        this.active = set;
        this.started = set2;
        this.terminated = set3;
    }

    public Set getActive() {
        return this.active;
    }

    public Set getStarted() {
        return this.started;
    }

    public Set getTerminated() {
        return this.terminated;
    }
}
