package jdk.nashorn.internal.codegen.types;

import jdk.internal.org.objectweb.asm.MethodVisitor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:jdk/nashorn/internal/codegen/types/BytecodeNumericOps.class */
public interface BytecodeNumericOps {
    Type neg(MethodVisitor methodVisitor, int i);

    Type sub(MethodVisitor methodVisitor, int i);

    Type mul(MethodVisitor methodVisitor, int i);

    Type div(MethodVisitor methodVisitor, int i);

    Type rem(MethodVisitor methodVisitor, int i);

    Type cmp(MethodVisitor methodVisitor, boolean z);
}
