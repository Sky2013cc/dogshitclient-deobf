package sun.jvmstat.perfdata.monitor;

import java.nio.ByteBuffer;
import sun.jvmstat.monitor.AbstractMonitor;
import sun.jvmstat.monitor.ByteArrayMonitor;
import sun.jvmstat.monitor.Units;
import sun.jvmstat.monitor.Variability;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/PerfByteArrayMonitor.class */
public class PerfByteArrayMonitor extends AbstractMonitor implements ByteArrayMonitor {
    ByteBuffer bb;

    public PerfByteArrayMonitor(String str, Units units, Variability variability, boolean z, ByteBuffer byteBuffer, int i) {
        super(str, units, variability, z, i);
        this.bb = byteBuffer;
    }

    @Override // sun.jvmstat.monitor.AbstractMonitor, sun.jvmstat.monitor.Monitor
    public Object getValue() {
        return byteArrayValue();
    }

    @Override // sun.jvmstat.monitor.ByteArrayMonitor
    public byte[] byteArrayValue() {
        this.bb.position(0);
        byte[] bArr = new byte[this.bb.limit()];
        this.bb.get(bArr);
        return bArr;
    }

    @Override // sun.jvmstat.monitor.ByteArrayMonitor
    public byte byteAt(int i) {
        this.bb.position(i);
        return this.bb.get();
    }

    public int getMaximumLength() {
        return this.bb.limit();
    }
}
