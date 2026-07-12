package sun.tools.jstat;

/* loaded from: target.jar:sun/tools/jstat/Expression.class */
public class Expression {
    private static int nextOrdinal;
    private boolean debug = Boolean.getBoolean("Expression.debug");
    private Expression left;
    private Expression right;
    private Operator operator;
    private int ordinal;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Expression() {
        int i = nextOrdinal;
        nextOrdinal = i + 1;
        this.ordinal = i;
        if (this.debug) {
            System.out.println("Expression " + this.ordinal + " created");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLeft(Expression expression) {
        if (this.debug) {
            System.out.println("Setting left on " + this.ordinal + " to " + expression);
        }
        this.left = expression;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Expression getLeft() {
        return this.left;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRight(Expression expression) {
        if (this.debug) {
            System.out.println("Setting right on " + this.ordinal + " to " + expression);
        }
        this.right = expression;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Expression getRight() {
        return this.right;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOperator(Operator operator) {
        if (this.debug) {
            System.out.println("Setting operator on " + this.ordinal + " to " + operator);
        }
        this.operator = operator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Operator getOperator() {
        return this.operator;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        if (this.left != null) {
            sb.append(this.left.toString());
        }
        if (this.operator != null) {
            sb.append(this.operator.toString());
            if (this.right != null) {
                sb.append(this.right.toString());
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
