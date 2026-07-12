package sun.tools.jstat;

import sun.jvmstat.monitor.MonitorException;

/* loaded from: target.jar:sun/tools/jstat/ExpressionEvaluator.class */
interface ExpressionEvaluator {
    Object evaluate(Expression expression) throws MonitorException;
}
