package sun.jvmstat.monitor.event;

import java.util.EventObject;
import sun.jvmstat.monitor.MonitoredHost;

/* loaded from: target.jar:sun/jvmstat/monitor/event/HostEvent.class */
public class HostEvent extends EventObject {
    public HostEvent(MonitoredHost monitoredHost) {
        super(monitoredHost);
    }

    public MonitoredHost getMonitoredHost() {
        return (MonitoredHost) this.source;
    }
}
