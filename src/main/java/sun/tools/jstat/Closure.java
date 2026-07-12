package sun.tools.jstat;

import sun.jvmstat.monitor.MonitorException;

/* loaded from: target.jar:sun/tools/jstat/Closure.class */
interface Closure {
    void visit(Object obj, boolean z) throws MonitorException;
}
