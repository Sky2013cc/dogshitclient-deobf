package sun.jvmstat.monitor;

import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:sun/jvmstat/monitor/AbstractMonitor.class */
public abstract class AbstractMonitor implements Monitor {
    protected String name;
    protected Units units;
    protected Variability variability;
    protected int vectorLength;
    protected boolean supported;

    @Override // sun.jvmstat.monitor.Monitor
    public abstract Object getValue();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMonitor(String str, Units units, Variability variability, boolean z, int i) {
        this.name = str;
        this.units = units;
        this.variability = variability;
        this.vectorLength = i;
        this.supported = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMonitor(String str, Units units, Variability variability, boolean z) {
        this(str, units, variability, z, 0);
    }

    @Override // sun.jvmstat.monitor.Monitor
    public String getName() {
        return this.name;
    }

    @Override // sun.jvmstat.monitor.Monitor
    public String getBaseName() {
        return this.name.substring(this.name.lastIndexOf(Constants.NAME_SEPARATOR) + 1);
    }

    @Override // sun.jvmstat.monitor.Monitor
    public Units getUnits() {
        return this.units;
    }

    @Override // sun.jvmstat.monitor.Monitor
    public Variability getVariability() {
        return this.variability;
    }

    @Override // sun.jvmstat.monitor.Monitor
    public boolean isVector() {
        return this.vectorLength > 0;
    }

    @Override // sun.jvmstat.monitor.Monitor
    public int getVectorLength() {
        return this.vectorLength;
    }

    @Override // sun.jvmstat.monitor.Monitor
    public boolean isSupported() {
        return this.supported;
    }
}
