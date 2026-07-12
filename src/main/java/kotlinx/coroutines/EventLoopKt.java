package kotlinx.coroutines;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.coroutines.scheduling.CoroutineScheduler;
import org.jetbrains.annotations.NotNull;

/* compiled from: EventLoop.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��&\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\u001a\b\u0010��\u001a\u00020\u0001H��\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0019\u0010\u0004\u001a\u00020\u00052\u000e\b\u0004\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0080\b\u001a\b\u0010\b\u001a\u00020\u0003H\u0001\u001a\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0001¨\u0006\f"}, d2 = {"createEventLoop", "Lkotlinx/coroutines/EventLoop;", "processNextEventInCurrentThread", "", "platformAutoreleasePool", "", Constants.ATTR_BLOCK, "Lkotlin/Function0;", "runSingleTaskFromCurrentSystemDispatcher", "isIoDispatcherThread", "", "Ljava/lang/Thread;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/EventLoopKt.class */
public final class EventLoopKt {
    @NotNull
    public static final EventLoop createEventLoop() {
        return new BlockingEventLoop(Thread.currentThread());
    }

    @InternalCoroutinesApi
    public static final long processNextEventInCurrentThread() {
        EventLoop currentOrNull$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
        return currentOrNull$kotlinx_coroutines_core != null ? currentOrNull$kotlinx_coroutines_core.processNextEvent() : LongCompanionObject.MAX_VALUE;
    }

    public static final void platformAutoreleasePool(@NotNull Function0<Unit> function0) {
        function0.invoke();
    }

    @InternalCoroutinesApi
    @PublishedApi
    @DelicateCoroutinesApi
    public static final long runSingleTaskFromCurrentSystemDispatcher() {
        Thread thread = Thread.currentThread();
        if (thread instanceof CoroutineScheduler.Worker) {
            return ((CoroutineScheduler.Worker) thread).runSingleTask();
        }
        throw new IllegalStateException("Expected CoroutineScheduler.Worker, but got " + thread);
    }

    @InternalCoroutinesApi
    @PublishedApi
    @DelicateCoroutinesApi
    public static final boolean isIoDispatcherThread(@NotNull Thread $this$isIoDispatcherThread) {
        if ($this$isIoDispatcherThread instanceof CoroutineScheduler.Worker) {
            return ((CoroutineScheduler.Worker) $this$isIoDispatcherThread).isIo();
        }
        return false;
    }
}
