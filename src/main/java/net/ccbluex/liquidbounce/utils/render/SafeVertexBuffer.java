package net.ccbluex.liquidbounce.utils.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: SafeVertexBuffer.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0004¨\u0006\b"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/SafeVertexBuffer;", "Lnet/minecraft/client/renderer/vertex/VertexBuffer;", "vertexFormatIn", "Lnet/minecraft/client/renderer/vertex/VertexFormat;", Constants.CTOR, "(Lnet/minecraft/client/renderer/vertex/VertexFormat;)V", "finalize", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/SafeVertexBuffer.class */
public final class SafeVertexBuffer extends VertexBuffer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SafeVertexBuffer(@NotNull VertexFormat vertexFormatIn) {
        super(vertexFormatIn);
        Intrinsics.checkNotNullParameter(vertexFormatIn, "vertexFormatIn");
    }

    protected final void finalize() {
        func_177362_c();
    }
}
