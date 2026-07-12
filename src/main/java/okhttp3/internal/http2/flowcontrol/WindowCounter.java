package okhttp3.internal.http2.flowcontrol;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: WindowCounter.kt */
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0002\n��\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\r\u0010\tR\u0011\u0010\u000e\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\t¨\u0006\u0014"}, d2 = {"Lokhttp3/internal/http2/flowcontrol/WindowCounter;", "", "streamId", "", "(I)V", "<set-?>", "", "acknowledged", "getAcknowledged", "()J", "getStreamId", "()I", "total", "getTotal", "unacknowledged", "getUnacknowledged", "toString", "", "update", "", "okhttp"})
/* loaded from: target.jar:okhttp3/internal/http2/flowcontrol/WindowCounter.class */
public final class WindowCounter {
    private final int streamId;
    private long total;
    private long acknowledged;

    public WindowCounter(int streamId) {
        this.streamId = streamId;
    }

    public final int getStreamId() {
        return this.streamId;
    }

    public final long getTotal() {
        return this.total;
    }

    public final long getAcknowledged() {
        return this.acknowledged;
    }

    public final synchronized long getUnacknowledged() {
        return this.total - this.acknowledged;
    }

    public static /* synthetic */ void update$default(WindowCounter windowCounter, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 0;
        }
        if ((i & 2) != 0) {
            j2 = 0;
        }
        windowCounter.update(j, j2);
    }

    public final synchronized void update(long total, long acknowledged) {
        if (!(total >= 0)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!(acknowledged >= 0)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.total += total;
        this.acknowledged += acknowledged;
        if (!(this.acknowledged <= this.total)) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    @NotNull
    public String toString() {
        return "WindowCounter(streamId=" + this.streamId + ", total=" + this.total + ", acknowledged=" + this.acknowledged + ", unacknowledged=" + getUnacknowledged() + ')';
    }
}
