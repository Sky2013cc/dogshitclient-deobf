package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Job.kt */
@Deprecated(message = "This is internal API and may be removed in the future releases", level = DeprecationLevel.ERROR)
@InternalCoroutinesApi
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018��2\u00020\u0001J\u0011\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H'¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/ParentJob;", "Lkotlinx/coroutines/Job;", "getChildJobCancellationCause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "()Ljava/util/concurrent/CancellationException;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/ParentJob.class */
public interface ParentJob extends Job {
    @InternalCoroutinesApi
    @NotNull
    CancellationException getChildJobCancellationCause();

    /* compiled from: Job.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlinx/coroutines/ParentJob$DefaultImpls.class */
    public static final class DefaultImpls {
        @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
        @NotNull
        public static Job plus(@NotNull ParentJob $this, @NotNull Job other) {
            return Job.DefaultImpls.plus((Job) $this, other);
        }

        @NotNull
        public static CoroutineContext plus(@NotNull ParentJob $this, @NotNull CoroutineContext context) {
            return Job.DefaultImpls.plus($this, context);
        }

        @Nullable
        public static <E extends CoroutineContext.Element> E get(@NotNull ParentJob parentJob, @NotNull CoroutineContext.Key<E> key) {
            return (E) Job.DefaultImpls.get(parentJob, key);
        }

        public static <R> R fold(@NotNull ParentJob parentJob, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) Job.DefaultImpls.fold(parentJob, r, function2);
        }

        @NotNull
        public static CoroutineContext minusKey(@NotNull ParentJob $this, @NotNull CoroutineContext.Key<?> key) {
            return Job.DefaultImpls.minusKey($this, key);
        }
    }
}
