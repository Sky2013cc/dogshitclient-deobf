package net.ccbluex.liquidbounce.injection.forge.mixins.entity;

import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.inventory.SilentHotbar;
import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({InventoryPlayer.class})
/* loaded from: target.jar:net/ccbluex/liquidbounce/injection/forge/mixins/entity/MixinInventoryPlayer.class */
public class MixinInventoryPlayer {
    @Redirect(method = {"getCurrentItem", "decrementAnimations", "getStrVsBlock", "canHeldItemHarvest"}, at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/InventoryPlayer;currentItem:I", opcode = 180))
    private int hookSilentHotbar(InventoryPlayer instance) {
        if (instance != null && instance.field_70458_d != null && MinecraftInstance.mc.field_71439_g != null) {
            return instance.field_70458_d.func_146103_bH().equals(MinecraftInstance.mc.field_71439_g.func_146103_bH()) ? SilentHotbar.INSTANCE.getCurrentSlot() : instance.field_70461_c;
        }
        if (instance != null) {
            return instance.field_70461_c;
        }
        return 0;
    }
}
