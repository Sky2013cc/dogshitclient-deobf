package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TestDispatcher.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018��2\u00060\u0001j\u0002`\u0002B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\n\u001a\u00020\u0005H\u0016R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/test/CancellableContinuationRunnable;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", Constants.CTOR, "(Lkotlinx/coroutines/CancellableContinuation;Lkotlinx/coroutines/CoroutineDispatcher;)V", "run", "kotlinx-coroutines-test"})
@SourceDebugExtension({"SMAP\nTestDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TestDispatcher.kt\nkotlinx/coroutines/test/CancellableContinuationRunnable\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,65:1\n1#2:66\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/test/CancellableContinuationRunnable.class */
public final class CancellableContinuationRunnable implements Runnable {

    @JvmField
    @NotNull
    public final CancellableContinuation<Unit> continuation;

    @NotNull
    private final CoroutineDispatcher dispatcher;

    /* JADX WARN: Multi-variable type inference failed */
    public CancellableContinuationRunnable(@NotNull CancellableContinuation<? super Unit> cancellableContinuation, @NotNull CoroutineDispatcher dispatcher) {
        this.continuation = cancellableContinuation;
        this.dispatcher = dispatcher;
    }

    @Override // java.lang.Runnable
    public void run() {
        CoroutineDispatcher $this$run_u24lambda_u241 = this.dispatcher;
        CancellableContinuation $this$run_u24lambda_u241_u24lambda_u240 = this.continuation;
        $this$run_u24lambda_u241_u24lambda_u240.resumeUndispatched($this$run_u24lambda_u241, Unit.INSTANCE);
    }
}
