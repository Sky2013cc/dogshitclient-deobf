package net.ccbluex.liquidbounce.utils.render;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import org.jetbrains.annotations.NotNull;

/* compiled from: RenderExtensions.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\u0016\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u001a%\u0010��\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005H\u0086\bø\u0001��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0006"}, d2 = {"drawWithTessellatorWorldRenderer", "", "drawAction", "Lkotlin/Function1;", "Lnet/minecraft/client/renderer/WorldRenderer;", "Lkotlin/ExtensionFunctionType;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/RenderExtensionsKt.class */
public final class RenderExtensionsKt {
    public static final void drawWithTessellatorWorldRenderer(@NotNull Function1<? super WorldRenderer, Unit> drawAction) {
        Intrinsics.checkNotNullParameter(drawAction, "drawAction");
        Tessellator instance = Tessellator.func_178181_a();
        try {
            WorldRenderer func_178180_c = instance.func_178180_c();
            Intrinsics.checkNotNullExpressionValue(func_178180_c, "getWorldRenderer(...)");
            drawAction.invoke(func_178180_c);
            InlineMarker.finallyStart(1);
            instance.func_78381_a();
            GlStateManager.func_179117_G();
            InlineMarker.finallyEnd(1);
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            instance.func_78381_a();
            GlStateManager.func_179117_G();
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }
}
