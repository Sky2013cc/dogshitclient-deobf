package sun.tools.java;

/* loaded from: target.jar:sun/tools/java/MethodType.class */
public final class MethodType extends Type {
    Type returnType;
    Type[] argTypes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodType(String str, Type type, Type[] typeArr) {
        super(12, str);
        this.returnType = type;
        this.argTypes = typeArr;
    }

    @Override // sun.tools.java.Type
    public Type getReturnType() {
        return this.returnType;
    }

    @Override // sun.tools.java.Type
    public Type[] getArgumentTypes() {
        return this.argTypes;
    }

    @Override // sun.tools.java.Type
    public boolean equalArguments(Type type) {
        if (type.typeCode != 12) {
            return false;
        }
        MethodType methodType = (MethodType) type;
        if (this.argTypes.length != methodType.argTypes.length) {
            return false;
        }
        for (int length = this.argTypes.length - 1; length >= 0; length--) {
            if (this.argTypes[length] != methodType.argTypes[length]) {
                return false;
            }
        }
        return true;
    }

    @Override // sun.tools.java.Type
    public int stackSize() {
        int i = 0;
        for (int i2 = 0; i2 < this.argTypes.length; i2++) {
            i += this.argTypes[i2].stackSize();
        }
        return i;
    }

    @Override // sun.tools.java.Type
    public String typeString(String str, boolean z, boolean z2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append('(');
        for (int i = 0; i < this.argTypes.length; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(this.argTypes[i].typeString("", z, z2));
        }
        stringBuffer.append(')');
        return z2 ? getReturnType().typeString(stringBuffer.toString(), z, z2) : stringBuffer.toString();
    }
}
