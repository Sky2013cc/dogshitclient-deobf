package net.ccbluex.liquidbounce.ui.font;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Fonts.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0086\b\u0018��2\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u0017"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/font/FontInfo;", "", "name", "", "size", "", "isCustom", "", Constants.CTOR, "(Ljava/lang/String;IZ)V", "getName", "()Ljava/lang/String;", "getSize", "()I", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/font/FontInfo.class */
public final class FontInfo {

    @NotNull
    private final String name;
    private final int size;
    private final boolean isCustom;

    @NotNull
    public final String component1() {
        return this.name;
    }

    public final int component2() {
        return this.size;
    }

    public final boolean component3() {
        return this.isCustom;
    }

    @NotNull
    public final FontInfo copy(@NotNull String name, int size, boolean isCustom) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new FontInfo(name, size, isCustom);
    }

    public static /* synthetic */ FontInfo copy$default(FontInfo fontInfo, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = fontInfo.name;
        }
        if ((i2 & 2) != 0) {
            i = fontInfo.size;
        }
        if ((i2 & 4) != 0) {
            z = fontInfo.isCustom;
        }
        return fontInfo.copy(str, i, z);
    }

    @NotNull
    public String toString() {
        return "FontInfo(name=" + this.name + ", size=" + this.size + ", isCustom=" + this.isCustom + ')';
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (((result * 31) + Integer.hashCode(this.size)) * 31) + Boolean.hashCode(this.isCustom);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FontInfo)) {
            return false;
        }
        FontInfo fontInfo = (FontInfo) other;
        return Intrinsics.areEqual(this.name, fontInfo.name) && this.size == fontInfo.size && this.isCustom == fontInfo.isCustom;
    }

    public FontInfo(@NotNull String name, int size, boolean isCustom) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.size = size;
        this.isCustom = isCustom;
    }

    public /* synthetic */ FontInfo(String str, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? -1 : i, (i2 & 4) != 0 ? false : z);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final int getSize() {
        return this.size;
    }

    public final boolean isCustom() {
        return this.isCustom;
    }
}
