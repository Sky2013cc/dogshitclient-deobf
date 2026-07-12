package sun.tools.jstatd;

import sun.jvmstat.monitor.remote.BufferedMonitoredVm;
import sun.jvmstat.monitor.remote.RemoteVm;

/* loaded from: target.jar:sun/tools/jstatd/RemoteVmImpl.class */
public class RemoteVmImpl implements RemoteVm {
    private BufferedMonitoredVm mvm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RemoteVmImpl(BufferedMonitoredVm bufferedMonitoredVm) {
        this.mvm = bufferedMonitoredVm;
    }

    @Override // sun.jvmstat.monitor.remote.RemoteVm
    public byte[] getBytes() {
        return this.mvm.getBytes();
    }

    @Override // sun.jvmstat.monitor.remote.RemoteVm
    public int getCapacity() {
        return this.mvm.getCapacity();
    }

    @Override // sun.jvmstat.monitor.remote.RemoteVm
    public void detach() {
        this.mvm.detach();
    }

    @Override // sun.jvmstat.monitor.remote.RemoteVm
    public int getLocalVmId() {
        return this.mvm.getVmIdentifier().getLocalVmId();
    }
}
