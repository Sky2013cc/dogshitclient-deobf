package sun.tools.jstat;

import sun.jvmstat.monitor.MonitorException;

/* loaded from: target.jar:sun/tools/jstat/OutputFormatter.class */
public interface OutputFormatter {
    String getHeader() throws MonitorException;

    String getRow() throws MonitorException;
}
