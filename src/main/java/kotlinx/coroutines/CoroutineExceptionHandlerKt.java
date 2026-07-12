package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.CoroutineExceptionHandlerImpl_commonKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: CoroutineExceptionHandler.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\"\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u0018\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H��\u001a%\u0010\t\u001a\u00020\n2\u001a\b\u0004\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\fH\u0086\b¨\u0006\r"}, d2 = {"handleCoroutineException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "handlerException", "originalException", "thrownException", "CoroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handler", "Lkotlin/Function2;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/CoroutineExceptionHandlerKt.class */
public final class CoroutineExceptionHandlerKt {
    @InternalCoroutinesApi
    public static final void handleCoroutineException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        Throwable reportException = exception instanceof DispatchException ? ((DispatchException) exception).getCause() : exception;
        try {
            CoroutineExceptionHandler it = (CoroutineExceptionHandler) context.get(CoroutineExceptionHandler.Key);
            if (it != null) {
                it.handleException(context, reportException);
            } else {
                CoroutineExceptionHandlerImpl_commonKt.handleUncaughtCoroutineException(context, reportException);
            }
        } catch (Throwable t) {
            CoroutineExceptionHandlerImpl_commonKt.handleUncaughtCoroutineException(context, handlerException(reportException, t));
        }
    }

    @NotNull
    public static final Throwable handlerException(@NotNull Throwable originalException, @NotNull Throwable thrownException) {
        if (originalException == thrownException) {
            return originalException;
        }
        RuntimeException $this$handlerException_u24lambda_u241 = new RuntimeException("Exception while trying to handle coroutine exception", thrownException);
        kotlin.ExceptionsKt.addSuppressed($this$handlerException_u24lambda_u241, originalException);
        return $this$handlerException_u24lambda_u241;
    }

    @NotNull
    public static final CoroutineExceptionHandler CoroutineExceptionHandler(@NotNull Function2<? super CoroutineContext, ? super Throwable, Unit> function2) {
        return new CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1(function2, CoroutineExceptionHandler.Key);
    }
}
