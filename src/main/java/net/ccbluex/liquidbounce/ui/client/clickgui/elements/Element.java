package net.ccbluex.liquidbounce.ui.client.clickgui.elements;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import kotlin.Metadata;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.Minecraft;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Element.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\n\b&\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005J)\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00052\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0002\u0010\u001dJ \u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\u0016J \u0010\u001f\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0005H\u0016J\u0016\u0010!\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u0012\u0010\u0010\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0007R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0012\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/clickgui/elements/Element;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", Constants.CTOR, "()V", SimpleTaglet.EXCLUDED, "", "getX", "()I", "setX", "(I)V", OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "getY", "setY", "width", "getWidth", "setWidth", "height", "getHeight", "isVisible", "", "()Z", "setVisible", "(Z)V", "setLocation", "", "drawScreenAndClick", "mouseX", "mouseY", "mouseButton", "(IILjava/lang/Integer;)Z", "mouseClicked", "mouseReleased", "button", "isHovered", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/clickgui/elements/Element.class */
public abstract class Element implements MinecraftInstance {
    private int x;
    private int y;
    private int width;
    private boolean isVisible = true;

    public abstract int getHeight();

    public abstract boolean drawScreenAndClick(int i, int i2, @Nullable Integer num);

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    public final int getX() {
        return this.x;
    }

    public final void setX(int i) {
        this.x = i;
    }

    public final int getY() {
        return this.y;
    }

    public final void setY(int i) {
        this.y = i;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    public final boolean isVisible() {
        return this.isVisible;
    }

    public final void setVisible(boolean z) {
        this.isVisible = z;
    }

    public final void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static /* synthetic */ boolean drawScreenAndClick$default(Element element, int i, int i2, Integer num, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawScreenAndClick");
        }
        if ((i3 & 4) != 0) {
            num = null;
        }
        return element.drawScreenAndClick(i, i2, num);
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        return isHovered(mouseX, mouseY);
    }

    public boolean mouseReleased(int mouseX, int mouseY, int button) {
        return isHovered(mouseX, mouseY);
    }

    public final boolean isHovered(int mouseX, int mouseY) {
        if (this.isVisible) {
            if (mouseX <= this.x + this.width ? this.x <= mouseX : false) {
                if (mouseY <= this.y + getHeight() ? this.y <= mouseY : false) {
                    return true;
                }
            }
        }
        return false;
    }
}
