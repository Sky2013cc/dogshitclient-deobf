package sun.jvmstat.perfdata.monitor;

import java.util.Timer;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/CountedTimerTaskUtils.class */
public class CountedTimerTaskUtils {
    private static final boolean DEBUG = false;

    public static void reschedule(Timer timer, CountedTimerTask countedTimerTask, CountedTimerTask countedTimerTask2, int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis() - countedTimerTask.scheduledExecutionTime();
        long j = 0;
        if (countedTimerTask.executionCount() > 0) {
            long j2 = i2 - currentTimeMillis;
            j = j2 >= 0 ? j2 : 0L;
        }
        timer.schedule(countedTimerTask2, j, i2);
    }
}
