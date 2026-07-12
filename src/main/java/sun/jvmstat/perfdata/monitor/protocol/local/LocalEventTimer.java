package sun.jvmstat.perfdata.monitor.protocol.local;

import java.util.Timer;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/protocol/local/LocalEventTimer.class */
public class LocalEventTimer extends Timer {
    private static LocalEventTimer instance;

    private LocalEventTimer() {
        super(true);
    }

    public static synchronized LocalEventTimer getInstance() {
        if (instance == null) {
            instance = new LocalEventTimer();
        }
        return instance;
    }
}
