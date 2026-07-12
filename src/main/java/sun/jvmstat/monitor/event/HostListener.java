package sun.jvmstat.monitor.event;

import java.util.EventListener;

/* loaded from: target.jar:sun/jvmstat/monitor/event/HostListener.class */
public interface HostListener extends EventListener {
    void vmStatusChanged(VmStatusChangeEvent vmStatusChangeEvent);

    void disconnected(HostEvent hostEvent);
}
