package sun.jvmstat.monitor;

/* loaded from: target.jar:sun/jvmstat/monitor/Monitor.class */
public interface Monitor {
    String getName();

    String getBaseName();

    Units getUnits();

    Variability getVariability();

    boolean isVector();

    int getVectorLength();

    boolean isSupported();

    Object getValue();
}
