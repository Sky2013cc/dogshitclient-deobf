package net.ccbluex.liquidbounce.ui.client;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: PopupScreen.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/ButtonData;", "", "text", "", "action", "Ljava/lang/Runnable;", Constants.CTOR, "(Ljava/lang/String;Ljava/lang/Runnable;)V", "getText", "()Ljava/lang/String;", "getAction", "()Ljava/lang/Runnable;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/ButtonData.class */
public final class ButtonData {

    @NotNull
    private final String text;

    @NotNull
    private final Runnable action;

    @NotNull
    public final String component1() {
        return this.text;
    }

    @NotNull
    public final Runnable component2() {
        return this.action;
    }

    @NotNull
    public final ButtonData copy(@NotNull String text, @NotNull Runnable action) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(action, "action");
        return new ButtonData(text, action);
    }

    public static /* synthetic */ ButtonData copy$default(ButtonData buttonData, String str, Runnable runnable, int i, Object obj) {
        if ((i & 1) != 0) {
            str = buttonData.text;
        }
        if ((i & 2) != 0) {
            runnable = buttonData.action;
        }
        return buttonData.copy(str, runnable);
    }

    @NotNull
    public String toString() {
        return "ButtonData(text=" + this.text + ", action=" + this.action + ')';
    }

    public int hashCode() {
        int result = this.text.hashCode();
        return (result * 31) + this.action.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ButtonData)) {
            return false;
        }
        ButtonData buttonData = (ButtonData) other;
        return Intrinsics.areEqual(this.text, buttonData.text) && Intrinsics.areEqual(this.action, buttonData.action);
    }

    public ButtonData(@NotNull String text, @NotNull Runnable action) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(action, "action");
        this.text = text;
        this.action = action;
    }

    @NotNull
    public final String getText() {
        return this.text;
    }

    @NotNull
    public final Runnable getAction() {
        return this.action;
    }
}
