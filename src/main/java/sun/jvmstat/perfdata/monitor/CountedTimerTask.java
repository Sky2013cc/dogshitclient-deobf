package sun.jvmstat.perfdata.monitor;

import java.util.TimerTask;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/CountedTimerTask.class */
public class CountedTimerTask extends TimerTask {
    volatile long executionCount;

    public long executionCount() {
        return this.executionCount;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.executionCount++;
    }
}
