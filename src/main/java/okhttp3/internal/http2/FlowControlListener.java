package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.flowcontrol.WindowCounter;
import org.jetbrains.annotations.NotNull;

/* compiled from: FlowControlListener.kt */
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018��2\u00020\u0001:\u0001\u000bJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&ø\u0001��\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lokhttp3/internal/http2/FlowControlListener;", "", "receivingConnectionWindowChanged", "", "windowCounter", "Lokhttp3/internal/http2/flowcontrol/WindowCounter;", "receivingStreamWindowChanged", "streamId", "", "bufferSize", "", "None", "okhttp"})
/* loaded from: target.jar:okhttp3/internal/http2/FlowControlListener.class */
public interface FlowControlListener {
    void receivingStreamWindowChanged(int i, @NotNull WindowCounter windowCounter, long j);

    void receivingConnectionWindowChanged(@NotNull WindowCounter windowCounter);

    /* compiled from: FlowControlListener.kt */
    @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\t\n��\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J \u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lokhttp3/internal/http2/FlowControlListener$None;", "Lokhttp3/internal/http2/FlowControlListener;", "()V", "receivingConnectionWindowChanged", "", "windowCounter", "Lokhttp3/internal/http2/flowcontrol/WindowCounter;", "receivingStreamWindowChanged", "streamId", "", "bufferSize", "", "okhttp"})
    /* loaded from: target.jar:okhttp3/internal/http2/FlowControlListener$None.class */
    public static final class None implements FlowControlListener {

        @NotNull
        public static final None INSTANCE = new None();

        private None() {
        }

        @Override // okhttp3.internal.http2.FlowControlListener
        public void receivingStreamWindowChanged(int streamId, @NotNull WindowCounter windowCounter, long bufferSize) {
            Intrinsics.checkNotNullParameter(windowCounter, "windowCounter");
        }

        @Override // okhttp3.internal.http2.FlowControlListener
        public void receivingConnectionWindowChanged(@NotNull WindowCounter windowCounter) {
            Intrinsics.checkNotNullParameter(windowCounter, "windowCounter");
        }
    }
}
