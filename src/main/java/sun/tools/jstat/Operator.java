package sun.tools.jstat;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.util.HashMap;
import java.util.Set;
import org.slf4j.Marker;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:sun/tools/jstat/Operator.class */
public abstract class Operator {
    private final String name;
    private final int ordinal;
    private static int nextOrdinal = 0;
    private static HashMap<String, Operator> map = new HashMap<>();
    public static final Operator PLUS = new Operator(Marker.ANY_NON_NULL_MARKER) { // from class: sun.tools.jstat.Operator.1
        @Override // sun.tools.jstat.Operator
        protected double eval(double d, double d2) {
            return d + d2;
        }
    };
    public static final Operator MINUS = new Operator(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR) { // from class: sun.tools.jstat.Operator.2
        @Override // sun.tools.jstat.Operator
        protected double eval(double d, double d2) {
            return d - d2;
        }
    };
    public static final Operator DIVIDE = new Operator(RuntimeConstants.SIG_PACKAGE) { // from class: sun.tools.jstat.Operator.3
        @Override // sun.tools.jstat.Operator
        protected double eval(double d, double d2) {
            if (d2 == 0.0d) {
                return Double.NaN;
            }
            return d / d2;
        }
    };
    public static final Operator MULTIPLY = new Operator(Marker.ANY_MARKER) { // from class: sun.tools.jstat.Operator.4
        @Override // sun.tools.jstat.Operator
        protected double eval(double d, double d2) {
            return d * d2;
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract double eval(double d, double d2);

    private Operator(String str) {
        int i = nextOrdinal;
        nextOrdinal = i + 1;
        this.ordinal = i;
        this.name = str;
        map.put(str, this);
    }

    public String toString() {
        return this.name;
    }

    public static Operator toOperator(String str) {
        return map.get(str);
    }

    protected static Set keySet() {
        return map.keySet();
    }
}
