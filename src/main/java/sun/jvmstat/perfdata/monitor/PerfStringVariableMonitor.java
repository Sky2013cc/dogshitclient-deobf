package sun.jvmstat.perfdata.monitor;

import java.nio.ByteBuffer;
import sun.jvmstat.monitor.Variability;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/PerfStringVariableMonitor.class */
public class PerfStringVariableMonitor extends PerfStringMonitor {
    public PerfStringVariableMonitor(String str, boolean z, ByteBuffer byteBuffer) {
        this(str, z, byteBuffer, byteBuffer.limit());
    }

    public PerfStringVariableMonitor(String str, boolean z, ByteBuffer byteBuffer, int i) {
        super(str, Variability.VARIABLE, z, byteBuffer, i + 1);
    }
}
