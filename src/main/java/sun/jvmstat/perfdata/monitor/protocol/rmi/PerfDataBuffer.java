package sun.jvmstat.perfdata.monitor.protocol.rmi;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.remote.RemoteVm;
import sun.jvmstat.perfdata.monitor.AbstractPerfDataBuffer;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/protocol/rmi/PerfDataBuffer.class */
public class PerfDataBuffer extends AbstractPerfDataBuffer {
    private RemoteVm rvm;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !PerfDataBuffer.class.desiredAssertionStatus();
    }

    public PerfDataBuffer(RemoteVm remoteVm, int i) throws MonitorException {
        this.rvm = remoteVm;
        try {
            ByteBuffer allocate = ByteBuffer.allocate(remoteVm.getCapacity());
            sample(allocate);
            createPerfDataBuffer(allocate, i);
        } catch (RemoteException e) {
            throw new MonitorException("Could not read data for remote JVM " + i, e);
        }
    }

    public void sample(ByteBuffer byteBuffer) throws RemoteException {
        if (!$assertionsDisabled && byteBuffer == null) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && this.rvm == null) {
            throw new AssertionError();
        }
        synchronized (byteBuffer) {
            byteBuffer.clear();
            byteBuffer.put(this.rvm.getBytes());
        }
    }
}
