package sun.jvmstat.monitor.event;

import java.util.EventListener;

/* loaded from: target.jar:sun/jvmstat/monitor/event/VmListener.class */
public interface VmListener extends EventListener {
    void monitorStatusChanged(MonitorStatusChangeEvent monitorStatusChangeEvent);

    void monitorsUpdated(VmEvent vmEvent);

    void disconnected(VmEvent vmEvent);
}
