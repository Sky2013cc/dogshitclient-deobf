package kotlin.coroutines.cancellation;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.SourceDebugExtension;

/* compiled from: CancellationException.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"�� \n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0003\n��\u001a!\u0010��\u001a\u00060\u0001j\u0002`\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0087\b\u001a\u0017\u0010��\u001a\u00060\u0001j\u0002`\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0087\b*\u001a\b\u0007\u0010��\"\u00020\u00012\u00020\u0001B\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004¨\u0006\n"}, d2 = {"CancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlin/SinceKotlin;", "version", "1.4", "Lkotlin/coroutines/cancellation/CancellationException;", "message", "", "cause", "", "kotlin-stdlib"})
@SourceDebugExtension({"SMAP\nCancellationException.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CancellationException.kt\nkotlin/coroutines/cancellation/CancellationExceptionKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,22:1\n1#2:23\n*E\n"})
/* loaded from: target.jar:kotlin/coroutines/cancellation/CancellationExceptionKt.class */
public final class CancellationExceptionKt {
    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void CancellationException$annotations() {
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final CancellationException CancellationException(String message, Throwable cause) {
        CancellationException it = new CancellationException(message);
        it.initCause(cause);
        return it;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final CancellationException CancellationException(Throwable cause) {
        CancellationException it = new CancellationException(cause != null ? cause.toString() : null);
        it.initCause(cause);
        return it;
    }
}
