package net.ccbluex.liquidbounce.utils.client;

import com.formdev.flatlaf.FlatClientProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.gui.GuiTextField;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.util.Constants;

/* compiled from: TabUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/client/TabUtils;", "", Constants.CTOR, "()V", FlatClientProperties.BUTTON_TYPE_TAB, "", "textFields", "", "Lnet/minecraft/client/gui/GuiTextField;", "([Lnet/minecraft/client/gui/GuiTextField;)V", "haze"})
@SourceDebugExtension({"SMAP\nTabUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TabUtils.kt\nnet/ccbluex/liquidbounce/utils/client/TabUtils\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,31:1\n13467#2,3:32\n*S KotlinDebug\n*F\n+ 1 TabUtils.kt\nnet/ccbluex/liquidbounce/utils/client/TabUtils\n*L\n13#1:32,3\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/TabUtils.class */
public final class TabUtils {

    @NotNull
    public static final TabUtils INSTANCE = new TabUtils();

    private TabUtils() {
    }

    public final void tab(@NotNull GuiTextField... textFields) {
        Intrinsics.checkNotNullParameter(textFields, "textFields");
        int index$iv = 0;
        for (GuiTextField guiTextField : textFields) {
            int i = index$iv;
            index$iv++;
            if (guiTextField.func_146206_l()) {
                guiTextField.func_146195_b(false);
                textFields[((i + (Keyboard.isKeyDown(42) ? -1 : 1)) + textFields.length) % textFields.length].func_146195_b(true);
                return;
            }
        }
        textFields[0].func_146195_b(true);
    }
}
