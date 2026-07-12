package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.codegen.types.Type;
import jdk.nashorn.internal.runtime.UnwarrantedOptimismException;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;

/* loaded from: target.jar:jdk/nashorn/internal/ir/Expression.class */
public abstract class Expression extends Node {
    private static final long serialVersionUID = 1;
    static final String OPT_IDENTIFIER = "%";

    public abstract Type getType();

    /* JADX INFO: Access modifiers changed from: protected */
    public Expression(long token, int start, int finish) {
        super(token, start, finish);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Expression(long token, int finish) {
        super(token, finish);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Expression(Expression expr) {
        super(expr);
    }

    public boolean isLocal() {
        return false;
    }

    public boolean isSelfModifying() {
        return false;
    }

    public Type getWidestOperationType() {
        return Type.OBJECT;
    }

    public final boolean isOptimistic() {
        return getType().narrowerThan(getWidestOperationType());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void optimisticTypeToString(StringBuilder sb) {
        optimisticTypeToString(sb, isOptimistic());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public void optimisticTypeToString(StringBuilder sb, boolean optimistic) {
        sb.append('{');
        Type type = getType();
        String desc = type == Type.UNDEFINED ? PDBorderStyleDictionary.STYLE_UNDERLINE : type.getDescriptor();
        sb.append(desc.charAt(desc.length() - 1) == ';' ? PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE : desc);
        if (isOptimistic() && optimistic) {
            sb.append(OPT_IDENTIFIER);
            int pp = ((Optimistic) this).getProgramPoint();
            if (UnwarrantedOptimismException.isValid(pp)) {
                sb.append('_').append(pp);
            }
        }
        sb.append('}');
    }

    public boolean isAlwaysFalse() {
        return false;
    }

    public boolean isAlwaysTrue() {
        return false;
    }

    public static boolean isAlwaysFalse(Expression test) {
        return test != null && test.isAlwaysFalse();
    }

    public static boolean isAlwaysTrue(Expression test) {
        return test == null || test.isAlwaysTrue();
    }
}
