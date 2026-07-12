package net.ccbluex.liquidbounce.cape;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: CapeService.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\n¨\u0006\u0017"}, d2 = {"Lnet/ccbluex/liquidbounce/cape/LoginResponse;", "", "cape", "", "enabled", "", "uuid", Constants.CTOR, "(Ljava/lang/String;ZLjava/lang/String;)V", "getCape", "()Ljava/lang/String;", "getEnabled", "()Z", "getUuid", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/cape/LoginResponse.class */
final class LoginResponse {

    @NotNull
    private final String cape;
    private final boolean enabled;

    @NotNull
    private final String uuid;

    @NotNull
    public final String component1() {
        return this.cape;
    }

    public final boolean component2() {
        return this.enabled;
    }

    @NotNull
    public final String component3() {
        return this.uuid;
    }

    @NotNull
    public final LoginResponse copy(@NotNull String cape, boolean enabled, @NotNull String uuid) {
        Intrinsics.checkNotNullParameter(cape, "cape");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        return new LoginResponse(cape, enabled, uuid);
    }

    public static /* synthetic */ LoginResponse copy$default(LoginResponse loginResponse, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = loginResponse.cape;
        }
        if ((i & 2) != 0) {
            z = loginResponse.enabled;
        }
        if ((i & 4) != 0) {
            str2 = loginResponse.uuid;
        }
        return loginResponse.copy(str, z, str2);
    }

    @NotNull
    public String toString() {
        return "LoginResponse(cape=" + this.cape + ", enabled=" + this.enabled + ", uuid=" + this.uuid + ')';
    }

    public int hashCode() {
        int result = this.cape.hashCode();
        return (((result * 31) + Boolean.hashCode(this.enabled)) * 31) + this.uuid.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoginResponse)) {
            return false;
        }
        LoginResponse loginResponse = (LoginResponse) other;
        return Intrinsics.areEqual(this.cape, loginResponse.cape) && this.enabled == loginResponse.enabled && Intrinsics.areEqual(this.uuid, loginResponse.uuid);
    }

    public LoginResponse(@NotNull String cape, boolean enabled, @NotNull String uuid) {
        Intrinsics.checkNotNullParameter(cape, "cape");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        this.cape = cape;
        this.enabled = enabled;
        this.uuid = uuid;
    }

    @NotNull
    public final String getCape() {
        return this.cape;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    @NotNull
    public final String getUuid() {
        return this.uuid;
    }
}
