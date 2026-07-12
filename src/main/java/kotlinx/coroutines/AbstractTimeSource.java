package kotlinx.coroutines;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: AbstractTimeSource.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n��\b \u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00060\bj\u0002`\t2\n\u0010\n\u001a\u00060\bj\u0002`\tH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\fH&J\b\u0010\u000e\u001a\u00020\fH&J\b\u0010\u000f\u001a\u00020\fH&J\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0005H&J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H&¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/AbstractTimeSource;", "", Constants.CTOR, "()V", "currentTimeMillis", "", "nanoTime", "wrapTask", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_BLOCK, "trackTask", "", "unTrackTask", "registerTimeLoopThread", "unregisterTimeLoopThread", "parkNanos", "blocker", "nanos", "unpark", "thread", "Ljava/lang/Thread;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/AbstractTimeSource.class */
public abstract class AbstractTimeSource {
    public abstract long currentTimeMillis();

    public abstract long nanoTime();

    @NotNull
    public abstract Runnable wrapTask(@NotNull Runnable runnable);

    public abstract void trackTask();

    public abstract void unTrackTask();

    public abstract void registerTimeLoopThread();

    public abstract void unregisterTimeLoopThread();

    public abstract void parkNanos(@NotNull Object obj, long j);

    public abstract void unpark(@NotNull Thread thread);
}
