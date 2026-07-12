package sun.jvmstat.perfdata.monitor.protocol.rmi;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.remote.RemoteHost;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/protocol/rmi/RemoteVmManager.class */
public class RemoteVmManager {
    private RemoteHost remoteHost;
    private String user;

    public RemoteVmManager(RemoteHost remoteHost) {
        this(remoteHost, null);
    }

    public RemoteVmManager(RemoteHost remoteHost, String str) {
        this.user = str;
        this.remoteHost = remoteHost;
    }

    public Set<Integer> activeVms() throws MonitorException {
        try {
            int[] activeVms = this.remoteHost.activeVms();
            HashSet hashSet = new HashSet(activeVms.length);
            for (int i : activeVms) {
                hashSet.add(new Integer(i));
            }
            return hashSet;
        } catch (RemoteException e) {
            throw new MonitorException("Error communicating with remote host: " + e.getMessage(), e);
        }
    }
}
