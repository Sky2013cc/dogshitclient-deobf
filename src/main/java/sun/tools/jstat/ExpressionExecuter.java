package sun.tools.jstat;

import java.util.HashMap;
import sun.jvmstat.monitor.Monitor;
import sun.jvmstat.monitor.MonitoredVm;

/* loaded from: target.jar:sun/tools/jstat/ExpressionExecuter.class */
public class ExpressionExecuter implements ExpressionEvaluator {
    private static final boolean debug = Boolean.getBoolean("ExpressionEvaluator.debug");
    private MonitoredVm vm;
    private HashMap<String, Object> map = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExpressionExecuter(MonitoredVm monitoredVm) {
        this.vm = monitoredVm;
    }

    @Override // sun.tools.jstat.ExpressionEvaluator
    public Object evaluate(Expression expression) {
        if (expression == null) {
            return null;
        }
        if (debug) {
            System.out.println("Evaluating expression: " + expression);
        }
        if (expression instanceof Literal) {
            return ((Literal) expression).getValue();
        }
        if (expression instanceof Identifier) {
            Identifier identifier = (Identifier) expression;
            if (this.map.containsKey(identifier.getName())) {
                return this.map.get(identifier.getName());
            }
            Object value = ((Monitor) identifier.getValue()).getValue();
            this.map.put(identifier.getName(), value);
            return value;
        }
        Expression left = expression.getLeft();
        Expression right = expression.getRight();
        Operator operator = expression.getOperator();
        if (operator == null) {
            return evaluate(left);
        }
        Double d = new Double(((Number) evaluate(left)).doubleValue());
        Double d2 = new Double(((Number) evaluate(right)).doubleValue());
        double eval = operator.eval(d.doubleValue(), d2.doubleValue());
        if (debug) {
            System.out.println("Performed Operation: " + d + operator + d2 + " = " + eval);
        }
        return new Double(eval);
    }
}
