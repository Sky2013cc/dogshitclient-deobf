package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.spongepowered.asm.util.Constants;

/* compiled from: Tasks.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018��2\u00060\u0001j\u0002`\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007¢\u0006\u0004\b\b\u0010\tB\t\b\u0010¢\u0006\u0004\b\b\u0010\nR\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��R\u0016\u0010\u0005\u001a\u00060\u0006j\u0002`\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/scheduling/Task;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "submissionTime", "", "taskContext", "", "Lkotlinx/coroutines/scheduling/TaskContext;", Constants.CTOR, "(JZ)V", "()V", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/scheduling/Task.class */
public abstract class Task implements Runnable {

    @JvmField
    public long submissionTime;

    @JvmField
    public boolean taskContext;

    public Task(long submissionTime, boolean taskContext) {
        this.submissionTime = submissionTime;
        this.taskContext = taskContext;
    }

    public Task() {
        this(0L, false);
    }
}
