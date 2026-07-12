package net.ccbluex.liquidbounce.ui.elements;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: GuiPasswordField.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n��\u0018��2\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/elements/GuiPasswordField;", "Lnet/minecraft/client/gui/GuiTextField;", "componentId", "", "fontrendererObj", "Lnet/minecraft/client/gui/FontRenderer;", SimpleTaglet.EXCLUDED, OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "width", "height", Constants.CTOR, "(ILnet/minecraft/client/gui/FontRenderer;IIII)V", "drawTextBox", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/elements/GuiPasswordField.class */
public final class GuiPasswordField extends GuiTextField {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GuiPasswordField(int componentId, @NotNull FontRenderer fontrendererObj, int x, int y, int width, int height) {
        super(componentId, fontrendererObj, x, y, width, height);
        Intrinsics.checkNotNullParameter(fontrendererObj, "fontrendererObj");
    }

    public void func_146194_f() {
        String realText = func_146179_b();
        StringBuilder $this$drawTextBox_u24lambda_u241 = new StringBuilder(realText.length());
        int length = realText.length();
        for (int i = 0; i < length; i++) {
            $this$drawTextBox_u24lambda_u241.append('*');
        }
        func_146180_a($this$drawTextBox_u24lambda_u241.toString());
        super.func_146194_f();
        func_146180_a(realText);
    }
}
