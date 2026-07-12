package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.SubclassOptInRequired;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.selects.SelectClause1;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Deferred.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n��\bg\u0018��*\u0006\b��\u0010\u0001 \u00012\u00020\u0002J\u000e\u0010\u0003\u001a\u00028��H¦@¢\u0006\u0002\u0010\u0004J\r\u0010\t\u001a\u00028��H'¢\u0006\u0002\u0010\nJ\n\u0010\u000b\u001a\u0004\u0018\u00010\fH'R\u0018\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/Deferred;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/Job;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onAwait", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "getCompleted", "()Ljava/lang/Object;", "getCompletionExceptionOrNull", "", "kotlinx-coroutines-core"})
@SubclassOptInRequired(markerClass = {InternalForInheritanceCoroutinesApi.class})
/* loaded from: target.jar:kotlinx/coroutines/Deferred.class */
public interface Deferred<T> extends Job {
    @Nullable
    Object await(@NotNull Continuation<? super T> continuation);

    @NotNull
    SelectClause1<T> getOnAwait();

    @ExperimentalCoroutinesApi
    T getCompleted();

    @ExperimentalCoroutinesApi
    @Nullable
    Throwable getCompletionExceptionOrNull();

    /* compiled from: Deferred.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlinx/coroutines/Deferred$DefaultImpls.class */
    public static final class DefaultImpls {
        @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
        @NotNull
        public static <T> Job plus(@NotNull Deferred<? extends T> deferred, @NotNull Job other) {
            return Job.DefaultImpls.plus((Job) deferred, other);
        }

        @NotNull
        public static <T> CoroutineContext plus(@NotNull Deferred<? extends T> deferred, @NotNull CoroutineContext context) {
            return Job.DefaultImpls.plus(deferred, context);
        }

        @Nullable
        public static <T, E extends CoroutineContext.Element> E get(@NotNull Deferred<? extends T> deferred, @NotNull CoroutineContext.Key<E> key) {
            return (E) Job.DefaultImpls.get(deferred, key);
        }

        public static <T, R> R fold(@NotNull Deferred<? extends T> deferred, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) Job.DefaultImpls.fold(deferred, r, function2);
        }

        @NotNull
        public static <T> CoroutineContext minusKey(@NotNull Deferred<? extends T> deferred, @NotNull CoroutineContext.Key<?> key) {
            return Job.DefaultImpls.minusKey(deferred, key);
        }
    }
}
