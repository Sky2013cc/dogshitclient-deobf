package sun.tools.tree;

import sun.tools.asm.Assembler;
import sun.tools.java.Environment;
import sun.tools.java.Type;

/* loaded from: target.jar:sun/tools/tree/IntegerExpression.class */
public class IntegerExpression extends ConstantExpression {
    int value;

    @Override // sun.tools.tree.ConstantExpression, sun.tools.tree.Expression
    public /* bridge */ /* synthetic */ boolean isConstant() {
        return super.isConstant();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IntegerExpression(int i, long j, Type type, int i2) {
        super(i, j, type);
        this.value = i2;
    }

    @Override // sun.tools.tree.Expression
    public boolean fitsType(Environment environment, Context context, Type type) {
        if (this.type.isType(2)) {
            return super.fitsType(environment, context, type);
        }
        switch (type.getTypeCode()) {
            case 1:
                return this.value == ((byte) this.value);
            case 2:
                return this.value == ((char) this.value);
            case 3:
                return this.value == ((short) this.value);
            default:
                return super.fitsType(environment, context, type);
        }
    }

    @Override // sun.tools.tree.Expression
    public Object getValue() {
        return new Integer(this.value);
    }

    @Override // sun.tools.tree.Expression
    public boolean equals(int i) {
        return this.value == i;
    }

    @Override // sun.tools.tree.Expression
    public boolean equalsDefault() {
        return this.value == 0;
    }

    @Override // sun.tools.tree.Expression
    public void codeValue(Environment environment, Context context, Assembler assembler) {
        assembler.add(this.where, 18, new Integer(this.value));
    }
}
