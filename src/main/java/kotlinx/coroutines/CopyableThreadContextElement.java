package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.ThreadContextElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ThreadContextElement.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\bg\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0��H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/CopyableThreadContextElement;", "S", "Lkotlinx/coroutines/ThreadContextElement;", "copyForChild", "mergeForChild", "Lkotlin/coroutines/CoroutineContext;", "overwritingElement", "Lkotlin/coroutines/CoroutineContext$Element;", "kotlinx-coroutines-core"})
@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
/* loaded from: target.jar:kotlinx/coroutines/CopyableThreadContextElement.class */
public interface CopyableThreadContextElement<S> extends ThreadContextElement<S> {
    @NotNull
    CopyableThreadContextElement<S> copyForChild();

    @NotNull
    CoroutineContext mergeForChild(@NotNull CoroutineContext.Element element);

    /* compiled from: ThreadContextElement.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlinx/coroutines/CopyableThreadContextElement$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static <S, E extends CoroutineContext.Element> E get(@NotNull CopyableThreadContextElement<S> copyableThreadContextElement, @NotNull CoroutineContext.Key<E> key) {
            return (E) ThreadContextElement.DefaultImpls.get(copyableThreadContextElement, key);
        }

        public static <S, R> R fold(@NotNull CopyableThreadContextElement<S> copyableThreadContextElement, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) ThreadContextElement.DefaultImpls.fold(copyableThreadContextElement, r, function2);
        }

        @NotNull
        public static <S> CoroutineContext minusKey(@NotNull CopyableThreadContextElement<S> copyableThreadContextElement, @NotNull CoroutineContext.Key<?> key) {
            return ThreadContextElement.DefaultImpls.minusKey(copyableThreadContextElement, key);
        }

        @NotNull
        public static <S> CoroutineContext plus(@NotNull CopyableThreadContextElement<S> copyableThreadContextElement, @NotNull CoroutineContext context) {
            return ThreadContextElement.DefaultImpls.plus(copyableThreadContextElement, context);
        }
    }
}
