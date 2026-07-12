package sun.jvmstat.monitor;

/* loaded from: target.jar:sun/jvmstat/monitor/MonitorException.class */
public class MonitorException extends Exception {
    public MonitorException() {
    }

    public MonitorException(String str) {
        super(str);
    }

    public MonitorException(String str, Throwable th) {
        super(str, th);
    }

    public MonitorException(Throwable th) {
        super(th);
    }
}
