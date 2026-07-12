package sun.tools.java;

import okhttp3.internal.url._UrlKt;

/* loaded from: target.jar:sun/tools/java/ArrayType.class */
public final class ArrayType extends Type {
    Type elemType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayType(String str, Type type) {
        super(9, str);
        this.elemType = type;
    }

    @Override // sun.tools.java.Type
    public Type getElementType() {
        return this.elemType;
    }

    @Override // sun.tools.java.Type
    public int getArrayDimension() {
        return this.elemType.getArrayDimension() + 1;
    }

    @Override // sun.tools.java.Type
    public String typeString(String str, boolean z, boolean z2) {
        return getElementType().typeString(str, z, z2) + _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
    }
}
