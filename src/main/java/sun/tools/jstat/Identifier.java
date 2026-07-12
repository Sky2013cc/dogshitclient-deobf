package sun.tools.jstat;

/* loaded from: target.jar:sun/tools/jstat/Identifier.class */
public class Identifier extends Expression {
    private String name;
    private Object value;

    public Identifier(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public Object getValue() {
        return this.value;
    }

    public boolean isResolved() {
        return this.value != null;
    }

    @Override // sun.tools.jstat.Expression
    public String toString() {
        return this.name;
    }
}
