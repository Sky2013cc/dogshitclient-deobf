package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Select.kt */
@InternalCoroutinesApi
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\bw\u0018��*\u0006\b��\u0010\u0001 ��2\u00020\u0002J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u0002H&J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&J\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002H&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0001\u0011¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/selects/SelectInstance;", "R", "", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "trySelect", "", "clauseObject", "result", "disposeOnCompletion", "", "disposableHandle", "Lkotlinx/coroutines/DisposableHandle;", "selectInRegistrationPhase", "internalResult", "Lkotlinx/coroutines/selects/SelectInstanceInternal;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/selects/SelectInstance.class */
public interface SelectInstance<R> {
    @NotNull
    CoroutineContext getContext();

    boolean trySelect(@NotNull Object obj, @Nullable Object obj2);

    void disposeOnCompletion(@NotNull DisposableHandle disposableHandle);

    void selectInRegistrationPhase(@Nullable Object obj);
}
