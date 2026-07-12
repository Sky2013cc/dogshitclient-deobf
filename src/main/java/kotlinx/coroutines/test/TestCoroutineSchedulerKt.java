package kotlinx.coroutines.test;

import com.formdev.flatlaf.FlatClientProperties;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: TestCoroutineScheduler.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��>\n��\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\b\u0010��\u001a\u00020\u0001H\u0002\u001a\b\u0010\u0002\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH��\u001a:\u0010\r\u001a\u00020\u000e\"\u0012\b��\u0010\u000f*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u000f0\u0011*\b\u0012\u0004\u0012\u0002H\u000f0\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u00020\u000e0\u0014H\u0002¨\u0006\u0015"}, d2 = {"currentTimeAheadOfEvents", "", "invalidSchedulerState", "addClamping", "", "a", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "checkSchedulerInContext", "", "scheduler", "Lkotlinx/coroutines/test/TestCoroutineScheduler;", "context", "Lkotlin/coroutines/CoroutineContext;", FlatClientProperties.TABBED_PANE_TAB_ROTATION_NONE, "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "predicate", "Lkotlin/Function1;", "kotlinx-coroutines-test"})
@SourceDebugExtension({"SMAP\nTestCoroutineScheduler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TestCoroutineScheduler.kt\nkotlinx/coroutines/test/TestCoroutineSchedulerKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,277:1\n1#2:278\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/test/TestCoroutineSchedulerKt.class */
public final class TestCoroutineSchedulerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Void currentTimeAheadOfEvents() {
        invalidSchedulerState();
        throw new KotlinNothingValueException();
    }

    private static final Void invalidSchedulerState() {
        throw new IllegalStateException("The test scheduler entered an invalid state. Please report this at https://github.com/Kotlin/kotlinx.coroutines/issues.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long addClamping(long a, long b) {
        long it = a + b;
        return it >= 0 ? it : LongCompanionObject.MAX_VALUE;
    }

    public static final void checkSchedulerInContext(@NotNull TestCoroutineScheduler scheduler, @NotNull CoroutineContext context) {
        TestCoroutineScheduler it = (TestCoroutineScheduler) context.get(TestCoroutineScheduler.Key);
        if (it != null) {
            if (!(it == scheduler)) {
                throw new IllegalStateException("Detected use of different schedulers. If you need to use several test coroutine dispatchers, create one `TestCoroutineScheduler` and pass it to each of them.".toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends ThreadSafeHeapNode & Comparable<? super T>> boolean none(ThreadSafeHeap<T> threadSafeHeap, Function1<? super T, Boolean> function1) {
        return threadSafeHeap.find(function1) == null;
    }
}
