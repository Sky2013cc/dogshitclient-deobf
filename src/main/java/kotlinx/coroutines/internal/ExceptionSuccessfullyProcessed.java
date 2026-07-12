package kotlinx.coroutines.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: CoroutineExceptionHandlerImpl.common.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018��2\u00060\u0001j\u0002`\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/internal/ExceptionSuccessfullyProcessed;", "Ljava/lang/Exception;", "Lkotlin/Exception;", Constants.CTOR, "()V", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/ExceptionSuccessfullyProcessed.class */
public final class ExceptionSuccessfullyProcessed extends Exception {

    @NotNull
    public static final ExceptionSuccessfullyProcessed INSTANCE = new ExceptionSuccessfullyProcessed();

    private ExceptionSuccessfullyProcessed() {
    }
}
