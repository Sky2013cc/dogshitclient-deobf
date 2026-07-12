package sun.tools.jstat;

import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.jvmstat.monitor.Monitor;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.StringMonitor;

/* loaded from: target.jar:sun/tools/jstat/RawOutputFormatter.class */
public class RawOutputFormatter implements OutputFormatter {
    private List logged;
    private String header;
    private boolean printStrings;

    public RawOutputFormatter(List list, boolean z) {
        this.logged = list;
        this.printStrings = z;
    }

    @Override // sun.tools.jstat.OutputFormatter
    public String getHeader() throws MonitorException {
        if (this.header == null) {
            StringBuilder sb = new StringBuilder();
            Iterator it = this.logged.iterator();
            while (it.hasNext()) {
                sb.append(((Monitor) it.next()).getName() + " ");
            }
            this.header = sb.toString();
        }
        return this.header;
    }

    @Override // sun.tools.jstat.OutputFormatter
    public String getRow() throws MonitorException {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Monitor monitor : this.logged) {
            int i2 = i;
            i++;
            if (i2 > 0) {
                sb.append(" ");
            }
            if (this.printStrings && (monitor instanceof StringMonitor)) {
                sb.append(OperatorName.SHOW_TEXT_LINE_AND_SPACE).append(monitor.getValue()).append(OperatorName.SHOW_TEXT_LINE_AND_SPACE);
            } else {
                sb.append(monitor.getValue());
            }
        }
        return sb.toString();
    }
}
