package jdk.nashorn.internal.ir;

/* loaded from: target.jar:jdk/nashorn/internal/ir/Statement.class */
public abstract class Statement extends Node implements Terminal {
    private static final long serialVersionUID = 1;
    private final int lineNumber;

    public Statement(int lineNumber, long token, int finish) {
        super(token, finish);
        this.lineNumber = lineNumber;
    }

    protected Statement(int lineNumber, long token, int start, int finish) {
        super(token, start, finish);
        this.lineNumber = lineNumber;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Statement(Statement node) {
        super(node);
        this.lineNumber = node.lineNumber;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public boolean isTerminal() {
        return false;
    }

    public boolean hasGoto() {
        return false;
    }

    public final boolean hasTerminalFlags() {
        return isTerminal() || hasGoto();
    }
}
