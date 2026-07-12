package jdk.nashorn.internal.codegen.types;

/* loaded from: target.jar:jdk/nashorn/internal/codegen/types/BitwiseType.class */
public abstract class BitwiseType extends NumericType implements BytecodeBitwiseOps {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    public BitwiseType(String name, Class<?> clazz, int weight, int slots) {
        super(name, clazz, weight, slots);
    }
}
