package net.ccbluex.liquidbounce.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ResponseTypes.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lnet/ccbluex/liquidbounce/api/ReportResponse;", "", "status", "Lnet/ccbluex/liquidbounce/api/Status;", "message", "", Constants.CTOR, "(Lnet/ccbluex/liquidbounce/api/Status;Ljava/lang/String;)V", "getStatus", "()Lnet/ccbluex/liquidbounce/api/Status;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/api/ReportResponse.class */
public final class ReportResponse {

    @NotNull
    private final Status status;

    @NotNull
    private final String message;

    @NotNull
    public final Status component1() {
        return this.status;
    }

    @NotNull
    public final String component2() {
        return this.message;
    }

    @NotNull
    public final ReportResponse copy(@NotNull Status status, @NotNull String message) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(message, "message");
        return new ReportResponse(status, message);
    }

    public static /* synthetic */ ReportResponse copy$default(ReportResponse reportResponse, Status status, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            status = reportResponse.status;
        }
        if ((i & 2) != 0) {
            str = reportResponse.message;
        }
        return reportResponse.copy(status, str);
    }

    @NotNull
    public String toString() {
        return "ReportResponse(status=" + this.status + ", message=" + this.message + ')';
    }

    public int hashCode() {
        int result = this.status.hashCode();
        return (result * 31) + this.message.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReportResponse)) {
            return false;
        }
        ReportResponse reportResponse = (ReportResponse) other;
        return this.status == reportResponse.status && Intrinsics.areEqual(this.message, reportResponse.message);
    }

    public ReportResponse(@NotNull Status status, @NotNull String message) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(message, "message");
        this.status = status;
        this.message = message;
    }

    @NotNull
    public final Status getStatus() {
        return this.status;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }
}
