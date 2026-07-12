package net.ccbluex.liquidbounce.injection.forge.mixins.client;

import net.minecraft.util.MovementInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({MovementInput.class})
/* loaded from: target.jar:net/ccbluex/liquidbounce/injection/forge/mixins/client/MixinMovementInput.class */
public class MixinMovementInput {

    @Shadow
    public float field_78902_a;

    @Shadow
    public float field_78900_b;
}
