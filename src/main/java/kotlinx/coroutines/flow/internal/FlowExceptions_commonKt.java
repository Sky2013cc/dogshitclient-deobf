package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import org.jetbrains.annotations.NotNull;

/* compiled from: FlowExceptions.common.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u001a\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0014\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H��\u001a\u0011\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0081\b¨\u0006\b"}, d2 = {"checkOwnership", "", "Lkotlinx/coroutines/flow/internal/AbortFlowException;", "owner", "", "checkIndexOverflow", "", "index", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/flow/internal/FlowExceptions_commonKt.class */
public final class FlowExceptions_commonKt {
    public static final void checkOwnership(@NotNull AbortFlowException $this$checkOwnership, @NotNull Object owner) {
        if ($this$checkOwnership.owner != owner) {
            throw $this$checkOwnership;
        }
    }

    @PublishedApi
    public static final int checkIndexOverflow(int index) {
        if (index < 0) {
            throw new ArithmeticException("Index overflow has happened");
        }
        return index;
    }
}
