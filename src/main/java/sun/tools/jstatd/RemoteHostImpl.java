package sun.tools.jstatd;

import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.VmIdentifier;
import sun.jvmstat.monitor.event.HostEvent;
import sun.jvmstat.monitor.event.HostListener;
import sun.jvmstat.monitor.event.VmStatusChangeEvent;
import sun.jvmstat.monitor.remote.BufferedMonitoredVm;
import sun.jvmstat.monitor.remote.RemoteHost;
import sun.jvmstat.monitor.remote.RemoteVm;

/* loaded from: target.jar:sun/tools/jstatd/RemoteHostImpl.class */
public class RemoteHostImpl implements RemoteHost, HostListener {
    private MonitoredHost monitoredHost;
    private Set<Integer> activeVms;

    public RemoteHostImpl() throws MonitorException {
        try {
            this.monitoredHost = MonitoredHost.getMonitoredHost("localhost");
        } catch (URISyntaxException e) {
        }
        this.activeVms = this.monitoredHost.activeVms();
        this.monitoredHost.addHostListener(this);
    }

    @Override // sun.jvmstat.monitor.remote.RemoteHost
    public RemoteVm attachVm(int i, String str) throws RemoteException, MonitorException {
        new Integer(i);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("local://").append(i).append("@localhost");
        if (str != null) {
            stringBuffer.append("?mode=" + str);
        }
        String stringBuffer2 = stringBuffer.toString();
        try {
            return (RemoteVm) UnicastRemoteObject.exportObject(new RemoteVmImpl((BufferedMonitoredVm) this.monitoredHost.getMonitoredVm(new VmIdentifier(stringBuffer2))), 0);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Malformed VmIdentifier URI: " + stringBuffer2, e);
        }
    }

    @Override // sun.jvmstat.monitor.remote.RemoteHost
    public void detachVm(RemoteVm remoteVm) throws RemoteException {
        remoteVm.detach();
    }

    @Override // sun.jvmstat.monitor.remote.RemoteHost
    public int[] activeVms() throws MonitorException {
        Object[] array = this.monitoredHost.activeVms().toArray();
        int[] iArr = new int[array.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = ((Integer) array[i]).intValue();
        }
        return iArr;
    }

    @Override // sun.jvmstat.monitor.event.HostListener
    public void vmStatusChanged(VmStatusChangeEvent vmStatusChangeEvent) {
        synchronized (this.activeVms) {
            this.activeVms.retainAll(vmStatusChangeEvent.getActive());
        }
    }

    @Override // sun.jvmstat.monitor.event.HostListener
    public void disconnected(HostEvent hostEvent) {
    }
}
