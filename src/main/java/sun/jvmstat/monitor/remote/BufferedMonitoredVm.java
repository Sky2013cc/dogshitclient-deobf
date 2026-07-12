package sun.jvmstat.monitor.remote;

import sun.jvmstat.monitor.MonitoredVm;

/* loaded from: target.jar:sun/jvmstat/monitor/remote/BufferedMonitoredVm.class */
public interface BufferedMonitoredVm extends MonitoredVm {
    byte[] getBytes();

    int getCapacity();
}
