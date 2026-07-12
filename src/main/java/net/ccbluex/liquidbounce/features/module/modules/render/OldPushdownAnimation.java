package net.ccbluex.liquidbounce.features.module.modules.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.util.Constants;

/* compiled from: Animations.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/render/OldPushdownAnimation;", "Lnet/ccbluex/liquidbounce/features/module/modules/render/Animation;", Constants.CTOR, "()V", "transform", "", "f1", "", "f", "clientPlayer", "Lnet/minecraft/client/entity/AbstractClientPlayer;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/render/OldPushdownAnimation.class */
public final class OldPushdownAnimation extends Animation {
    public OldPushdownAnimation() {
        super("OldPushdown");
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.render.Animation
    public void transform(float f1, float f, @NotNull AbstractClientPlayer clientPlayer) {
        Intrinsics.checkNotNullParameter(clientPlayer, "clientPlayer");
        GlStateManager.func_179137_b(0.56d, -0.52d, -0.5d);
        GlStateManager.func_179137_b(0.0d, (-f) * 0.3d, 0.0d);
        GlStateManager.func_179114_b(45.5f, 0.0f, 1.0f, 0.0f);
        float var3 = MathHelper.func_76126_a(0.0f);
        float var4 = MathHelper.func_76126_a(0.0f);
        GlStateManager.func_179114_b(var3 * (-20.0f), 0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(var4 * (-20.0f), 0.0f, 0.0f, 1.0f);
        GlStateManager.func_179114_b(var4 * (-80.0f), 1.0f, 0.0f, 0.0f);
        GlStateManager.func_179139_a(0.32d, 0.32d, 0.32d);
        float var15 = MathHelper.func_76126_a(MathHelper.func_76129_c(f1) * 3.1415927f);
        GlStateManager.func_179114_b(((-var15) * 125) / 1.75f, 3.95f, 0.35f, 8.0f);
        GlStateManager.func_179114_b((-var15) * 35, 0.0f, var15 / 100.0f, -10.0f);
        GlStateManager.func_179137_b(-1.0d, 0.6d, -0.0d);
        GlStateManager.func_179114_b(30.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(-80.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.func_179114_b(60.0f, 0.0f, 1.0f, 0.0f);
        GL11.glTranslated(1.05d, 0.35d, 0.4d);
        GL11.glTranslatef(-1.0f, 0.0f, 0.0f);
    }
}
