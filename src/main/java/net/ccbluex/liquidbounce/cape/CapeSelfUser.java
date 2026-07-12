package net.ccbluex.liquidbounce.cape;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: CapeService.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J1\u0010\u0018\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u000b¨\u0006\u001e"}, d2 = {"Lnet/ccbluex/liquidbounce/cape/CapeSelfUser;", "", "token", "", "enabled", "", "uuid", "capeName", Constants.CTOR, "(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "getToken", "()Ljava/lang/String;", "getEnabled", "()Z", "setEnabled", "(Z)V", "getUuid", "setUuid", "(Ljava/lang/String;)V", "getCapeName", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/cape/CapeSelfUser.class */
public final class CapeSelfUser {

    @NotNull
    private final String token;
    private boolean enabled;

    @NotNull
    private String uuid;

    @NotNull
    private final String capeName;

    @NotNull
    public final String component1() {
        return this.token;
    }

    public final boolean component2() {
        return this.enabled;
    }

    @NotNull
    public final String component3() {
        return this.uuid;
    }

    @NotNull
    public final String component4() {
        return this.capeName;
    }

    @NotNull
    public final CapeSelfUser copy(@NotNull String token, boolean enabled, @NotNull String uuid, @NotNull String capeName) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(capeName, "capeName");
        return new CapeSelfUser(token, enabled, uuid, capeName);
    }

    public static /* synthetic */ CapeSelfUser copy$default(CapeSelfUser capeSelfUser, String str, boolean z, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = capeSelfUser.token;
        }
        if ((i & 2) != 0) {
            z = capeSelfUser.enabled;
        }
        if ((i & 4) != 0) {
            str2 = capeSelfUser.uuid;
        }
        if ((i & 8) != 0) {
            str3 = capeSelfUser.capeName;
        }
        return capeSelfUser.copy(str, z, str2, str3);
    }

    @NotNull
    public String toString() {
        return "CapeSelfUser(token=" + this.token + ", enabled=" + this.enabled + ", uuid=" + this.uuid + ", capeName=" + this.capeName + ')';
    }

    public int hashCode() {
        int result = this.token.hashCode();
        return (((((result * 31) + Boolean.hashCode(this.enabled)) * 31) + this.uuid.hashCode()) * 31) + this.capeName.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CapeSelfUser)) {
            return false;
        }
        CapeSelfUser capeSelfUser = (CapeSelfUser) other;
        return Intrinsics.areEqual(this.token, capeSelfUser.token) && this.enabled == capeSelfUser.enabled && Intrinsics.areEqual(this.uuid, capeSelfUser.uuid) && Intrinsics.areEqual(this.capeName, capeSelfUser.capeName);
    }

    public CapeSelfUser(@NotNull String token, boolean enabled, @NotNull String uuid, @NotNull String capeName) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(capeName, "capeName");
        this.token = token;
        this.enabled = enabled;
        this.uuid = uuid;
        this.capeName = capeName;
    }

    @NotNull
    public final String getToken() {
        return this.token;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    @NotNull
    public final String getUuid() {
        return this.uuid;
    }

    public final void setUuid(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uuid = str;
    }

    @NotNull
    public final String getCapeName() {
        return this.capeName;
    }
}
