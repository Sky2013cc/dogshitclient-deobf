package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: CoroutineExceptionHandlerImpl.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0003\n��\b��\u0018��2\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/internal/DiagnosticCoroutineContextException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "context", "Lkotlin/coroutines/CoroutineContext;", Constants.CTOR, "(Lkotlin/coroutines/CoroutineContext;)V", "getLocalizedMessage", "", "fillInStackTrace", "", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/DiagnosticCoroutineContextException.class */
public final class DiagnosticCoroutineContextException extends RuntimeException {

    @Nullable
    private final transient CoroutineContext context;

    public DiagnosticCoroutineContextException(@NotNull CoroutineContext context) {
        this.context = context;
    }

    @Override // java.lang.Throwable
    @NotNull
    public String getLocalizedMessage() {
        return String.valueOf(this.context);
    }

    @Override // java.lang.Throwable
    @NotNull
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
