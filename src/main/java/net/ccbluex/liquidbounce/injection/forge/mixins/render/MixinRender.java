package net.ccbluex.liquidbounce.injection.forge.mixins.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({Render.class})
/* loaded from: target.jar:net/ccbluex/liquidbounce/injection/forge/mixins/render/MixinRender.class */
public abstract class MixinRender {
    /* JADX INFO: Access modifiers changed from: protected */
    @Shadow
    public abstract <T extends Entity> boolean func_180548_c(T t);
}
