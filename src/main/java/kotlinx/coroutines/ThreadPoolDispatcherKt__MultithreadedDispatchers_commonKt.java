package kotlinx.coroutines;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: MultithreadedDispatchers.common.kt */
@Metadata(mv = {2, 1, 0}, k = 5, xi = 48, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0019\u0010��\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"newSingleThreadContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/CloseableCoroutineDispatcher;", "name", "", "(Ljava/lang/String;)Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "kotlinx-coroutines-core"}, xs = "kotlinx/coroutines/ThreadPoolDispatcherKt")
/* loaded from: target.jar:kotlinx/coroutines/ThreadPoolDispatcherKt__MultithreadedDispatchers_commonKt.class */
final /* synthetic */ class ThreadPoolDispatcherKt__MultithreadedDispatchers_commonKt {
    @ExperimentalCoroutinesApi
    @DelicateCoroutinesApi
    @NotNull
    public static final ExecutorCoroutineDispatcher newSingleThreadContext(@NotNull String name) {
        return ThreadPoolDispatcherKt.newFixedThreadPoolContext(1, name);
    }
}
