package sun.jvmstat.perfdata.monitor;

import java.nio.LongBuffer;
import sun.jvmstat.monitor.AbstractMonitor;
import sun.jvmstat.monitor.LongMonitor;
import sun.jvmstat.monitor.Units;
import sun.jvmstat.monitor.Variability;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/PerfLongMonitor.class */
public class PerfLongMonitor extends AbstractMonitor implements LongMonitor {
    LongBuffer lb;

    public PerfLongMonitor(String str, Units units, Variability variability, boolean z, LongBuffer longBuffer) {
        super(str, units, variability, z);
        this.lb = longBuffer;
    }

    @Override // sun.jvmstat.monitor.AbstractMonitor, sun.jvmstat.monitor.Monitor
    public Object getValue() {
        return new Long(this.lb.get(0));
    }

    @Override // sun.jvmstat.monitor.LongMonitor
    public long longValue() {
        return this.lb.get(0);
    }
}
