package net.ccbluex.liquidbounce.features.module.modules.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Animations.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\tH\u0004J\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000bH\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/render/Animation;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", "name", "", Constants.CTOR, "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "transform", "", "f1", "", "f", "clientPlayer", "Lnet/minecraft/client/entity/AbstractClientPlayer;", "doBlockTransformations", "transformFirstPersonItem", "equipProgress", "swingProgress", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/render/Animation.class */
public abstract class Animation implements MinecraftInstance {

    @NotNull
    private final String name;

    public abstract void transform(float f, float f2, @NotNull AbstractClientPlayer abstractClientPlayer);

    public Animation(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void doBlockTransformations() {
        GlStateManager.func_179109_b(-0.5f, 0.2f, 0.0f);
        GlStateManager.func_179114_b(30.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(-80.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.func_179114_b(60.0f, 0.0f, 1.0f, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void transformFirstPersonItem(float equipProgress, float swingProgress) {
        GlStateManager.func_179109_b(0.56f, -0.52f, -0.71999997f);
        GlStateManager.func_179109_b(0.0f, equipProgress * (-0.6f), 0.0f);
        GlStateManager.func_179114_b(45.0f, 0.0f, 1.0f, 0.0f);
        float f = MathHelper.func_76126_a(swingProgress * swingProgress * 3.1415927f);
        float f1 = MathHelper.func_76126_a(MathHelper.func_76129_c(swingProgress) * 3.1415927f);
        GlStateManager.func_179114_b(f * (-20.0f), 0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(f1 * (-20.0f), 0.0f, 0.0f, 1.0f);
        GlStateManager.func_179114_b(f1 * (-80.0f), 1.0f, 0.0f, 0.0f);
        GlStateManager.func_179152_a(0.4f, 0.4f, 0.4f);
    }
}
