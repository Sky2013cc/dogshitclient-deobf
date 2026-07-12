package net.ccbluex.liquidbounce.injection.forge.mixins.tweaks;

import net.minecraft.client.particle.EntityFX;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({EntityFX.class})
/* loaded from: target.jar:net/ccbluex/liquidbounce/injection/forge/mixins/tweaks/MixinEntityFX.class */
public class MixinEntityFX {

    @Unique
    private static final int BRIGHTNESS_VALUE = 15728880;

    @Redirect(method = {"renderParticle"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/EntityFX;getBrightnessForRender(F)I"))
    private int renderParticle(EntityFX entityFX, float f) {
        return BRIGHTNESS_VALUE;
    }
}
