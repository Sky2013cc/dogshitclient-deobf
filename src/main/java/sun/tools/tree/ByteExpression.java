package sun.tools.tree;

import java.io.PrintStream;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.tools.java.Type;

/* loaded from: target.jar:sun/tools/tree/ByteExpression.class */
public class ByteExpression extends IntegerExpression {
    public ByteExpression(long j, byte b) {
        super(62, j, Type.tByte, b);
    }

    @Override // sun.tools.tree.Expression, sun.tools.tree.Node
    public void print(PrintStream printStream) {
        printStream.print(this.value + OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE);
    }
}
