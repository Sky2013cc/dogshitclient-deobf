package sun.jvmstat.perfdata.monitor;

import java.nio.IntBuffer;
import sun.jvmstat.monitor.AbstractMonitor;
import sun.jvmstat.monitor.IntegerMonitor;
import sun.jvmstat.monitor.Units;
import sun.jvmstat.monitor.Variability;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/PerfIntegerMonitor.class */
public class PerfIntegerMonitor extends AbstractMonitor implements IntegerMonitor {
    IntBuffer ib;

    public PerfIntegerMonitor(String str, Units units, Variability variability, boolean z, IntBuffer intBuffer) {
        super(str, units, variability, z);
        this.ib = intBuffer;
    }

    @Override // sun.jvmstat.monitor.AbstractMonitor, sun.jvmstat.monitor.Monitor
    public Object getValue() {
        return new Integer(this.ib.get(0));
    }

    @Override // sun.jvmstat.monitor.IntegerMonitor
    public int intValue() {
        return this.ib.get(0);
    }
}
