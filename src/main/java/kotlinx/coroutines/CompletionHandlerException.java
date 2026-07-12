package kotlinx.coroutines;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Exceptions.common.kt */
@InternalCoroutinesApi
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0007\u0018��2\u00060\u0001j\u0002`\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/CompletionHandlerException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "message", "", "cause", "", Constants.CTOR, "(Ljava/lang/String;Ljava/lang/Throwable;)V", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/CompletionHandlerException.class */
public final class CompletionHandlerException extends RuntimeException {
    public CompletionHandlerException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }
}
