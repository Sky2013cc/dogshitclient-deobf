package net.ccbluex.liquidbounce.script.remapper.injection.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.util.Constants;

/* compiled from: ClassUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n\u0002\b\u0003\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005¨\u0006\n"}, d2 = {"Lnet/ccbluex/liquidbounce/script/remapper/injection/utils/ClassUtils;", "", Constants.CTOR, "()V", "toClassNode", "Lorg/objectweb/asm/tree/ClassNode;", "bytes", "", "toBytes", "classNode", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/script/remapper/injection/utils/ClassUtils.class */
public final class ClassUtils {

    @NotNull
    public static final ClassUtils INSTANCE = new ClassUtils();

    private ClassUtils() {
    }

    @NotNull
    public final ClassNode toClassNode(@NotNull byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        ClassReader classReader = new ClassReader(bytes);
        ClassVisitor classNode = new ClassNode();
        classReader.accept(classNode, 0);
        return classNode;
    }

    @NotNull
    public final byte[] toBytes(@NotNull ClassNode classNode) {
        Intrinsics.checkNotNullParameter(classNode, "classNode");
        ClassVisitor classWriter = new ClassWriter(3);
        classNode.accept(classWriter);
        byte[] byteArray = classWriter.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }
}
