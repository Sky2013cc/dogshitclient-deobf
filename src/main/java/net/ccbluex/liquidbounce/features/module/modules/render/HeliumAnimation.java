package net.ccbluex.liquidbounce.features.module.modules.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Animations.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/render/HeliumAnimation;", "Lnet/ccbluex/liquidbounce/features/module/modules/render/Animation;", Constants.CTOR, "()V", "transform", "", "f1", "", "f", "clientPlayer", "Lnet/minecraft/client/entity/AbstractClientPlayer;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/render/HeliumAnimation.class */
public final class HeliumAnimation extends Animation {
    public HeliumAnimation() {
        super("Helium");
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.render.Animation
    public void transform(float f1, float f, @NotNull AbstractClientPlayer clientPlayer) {
        Intrinsics.checkNotNullParameter(clientPlayer, "clientPlayer");
        transformFirstPersonItem(f, 0.0f);
        float c0 = MathHelper.func_76126_a(f1 * f * 3.1415927f);
        float c1 = MathHelper.func_76126_a(MathHelper.func_76129_c(f1) * 3.1415927f);
        GlStateManager.func_179114_b((-c1) * 55.0f, 30.0f, c0 / 5.0f, 0.0f);
        doBlockTransformations();
    }
}
