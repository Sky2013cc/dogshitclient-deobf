package net.ccbluex.liquidbounce.script.remapper.injection.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.spongepowered.asm.util.Constants;

/* compiled from: NodeUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lnet/ccbluex/liquidbounce/script/remapper/injection/utils/NodeUtils;", "", Constants.CTOR, "()V", "toNodes", "Lorg/objectweb/asm/tree/InsnList;", "nodes", "", "Lorg/objectweb/asm/tree/AbstractInsnNode;", "([Lorg/objectweb/asm/tree/AbstractInsnNode;)Lorg/objectweb/asm/tree/InsnList;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/script/remapper/injection/utils/NodeUtils.class */
public final class NodeUtils {

    @NotNull
    public static final NodeUtils INSTANCE = new NodeUtils();

    private NodeUtils() {
    }

    @NotNull
    public final InsnList toNodes(@NotNull AbstractInsnNode... nodes) {
        Intrinsics.checkNotNullParameter(nodes, "nodes");
        InsnList insnList = new InsnList();
        for (AbstractInsnNode node : nodes) {
            insnList.add(node);
        }
        return insnList;
    }
}
