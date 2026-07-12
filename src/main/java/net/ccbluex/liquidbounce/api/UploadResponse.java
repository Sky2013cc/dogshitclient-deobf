package net.ccbluex.liquidbounce.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ResponseTypes.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lnet/ccbluex/liquidbounce/api/UploadResponse;", "", "status", "Lnet/ccbluex/liquidbounce/api/Status;", "message", "", "token", Constants.CTOR, "(Lnet/ccbluex/liquidbounce/api/Status;Ljava/lang/String;Ljava/lang/String;)V", "getStatus", "()Lnet/ccbluex/liquidbounce/api/Status;", "getMessage", "()Ljava/lang/String;", "getToken", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/api/UploadResponse.class */
public final class UploadResponse {

    @NotNull
    private final Status status;

    @NotNull
    private final String message;

    @NotNull
    private final String token;

    @NotNull
    public final Status component1() {
        return this.status;
    }

    @NotNull
    public final String component2() {
        return this.message;
    }

    @NotNull
    public final String component3() {
        return this.token;
    }

    @NotNull
    public final UploadResponse copy(@NotNull Status status, @NotNull String message, @NotNull String token) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(token, "token");
        return new UploadResponse(status, message, token);
    }

    public static /* synthetic */ UploadResponse copy$default(UploadResponse uploadResponse, Status status, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            status = uploadResponse.status;
        }
        if ((i & 2) != 0) {
            str = uploadResponse.message;
        }
        if ((i & 4) != 0) {
            str2 = uploadResponse.token;
        }
        return uploadResponse.copy(status, str, str2);
    }

    @NotNull
    public String toString() {
        return "UploadResponse(status=" + this.status + ", message=" + this.message + ", token=" + this.token + ')';
    }

    public int hashCode() {
        int result = this.status.hashCode();
        return (((result * 31) + this.message.hashCode()) * 31) + this.token.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UploadResponse)) {
            return false;
        }
        UploadResponse uploadResponse = (UploadResponse) other;
        return this.status == uploadResponse.status && Intrinsics.areEqual(this.message, uploadResponse.message) && Intrinsics.areEqual(this.token, uploadResponse.token);
    }

    public UploadResponse(@NotNull Status status, @NotNull String message, @NotNull String token) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(token, "token");
        this.status = status;
        this.message = message;
        this.token = token;
    }

    @NotNull
    public final Status getStatus() {
        return this.status;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    public final String getToken() {
        return this.token;
    }
}
