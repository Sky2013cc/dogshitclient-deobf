package sun.tools.jstat;

import sun.jvmstat.monitor.Monitor;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.Variability;

/* loaded from: target.jar:sun/tools/jstat/ExpressionResolver.class */
public class ExpressionResolver implements ExpressionEvaluator {
    private static boolean debug = Boolean.getBoolean("ExpressionResolver.debug");
    private MonitoredVm vm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExpressionResolver(MonitoredVm monitoredVm) {
        this.vm = monitoredVm;
    }

    @Override // sun.tools.jstat.ExpressionEvaluator
    public Object evaluate(Expression expression) throws MonitorException {
        if (expression == null) {
            return null;
        }
        if (debug) {
            System.out.println("Resolving Expression:" + expression);
        }
        if (expression instanceof Identifier) {
            Identifier identifier = (Identifier) expression;
            if (identifier.isResolved()) {
                return identifier;
            }
            Monitor findByName = this.vm.findByName(identifier.getName());
            if (findByName == null) {
                System.err.println("Warning: Unresolved Symbol: " + identifier.getName() + " substituted NaN");
                return new Literal(new Double(Double.NaN));
            }
            if (findByName.getVariability() == Variability.CONSTANT) {
                if (debug) {
                    System.out.println("Converting constant " + identifier.getName() + " to literal with value " + findByName.getValue());
                }
                return new Literal(findByName.getValue());
            }
            identifier.setValue(findByName);
            return identifier;
        }
        if (expression instanceof Literal) {
            return expression;
        }
        Expression expression2 = null;
        Expression expression3 = null;
        if (expression.getLeft() != null) {
            expression2 = (Expression) evaluate(expression.getLeft());
        }
        if (expression.getRight() != null) {
            expression3 = (Expression) evaluate(expression.getRight());
        }
        if (expression2 != null && expression3 != null && (expression2 instanceof Literal) && (expression3 instanceof Literal)) {
            Literal literal = (Literal) expression2;
            Literal literal2 = (Literal) expression3;
            boolean z = false;
            Double d = new Double(Double.NaN);
            if (literal.getValue() instanceof String) {
                z = true;
                literal.setValue(d);
            }
            if (literal2.getValue() instanceof String) {
                z = true;
                literal2.setValue(d);
            }
            if (debug && z) {
                System.out.println("Warning: String literal in numerical expression: substitutied NaN");
            }
            Number number = (Number) literal.getValue();
            Number number2 = (Number) literal2.getValue();
            double eval = expression.getOperator().eval(number.doubleValue(), number2.doubleValue());
            if (debug) {
                System.out.println("Converting expression " + expression + " (left = " + number.doubleValue() + ") (right = " + number2.doubleValue() + ") to literal value " + eval);
            }
            return new Literal(new Double(eval));
        }
        if (expression2 != null && expression3 == null) {
            return expression2;
        }
        expression.setLeft(expression2);
        expression.setRight(expression3);
        return expression;
    }
}
