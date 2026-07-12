package sun.tools.tree;

import java.io.PrintStream;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.tools.java.Type;

/* loaded from: target.jar:sun/tools/tree/ShortExpression.class */
public class ShortExpression extends IntegerExpression {
    public ShortExpression(long j, short s) {
        super(64, j, Type.tShort, s);
    }

    @Override // sun.tools.tree.Expression, sun.tools.tree.Node
    public void print(PrintStream printStream) {
        printStream.print(this.value + OperatorName.CLOSE_AND_STROKE);
    }
}
