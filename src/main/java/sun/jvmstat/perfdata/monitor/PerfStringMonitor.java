package sun.jvmstat.perfdata.monitor;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import sun.jvmstat.monitor.StringMonitor;
import sun.jvmstat.monitor.Units;
import sun.jvmstat.monitor.Variability;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/PerfStringMonitor.class */
public class PerfStringMonitor extends PerfByteArrayMonitor implements StringMonitor {
    private static Charset defaultCharset = Charset.defaultCharset();

    public PerfStringMonitor(String str, Variability variability, boolean z, ByteBuffer byteBuffer) {
        this(str, variability, z, byteBuffer, byteBuffer.limit());
    }

    public PerfStringMonitor(String str, Variability variability, boolean z, ByteBuffer byteBuffer, int i) {
        super(str, Units.STRING, variability, z, byteBuffer, i);
    }

    @Override // sun.jvmstat.perfdata.monitor.PerfByteArrayMonitor, sun.jvmstat.monitor.AbstractMonitor, sun.jvmstat.monitor.Monitor
    public Object getValue() {
        return stringValue();
    }

    public String stringValue() {
        byte[] byteArrayValue = byteArrayValue();
        if (byteArrayValue == null || byteArrayValue.length <= 1 || byteArrayValue[0] == 0) {
            return "";
        }
        int i = 0;
        while (i < byteArrayValue.length && byteArrayValue[i] != 0) {
            i++;
        }
        return new String(byteArrayValue, 0, i, defaultCharset);
    }
}
