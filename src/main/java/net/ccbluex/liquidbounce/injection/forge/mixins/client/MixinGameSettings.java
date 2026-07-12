package net.ccbluex.liquidbounce.injection.forge.mixins.client;

import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GameSettings.class})
/* loaded from: target.jar:net/ccbluex/liquidbounce/injection/forge/mixins/client/MixinGameSettings.class */
public class MixinGameSettings {

    @Shadow
    public int field_74335_Z;

    @Inject(method = {"<init>()V"}, at = {@At("RETURN")})
    private void injectGuiScaleDefault(CallbackInfo callbackInfo) {
        this.field_74335_Z = 2;
    }
}
