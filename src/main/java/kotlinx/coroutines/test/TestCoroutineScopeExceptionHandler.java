package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineExceptionHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TestCoroutineScope.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\bb\u0018��2\u00020\u0001¨\u0006\u0002"}, d2 = {"Lkotlinx/coroutines/test/TestCoroutineScopeExceptionHandler;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "kotlinx-coroutines-test"})
/* loaded from: target.jar:kotlinx/coroutines/test/TestCoroutineScopeExceptionHandler.class */
interface TestCoroutineScopeExceptionHandler extends CoroutineExceptionHandler {

    /* compiled from: TestCoroutineScope.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlinx/coroutines/test/TestCoroutineScopeExceptionHandler$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static <E extends CoroutineContext.Element> E get(@NotNull TestCoroutineScopeExceptionHandler testCoroutineScopeExceptionHandler, @NotNull CoroutineContext.Key<E> key) {
            return (E) CoroutineExceptionHandler.DefaultImpls.get(testCoroutineScopeExceptionHandler, key);
        }

        public static <R> R fold(@NotNull TestCoroutineScopeExceptionHandler testCoroutineScopeExceptionHandler, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) CoroutineExceptionHandler.DefaultImpls.fold(testCoroutineScopeExceptionHandler, r, function2);
        }

        @NotNull
        public static CoroutineContext minusKey(@NotNull TestCoroutineScopeExceptionHandler $this, @NotNull CoroutineContext.Key<?> key) {
            return CoroutineExceptionHandler.DefaultImpls.minusKey($this, key);
        }

        @NotNull
        public static CoroutineContext plus(@NotNull TestCoroutineScopeExceptionHandler $this, @NotNull CoroutineContext context) {
            return CoroutineExceptionHandler.DefaultImpls.plus($this, context);
        }
    }
}
