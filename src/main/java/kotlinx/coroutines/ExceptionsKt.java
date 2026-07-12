package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Exceptions.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0003\n��\u001a\u001e\u0010��\u001a\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\n\u0010��\"\u00020\u00012\u00020\u0001¨\u0006\u0007"}, d2 = {"CancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "message", "", "cause", "", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nExceptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Exceptions.kt\nkotlinx/coroutines/ExceptionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,77:1\n1#2:78\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/ExceptionsKt.class */
public final class ExceptionsKt {
    @NotNull
    public static final CancellationException CancellationException(@Nullable String message, @Nullable Throwable cause) {
        CancellationException $this$CancellationException_u24lambda_u240 = new CancellationException(message);
        $this$CancellationException_u24lambda_u240.initCause(cause);
        return $this$CancellationException_u24lambda_u240;
    }
}
