package jdk.nashorn.internal.codegen.types;

/* loaded from: target.jar:jdk/nashorn/internal/codegen/types/NumericType.class */
public abstract class NumericType extends Type implements BytecodeNumericOps {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    public NumericType(String name, Class<?> clazz, int weight, int slots) {
        super(name, clazz, weight, slots);
    }
}
