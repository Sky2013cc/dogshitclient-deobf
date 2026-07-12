package sun.tools.jstat;

import sun.jvmstat.monitor.MonitorException;

/* loaded from: target.jar:sun/tools/jstat/SymbolResolutionClosure.class */
public class SymbolResolutionClosure implements Closure {
    private static final boolean debug = Boolean.getBoolean("SymbolResolutionClosure.debug");
    private ExpressionEvaluator ee;

    public SymbolResolutionClosure(ExpressionEvaluator expressionEvaluator) {
        this.ee = expressionEvaluator;
    }

    @Override // sun.tools.jstat.Closure
    public void visit(Object obj, boolean z) throws MonitorException {
        if (!(obj instanceof ColumnFormat)) {
            return;
        }
        ColumnFormat columnFormat = (ColumnFormat) obj;
        Expression expression = columnFormat.getExpression();
        String expression2 = expression.toString();
        Expression expression3 = (Expression) this.ee.evaluate(expression);
        if (debug) {
            System.out.print("Expression: " + expression2 + " resolved to " + expression3.toString());
        }
        columnFormat.setExpression(expression3);
    }
}
