package sun.tools.tree;

import java.io.PrintStream;
import sun.tools.java.Type;

/* loaded from: target.jar:sun/tools/tree/CharExpression.class */
public class CharExpression extends IntegerExpression {
    public CharExpression(long j, char c) {
        super(63, j, Type.tChar, c);
    }

    @Override // sun.tools.tree.Expression, sun.tools.tree.Node
    public void print(PrintStream printStream) {
        printStream.print(this.value + "c");
    }
}
