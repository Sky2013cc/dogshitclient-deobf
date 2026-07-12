package net.ccbluex.liquidbounce.injection.forge.mixins.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({GuiButton.class})
@SideOnly(Side.CLIENT)
/* loaded from: target.jar:net/ccbluex/liquidbounce/injection/forge/mixins/gui/MixinGuiButton.class */
public abstract class MixinGuiButton extends Gui {
}
