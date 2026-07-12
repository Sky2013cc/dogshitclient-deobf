package net.ccbluex.liquidbounce.ui.font;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Fonts.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018��2\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/font/CustomFontInfo;", "", "name", "", "fontFile", "fontSize", "", Constants.CTOR, "(Ljava/lang/String;Ljava/lang/String;I)V", "getName", "()Ljava/lang/String;", "getFontFile", "getFontSize", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/font/CustomFontInfo.class */
public final class CustomFontInfo {

    @NotNull
    private final String name;

    @NotNull
    private final String fontFile;
    private final int fontSize;

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.fontFile;
    }

    public final int component3() {
        return this.fontSize;
    }

    @NotNull
    public final CustomFontInfo copy(@NotNull String name, @NotNull String fontFile, int fontSize) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(fontFile, "fontFile");
        return new CustomFontInfo(name, fontFile, fontSize);
    }

    public static /* synthetic */ CustomFontInfo copy$default(CustomFontInfo customFontInfo, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = customFontInfo.name;
        }
        if ((i2 & 2) != 0) {
            str2 = customFontInfo.fontFile;
        }
        if ((i2 & 4) != 0) {
            i = customFontInfo.fontSize;
        }
        return customFontInfo.copy(str, str2, i);
    }

    @NotNull
    public String toString() {
        return "CustomFontInfo(name=" + this.name + ", fontFile=" + this.fontFile + ", fontSize=" + this.fontSize + ')';
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (((result * 31) + this.fontFile.hashCode()) * 31) + Integer.hashCode(this.fontSize);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomFontInfo)) {
            return false;
        }
        CustomFontInfo customFontInfo = (CustomFontInfo) other;
        return Intrinsics.areEqual(this.name, customFontInfo.name) && Intrinsics.areEqual(this.fontFile, customFontInfo.fontFile) && this.fontSize == customFontInfo.fontSize;
    }

    public CustomFontInfo(@NotNull String name, @NotNull String fontFile, int fontSize) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(fontFile, "fontFile");
        this.name = name;
        this.fontFile = fontFile;
        this.fontSize = fontSize;
    }

    public /* synthetic */ CustomFontInfo(String str, String str2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? 20 : i);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getFontFile() {
        return this.fontFile;
    }

    public final int getFontSize() {
        return this.fontSize;
    }
}
