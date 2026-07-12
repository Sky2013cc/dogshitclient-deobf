package sun.tools.tree;

import java.io.PrintStream;
import sun.tools.java.Type;

/* loaded from: target.jar:sun/tools/tree/IntExpression.class */
public class IntExpression extends IntegerExpression {
    public IntExpression(long j, int i) {
        super(65, j, Type.tInt, i);
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof IntExpression) && this.value == ((IntExpression) obj).value;
    }

    public int hashCode() {
        return this.value;
    }

    @Override // sun.tools.tree.Expression, sun.tools.tree.Node
    public void print(PrintStream printStream) {
        printStream.print(this.value);
    }
}
