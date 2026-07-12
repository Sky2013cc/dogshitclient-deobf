package net.ccbluex.liquidbounce.ui.font;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.FontRenderer;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: GameFontRenderer.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��&\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n��\u001a4\u0010��\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a,\u0010��\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\f"}, d2 = {"drawCenteredString", "", "Lnet/minecraft/client/gui/FontRenderer;", "text", "", SimpleTaglet.EXCLUDED, "", OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "color", "", "shadow", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/font/GameFontRendererKt.class */
public final class GameFontRendererKt {
    public static final void drawCenteredString(@NotNull FontRenderer $this$drawCenteredString, @Nullable String text, float x, float y, int color, boolean shadow) {
        Intrinsics.checkNotNullParameter($this$drawCenteredString, "<this>");
        float drawX = x - ($this$drawCenteredString.func_78256_a(text) / 2.0f);
        if (shadow) {
            $this$drawCenteredString.func_175063_a(text, drawX, y, color);
        } else {
            $this$drawCenteredString.func_78276_b(text, (int) drawX, (int) y, color);
        }
    }

    public static final void drawCenteredString(@NotNull FontRenderer $this$drawCenteredString, @Nullable String text, float x, float y, int color) {
        Intrinsics.checkNotNullParameter($this$drawCenteredString, "<this>");
        float drawX = x - ($this$drawCenteredString.func_78256_a(text) / 2.0f);
        $this$drawCenteredString.func_78276_b(text, (int) drawX, (int) y, color);
    }
}
