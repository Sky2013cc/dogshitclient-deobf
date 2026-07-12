package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: TestDispatcher.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
/* loaded from: target.jar:kotlinx/coroutines/test/TestDispatcher$scheduleResumeAfterDelay$handle$1.class */
/* synthetic */ class TestDispatcher$scheduleResumeAfterDelay$handle$1 extends FunctionReferenceImpl implements Function1<CancellableContinuationRunnable, Boolean> {
    public static final TestDispatcher$scheduleResumeAfterDelay$handle$1 INSTANCE = new TestDispatcher$scheduleResumeAfterDelay$handle$1();

    TestDispatcher$scheduleResumeAfterDelay$handle$1() {
        super(1, TestDispatcherKt.class, "cancellableRunnableIsCancelled", "cancellableRunnableIsCancelled(Lkotlinx/coroutines/test/CancellableContinuationRunnable;)Z", 1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(CancellableContinuationRunnable p0) {
        boolean cancellableRunnableIsCancelled;
        cancellableRunnableIsCancelled = TestDispatcherKt.cancellableRunnableIsCancelled(p0);
        return Boolean.valueOf(cancellableRunnableIsCancelled);
    }
}
