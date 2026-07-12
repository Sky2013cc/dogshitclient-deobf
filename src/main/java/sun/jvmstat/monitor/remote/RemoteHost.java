package sun.jvmstat.monitor.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import sun.jvmstat.monitor.MonitorException;

/* loaded from: target.jar:sun/jvmstat/monitor/remote/RemoteHost.class */
public interface RemoteHost extends Remote {
    RemoteVm attachVm(int i, String str) throws RemoteException, MonitorException;

    void detachVm(RemoteVm remoteVm) throws RemoteException, MonitorException;

    int[] activeVms() throws RemoteException, MonitorException;
}
