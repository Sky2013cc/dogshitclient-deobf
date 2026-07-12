package sun.tools.jstat;

/* loaded from: target.jar:sun/tools/jstat/Literal.class */
public class Literal extends Expression {
    private Object value;

    public Literal(Object obj) {
        this.value = obj;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    @Override // sun.tools.jstat.Expression
    public String toString() {
        return this.value.toString();
    }
}
