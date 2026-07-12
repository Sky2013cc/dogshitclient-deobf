package com.sun.tools.javac.jvm;

import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;

/* loaded from: target.jar:com/sun/tools/javac/jvm/UninitializedType.class */
class UninitializedType extends Type.DelegatedType {
    public final int offset;

    public static UninitializedType uninitializedThis(Type type) {
        return new UninitializedType(TypeTag.UNINITIALIZED_THIS, type, -1);
    }

    public static UninitializedType uninitializedObject(Type type, int i) {
        return new UninitializedType(TypeTag.UNINITIALIZED_OBJECT, type, i);
    }

    private UninitializedType(TypeTag typeTag, Type type, int i) {
        super(typeTag, type);
        this.offset = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Type initializedType() {
        return this.qtype;
    }
}
