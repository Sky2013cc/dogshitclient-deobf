package sun.jvmstat.monitor;

import java.util.List;
import sun.jvmstat.monitor.event.VmListener;

/* loaded from: target.jar:sun/jvmstat/monitor/MonitoredVm.class */
public interface MonitoredVm {
    VmIdentifier getVmIdentifier();

    Monitor findByName(String str) throws MonitorException;

    List<Monitor> findByPattern(String str) throws MonitorException;

    void detach();

    void setInterval(int i);

    int getInterval();

    void setLastException(Exception exc);

    Exception getLastException();

    void clearLastException();

    boolean isErrored();

    void addVmListener(VmListener vmListener) throws MonitorException;

    void removeVmListener(VmListener vmListener) throws MonitorException;
}
