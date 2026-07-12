package sun.jvmstat.perfdata.monitor;

import java.util.List;
import sun.jvmstat.monitor.Monitor;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.VmIdentifier;
import sun.jvmstat.monitor.event.VmListener;
import sun.jvmstat.monitor.remote.BufferedMonitoredVm;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/AbstractMonitoredVm.class */
public abstract class AbstractMonitoredVm implements BufferedMonitoredVm {
    protected VmIdentifier vmid;
    protected AbstractPerfDataBuffer pdb;
    protected int interval;

    @Override // sun.jvmstat.monitor.MonitoredVm
    public abstract void addVmListener(VmListener vmListener);

    @Override // sun.jvmstat.monitor.MonitoredVm
    public abstract void removeVmListener(VmListener vmListener);

    public AbstractMonitoredVm(VmIdentifier vmIdentifier, int i) throws MonitorException {
        this.vmid = vmIdentifier;
        this.interval = i;
    }

    @Override // sun.jvmstat.monitor.MonitoredVm
    public VmIdentifier getVmIdentifier() {
        return this.vmid;
    }

    @Override // sun.jvmstat.monitor.MonitoredVm
    public Monitor findByName(String str) throws MonitorException {
        return this.pdb.findByName(str);
    }

    @Override // sun.jvmstat.monitor.MonitoredVm
    public List<Monitor> findByPattern(String str) throws MonitorException {
        return this.pdb.findByPattern(str);
    }

    @Override // sun.jvmstat.monitor.MonitoredVm
    public void detach() {
    }

    @Override // sun.jvmstat.monitor.MonitoredVm
    public void setInterval(int i) {
        this.interval = i;
    }

    @Override // sun.jvmstat.monitor.MonitoredVm
    public int getInterval() {
        return this.interval;
    }

    @Override // sun.jvmstat.monitor.MonitoredVm
    public void setLastException(Exception exc) {
    }

    @Override // sun.jvmstat.monitor.MonitoredVm
    public Exception getLastException() {
        return null;
    }

    @Override // sun.jvmstat.monitor.MonitoredVm
    public void clearLastException() {
    }

    @Override // sun.jvmstat.monitor.MonitoredVm
    public boolean isErrored() {
        return false;
    }

    public MonitorStatus getMonitorStatus() throws MonitorException {
        return this.pdb.getMonitorStatus();
    }

    @Override // sun.jvmstat.monitor.remote.BufferedMonitoredVm
    public byte[] getBytes() {
        return this.pdb.getBytes();
    }

    @Override // sun.jvmstat.monitor.remote.BufferedMonitoredVm
    public int getCapacity() {
        return this.pdb.getCapacity();
    }
}
