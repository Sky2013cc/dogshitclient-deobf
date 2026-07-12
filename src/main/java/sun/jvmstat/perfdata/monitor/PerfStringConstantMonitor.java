package sun.jvmstat.perfdata.monitor;

import java.nio.ByteBuffer;
import sun.jvmstat.monitor.Variability;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/PerfStringConstantMonitor.class */
public class PerfStringConstantMonitor extends PerfStringMonitor {
    String data;

    public PerfStringConstantMonitor(String str, boolean z, ByteBuffer byteBuffer) {
        super(str, Variability.CONSTANT, z, byteBuffer);
        this.data = super.stringValue();
    }

    @Override // sun.jvmstat.perfdata.monitor.PerfStringMonitor, sun.jvmstat.perfdata.monitor.PerfByteArrayMonitor, sun.jvmstat.monitor.AbstractMonitor, sun.jvmstat.monitor.Monitor
    public Object getValue() {
        return this.data;
    }

    @Override // sun.jvmstat.perfdata.monitor.PerfStringMonitor, sun.jvmstat.monitor.StringMonitor
    public String stringValue() {
        return this.data;
    }
}
