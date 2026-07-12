package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;

/* compiled from: Executors.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0011\u0010\u0003\u001a\u00020\u0001*\u00020\u0004H\u0007¢\u0006\u0002\b\u0005\u001a\u0011\u0010\u0003\u001a\u00020\u0006*\u00020\u0007H\u0007¢\u0006\u0002\b\u0005\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\u0006*\u0010\b\u0007\u0010��\"\u00020\u00012\u00020\u0001B\u0002\b\u0002¨\u0006\t"}, d2 = {"CloseableCoroutineDispatcher", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/ExperimentalCoroutinesApi;", "asCoroutineDispatcher", "Ljava/util/concurrent/ExecutorService;", "from", "Lkotlinx/coroutines/CoroutineDispatcher;", "Ljava/util/concurrent/Executor;", "asExecutor", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/ExecutorsKt.class */
public final class ExecutorsKt {
    @ExperimentalCoroutinesApi
    public static /* synthetic */ void CloseableCoroutineDispatcher$annotations() {
    }

    @JvmName(name = "from")
    @NotNull
    public static final ExecutorCoroutineDispatcher from(@NotNull ExecutorService $this$asCoroutineDispatcher) {
        return new ExecutorCoroutineDispatcherImpl($this$asCoroutineDispatcher);
    }

    @JvmName(name = "from")
    @NotNull
    public static final CoroutineDispatcher from(@NotNull Executor $this$asCoroutineDispatcher) {
        DispatcherExecutor dispatcherExecutor = $this$asCoroutineDispatcher instanceof DispatcherExecutor ? (DispatcherExecutor) $this$asCoroutineDispatcher : null;
        if (dispatcherExecutor != null) {
            CoroutineDispatcher coroutineDispatcher = dispatcherExecutor.dispatcher;
            if (coroutineDispatcher != null) {
                return coroutineDispatcher;
            }
        }
        return new ExecutorCoroutineDispatcherImpl($this$asCoroutineDispatcher);
    }

    @NotNull
    public static final Executor asExecutor(@NotNull CoroutineDispatcher $this$asExecutor) {
        ExecutorCoroutineDispatcher executorCoroutineDispatcher = $this$asExecutor instanceof ExecutorCoroutineDispatcher ? (ExecutorCoroutineDispatcher) $this$asExecutor : null;
        if (executorCoroutineDispatcher != null) {
            Executor executor = executorCoroutineDispatcher.getExecutor();
            if (executor != null) {
                return executor;
            }
        }
        return new DispatcherExecutor($this$asExecutor);
    }
}
