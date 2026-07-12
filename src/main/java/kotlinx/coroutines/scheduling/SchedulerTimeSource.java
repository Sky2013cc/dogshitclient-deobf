package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import org.spongepowered.asm.util.Constants;

/* compiled from: Tasks.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\t\n��\b \u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/scheduling/SchedulerTimeSource;", "", Constants.CTOR, "()V", "nanoTime", "", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/scheduling/SchedulerTimeSource.class */
public abstract class SchedulerTimeSource {
    public abstract long nanoTime();
}
