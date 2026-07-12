package net.ccbluex.liquidbounce.utils.client;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: JavaVersion.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018�� \u001d2\u00020\u0001:\u0001\u001dB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J;\u0010\u0017\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001e"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/client/JavaVersion;", "", "raw", "", "major", "", "minor", "patch", "update", Constants.CTOR, "(Ljava/lang/String;IILjava/lang/String;I)V", "getRaw", "()Ljava/lang/String;", "getMajor", "()I", "getMinor", "getPatch", "getUpdate", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/JavaVersion.class */
public final class JavaVersion {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final String raw;
    private final int major;
    private final int minor;

    @NotNull
    private final String patch;
    private final int update;

    @NotNull
    public static final String DOWNLOAD_PAGE = "https://www.java.com/download/manual.jsp";

    @NotNull
    public final String component1() {
        return this.raw;
    }

    public final int component2() {
        return this.major;
    }

    public final int component3() {
        return this.minor;
    }

    @NotNull
    public final String component4() {
        return this.patch;
    }

    public final int component5() {
        return this.update;
    }

    @NotNull
    public final JavaVersion copy(@NotNull String raw, int major, int minor, @NotNull String patch, int update) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        Intrinsics.checkNotNullParameter(patch, "patch");
        return new JavaVersion(raw, major, minor, patch, update);
    }

    public static /* synthetic */ JavaVersion copy$default(JavaVersion javaVersion, String str, int i, int i2, String str2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = javaVersion.raw;
        }
        if ((i4 & 2) != 0) {
            i = javaVersion.major;
        }
        if ((i4 & 4) != 0) {
            i2 = javaVersion.minor;
        }
        if ((i4 & 8) != 0) {
            str2 = javaVersion.patch;
        }
        if ((i4 & 16) != 0) {
            i3 = javaVersion.update;
        }
        return javaVersion.copy(str, i, i2, str2, i3);
    }

    @NotNull
    public String toString() {
        return "JavaVersion(raw=" + this.raw + ", major=" + this.major + ", minor=" + this.minor + ", patch=" + this.patch + ", update=" + this.update + ')';
    }

    public int hashCode() {
        int result = this.raw.hashCode();
        return (((((((result * 31) + Integer.hashCode(this.major)) * 31) + Integer.hashCode(this.minor)) * 31) + this.patch.hashCode()) * 31) + Integer.hashCode(this.update);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JavaVersion)) {
            return false;
        }
        JavaVersion javaVersion = (JavaVersion) other;
        return Intrinsics.areEqual(this.raw, javaVersion.raw) && this.major == javaVersion.major && this.minor == javaVersion.minor && Intrinsics.areEqual(this.patch, javaVersion.patch) && this.update == javaVersion.update;
    }

    public JavaVersion(@NotNull String raw, int major, int minor, @NotNull String patch, int update) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        Intrinsics.checkNotNullParameter(patch, "patch");
        this.raw = raw;
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.update = update;
    }

    @NotNull
    public final String getRaw() {
        return this.raw;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    @NotNull
    public final String getPatch() {
        return this.patch;
    }

    public final int getUpdate() {
        return this.update;
    }

    /* compiled from: JavaVersion.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/client/JavaVersion$Companion;", "", Constants.CTOR, "()V", "DOWNLOAD_PAGE", "", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/JavaVersion$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }
}
