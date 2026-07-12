package kotlinx.coroutines.test;

import kotlin.Metadata;

/* compiled from: TestDispatcher.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\u001a\u0010\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\u0004"}, d2 = {"cancellableRunnableIsCancelled", "", "runnable", "Lkotlinx/coroutines/test/CancellableContinuationRunnable;", "kotlinx-coroutines-test"})
/* loaded from: target.jar:kotlinx/coroutines/test/TestDispatcherKt.class */
public final class TestDispatcherKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean cancellableRunnableIsCancelled(CancellableContinuationRunnable runnable) {
        return !runnable.continuation.isActive();
    }
}
