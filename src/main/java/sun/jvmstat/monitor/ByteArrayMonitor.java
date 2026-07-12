package sun.jvmstat.monitor;

/* loaded from: target.jar:sun/jvmstat/monitor/ByteArrayMonitor.class */
public interface ByteArrayMonitor extends Monitor {
    byte[] byteArrayValue();

    byte byteAt(int i);
}
