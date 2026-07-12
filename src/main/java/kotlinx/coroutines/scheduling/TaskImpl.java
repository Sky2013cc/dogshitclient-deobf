package kotlinx.coroutines.scheduling;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.DebugStringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Tasks.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\b\u0002\u0018��2\u00020\u0001B'\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\u0010\u0007\u001a\u00060\bj\u0002`\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00060\u0003j\u0002`\u00048\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/scheduling/TaskImpl;", "Lkotlinx/coroutines/scheduling/Task;", Constants.ATTR_BLOCK, "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "submissionTime", "", "taskContext", "", "Lkotlinx/coroutines/scheduling/TaskContext;", org.spongepowered.asm.util.Constants.CTOR, "(Ljava/lang/Runnable;JZ)V", "run", "", "toString", "", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/scheduling/TaskImpl.class */
public final class TaskImpl extends Task {

    @JvmField
    @NotNull
    public final Runnable block;

    public TaskImpl(@NotNull Runnable block, long submissionTime, boolean taskContext) {
        super(submissionTime, taskContext);
        this.block = block;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.block.run();
    }

    @NotNull
    public String toString() {
        String taskContextString;
        StringBuilder append = new StringBuilder().append("Task[").append(DebugStringsKt.getClassSimpleName(this.block)).append('@').append(DebugStringsKt.getHexAddress(this.block)).append(", ").append(this.submissionTime).append(", ");
        taskContextString = TasksKt.taskContextString(this.taskContext);
        return append.append(taskContextString).append(']').toString();
    }
}
