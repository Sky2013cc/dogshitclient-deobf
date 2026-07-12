package okhttp3;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: ConnectionListener.kt */
@ExperimentalOkHttpApi
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b'\u0018�� \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J$\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0015"}, d2 = {"Lokhttp3/ConnectionListener;", "", "()V", "connectEnd", "", "connection", "Lokhttp3/Connection;", "route", "Lokhttp3/Route;", "call", "Lokhttp3/Call;", "connectFailed", "failure", "Ljava/io/IOException;", "Lokio/IOException;", "connectStart", "connectionAcquired", "connectionClosed", "connectionReleased", "noNewExchanges", "Companion", "okhttp"})
/* loaded from: target.jar:okhttp3/ConnectionListener.class */
public abstract class ConnectionListener {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final ConnectionListener NONE = new ConnectionListener() { // from class: okhttp3.ConnectionListener$Companion$NONE$1
    };

    public void connectStart(@NotNull Route route, @NotNull Call call) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(call, "call");
    }

    public void connectFailed(@NotNull Route route, @NotNull Call call, @NotNull IOException failure) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(failure, "failure");
    }

    public void connectEnd(@NotNull Connection connection, @NotNull Route route, @NotNull Call call) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(call, "call");
    }

    public void connectionClosed(@NotNull Connection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
    }

    public void connectionAcquired(@NotNull Connection connection, @NotNull Call call) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(call, "call");
    }

    public void connectionReleased(@NotNull Connection connection, @NotNull Call call) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(call, "call");
    }

    public void noNewExchanges(@NotNull Connection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
    }

    /* compiled from: ConnectionListener.kt */
    @ExperimentalOkHttpApi
    @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lokhttp3/ConnectionListener$Companion;", "", "()V", "NONE", "Lokhttp3/ConnectionListener;", "getNONE", "()Lokhttp3/ConnectionListener;", "okhttp"})
    /* loaded from: target.jar:okhttp3/ConnectionListener$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final ConnectionListener getNONE() {
            return ConnectionListener.NONE;
        }
    }
}
