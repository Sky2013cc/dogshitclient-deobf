package sun.tools.jstat;

import sun.jvmstat.monitor.MonitorException;

/* loaded from: target.jar:sun/tools/jstat/HeaderClosure.class */
public class HeaderClosure implements Closure {
    private static final char ALIGN_CHAR = '^';
    private StringBuilder header = new StringBuilder();

    @Override // sun.tools.jstat.Closure
    public void visit(Object obj, boolean z) throws MonitorException {
        if (!(obj instanceof ColumnFormat)) {
            return;
        }
        ColumnFormat columnFormat = (ColumnFormat) obj;
        String header = columnFormat.getHeader();
        if (header.indexOf(94) >= 0) {
            int length = header.length();
            if (header.charAt(0) == '^' && header.charAt(length - 1) == '^') {
                columnFormat.setWidth(Math.max(columnFormat.getWidth(), Math.max(columnFormat.getFormat().length(), length - 2)));
                header = Alignment.CENTER.align(header.substring(1, length - 1), columnFormat.getWidth());
            } else if (header.charAt(0) == '^') {
                columnFormat.setWidth(Math.max(columnFormat.getWidth(), Math.max(columnFormat.getFormat().length(), length - 1)));
                header = Alignment.LEFT.align(header.substring(1, length), columnFormat.getWidth());
            } else if (header.charAt(length - 1) == '^') {
                columnFormat.setWidth(Math.max(columnFormat.getWidth(), Math.max(columnFormat.getFormat().length(), length - 1)));
                header = Alignment.RIGHT.align(header.substring(0, length - 1), columnFormat.getWidth());
            }
        }
        this.header.append(header);
        if (z) {
            this.header.append(" ");
        }
    }

    public String getHeader() {
        return this.header.toString();
    }
}
