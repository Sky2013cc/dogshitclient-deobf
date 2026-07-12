package sun.jvmstat.perfdata.monitor;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/SyntaxException.class */
public class SyntaxException extends Exception {
    int lineno;

    public SyntaxException(int i) {
        this.lineno = i;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "syntax error at line " + this.lineno;
    }
}
