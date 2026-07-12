package okhttp3.internal.concurrent;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilJvmKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TaskQueue.kt */
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��^\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\b\u0018��2\u00020\u0001:\u00017B\u0017\b��\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010%\u001a\u00020&J\r\u0010'\u001a\u00020\u000eH��¢\u0006\u0002\b(J0\u0010)\u001a\u00020&2\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u000e2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020&0.J\u0006\u0010/\u001a\u000200J&\u00101\u001a\u00020&2\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010*\u001a\u00020+2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020+0.J\u0018\u00101\u001a\u00020&2\u0006\u00102\u001a\u00020\b2\b\b\u0002\u0010*\u001a\u00020+J%\u00103\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\b2\u0006\u0010*\u001a\u00020+2\u0006\u00104\u001a\u00020\u000eH��¢\u0006\u0002\b5J\u0006\u0010 \u001a\u00020&J\b\u00106\u001a\u00020\u0005H\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0014X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0016R\u001a\u0010 \u001a\u00020\u000eX\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b#\u0010$¨\u00068"}, d2 = {"Lokhttp3/internal/concurrent/TaskQueue;", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "name", "", "(Lokhttp3/internal/concurrent/TaskRunner;Ljava/lang/String;)V", "activeTask", "Lokhttp3/internal/concurrent/Task;", "getActiveTask$okhttp", "()Lokhttp3/internal/concurrent/Task;", "setActiveTask$okhttp", "(Lokhttp3/internal/concurrent/Task;)V", "cancelActiveTask", "", "getCancelActiveTask$okhttp", "()Z", "setCancelActiveTask$okhttp", "(Z)V", "futureTasks", "", "getFutureTasks$okhttp", "()Ljava/util/List;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "getName$okhttp", "()Ljava/lang/String;", "scheduledTasks", "", "getScheduledTasks", "shutdown", "getShutdown$okhttp", "setShutdown$okhttp", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "cancelAll", "", "cancelAllAndDecide", "cancelAllAndDecide$okhttp", "execute", "delayNanos", "", "cancelable", Constants.ATTR_BLOCK, "Lkotlin/Function0;", "idleLatch", "Ljava/util/concurrent/CountDownLatch;", "schedule", "task", "scheduleAndDecide", "recurrence", "scheduleAndDecide$okhttp", "toString", "AwaitIdleTask", "okhttp"})
@SourceDebugExtension({"SMAP\nTaskQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskQueue.kt\nokhttp3/internal/concurrent/TaskQueue\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 TaskLogger.kt\nokhttp3/internal/concurrent/TaskLoggerKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n*L\n1#1,248:1\n1#2:249\n26#3,4:250\n26#3,4:254\n26#3,4:258\n26#3,4:262\n26#3,4:281\n350#4,7:266\n360#5,4:273\n360#5,4:277\n*S KotlinDebug\n*F\n+ 1 TaskQueue.kt\nokhttp3/internal/concurrent/TaskQueue\n*L\n72#1:250,4\n75#1:254,4\n180#1:258,4\n186#1:262,4\n238#1:281,4\n195#1:266,7\n209#1:273,4\n219#1:277,4\n*E\n"})
/* loaded from: target.jar:okhttp3/internal/concurrent/TaskQueue.class */
public final class TaskQueue {

    @NotNull
    private final TaskRunner taskRunner;

    @NotNull
    private final String name;

    @NotNull
    private final ReentrantLock lock;
    private boolean shutdown;

    @Nullable
    private Task activeTask;

    @NotNull
    private final List<Task> futureTasks;
    private boolean cancelActiveTask;

    public TaskQueue(@NotNull TaskRunner taskRunner, @NotNull String name) {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        Intrinsics.checkNotNullParameter(name, "name");
        this.taskRunner = taskRunner;
        this.name = name;
        this.lock = new ReentrantLock();
        this.futureTasks = new ArrayList();
    }

    @NotNull
    public final TaskRunner getTaskRunner$okhttp() {
        return this.taskRunner;
    }

    @NotNull
    public final String getName$okhttp() {
        return this.name;
    }

    @NotNull
    public final ReentrantLock getLock() {
        return this.lock;
    }

    public final boolean getShutdown$okhttp() {
        return this.shutdown;
    }

    public final void setShutdown$okhttp(boolean z) {
        this.shutdown = z;
    }

    @Nullable
    public final Task getActiveTask$okhttp() {
        return this.activeTask;
    }

    public final void setActiveTask$okhttp(@Nullable Task task) {
        this.activeTask = task;
    }

    @NotNull
    public final List<Task> getFutureTasks$okhttp() {
        return this.futureTasks;
    }

    public final boolean getCancelActiveTask$okhttp() {
        return this.cancelActiveTask;
    }

    public final void setCancelActiveTask$okhttp(boolean z) {
        this.cancelActiveTask = z;
    }

    @NotNull
    public final List<Task> getScheduledTasks() {
        ReentrantLock lock = this.taskRunner.getLock();
        lock.lock();
        try {
            List<Task> list = CollectionsKt.toList(this.futureTasks);
            lock.unlock();
            return list;
        } catch (Throwable th) {
            lock.unlock();
            throw th;
        }
    }

    public static /* synthetic */ void schedule$default(TaskQueue taskQueue, Task task, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        taskQueue.schedule(task, j);
    }

    public final void schedule(@NotNull Task task, long delayNanos) {
        Intrinsics.checkNotNullParameter(task, "task");
        ReentrantLock lock = this.taskRunner.getLock();
        lock.lock();
        try {
            if (this.shutdown) {
                if (task.getCancelable()) {
                    Logger $this$taskLog$iv = this.taskRunner.getLogger$okhttp();
                    if ($this$taskLog$iv.isLoggable(Level.FINE)) {
                        TaskLoggerKt.log($this$taskLog$iv, task, this, "schedule canceled (queue is shutdown)");
                    }
                    return;
                } else {
                    Logger $this$taskLog$iv2 = this.taskRunner.getLogger$okhttp();
                    if ($this$taskLog$iv2.isLoggable(Level.FINE)) {
                        TaskLoggerKt.log($this$taskLog$iv2, task, this, "schedule failed (queue is shutdown)");
                    }
                    throw new RejectedExecutionException();
                }
            }
            if (scheduleAndDecide$okhttp(task, delayNanos, false)) {
                this.taskRunner.kickCoordinator$okhttp(this);
            }
            Unit unit = Unit.INSTANCE;
            lock.unlock();
        } finally {
            lock.unlock();
        }
    }

    public static /* synthetic */ void schedule$default(TaskQueue taskQueue, String str, long j, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        taskQueue.schedule(str, j, function0);
    }

    public final void schedule(@NotNull final String name, long delayNanos, @NotNull final Function0<Long> block) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        schedule(new Task(name) { // from class: okhttp3.internal.concurrent.TaskQueue$schedule$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                boolean z = false;
                int i = 2;
                DefaultConstructorMarker defaultConstructorMarker = null;
            }

            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                return block.invoke().longValue();
            }
        }, delayNanos);
    }

    public static /* synthetic */ void execute$default(TaskQueue taskQueue, String str, long j, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        if ((i & 4) != 0) {
            z = true;
        }
        taskQueue.execute(str, j, z, function0);
    }

    public final void execute(@NotNull final String name, long delayNanos, final boolean cancelable, @NotNull final Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        schedule(new Task(name, cancelable) { // from class: okhttp3.internal.concurrent.TaskQueue$execute$1
            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                block.invoke();
                return -1L;
            }
        }, delayNanos);
    }

    @NotNull
    public final CountDownLatch idleLatch() {
        ReentrantLock lock = this.taskRunner.getLock();
        lock.lock();
        try {
            if (this.activeTask == null && this.futureTasks.isEmpty()) {
                CountDownLatch countDownLatch = new CountDownLatch(0);
                lock.unlock();
                return countDownLatch;
            }
            Task existingTask = this.activeTask;
            if (existingTask instanceof AwaitIdleTask) {
                CountDownLatch latch = ((AwaitIdleTask) existingTask).getLatch();
                lock.unlock();
                return latch;
            }
            for (Task futureTask : this.futureTasks) {
                if (futureTask instanceof AwaitIdleTask) {
                    CountDownLatch latch2 = ((AwaitIdleTask) futureTask).getLatch();
                    lock.unlock();
                    return latch2;
                }
            }
            AwaitIdleTask newTask = new AwaitIdleTask();
            if (scheduleAndDecide$okhttp(newTask, 0L, false)) {
                this.taskRunner.kickCoordinator$okhttp(this);
            }
            CountDownLatch latch3 = newTask.getLatch();
            lock.unlock();
            return latch3;
        } catch (Throwable th) {
            lock.unlock();
            throw th;
        }
    }

    /* compiled from: TaskQueue.kt */
    @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n��\b\u0002\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lokhttp3/internal/concurrent/TaskQueue$AwaitIdleTask;", "Lokhttp3/internal/concurrent/Task;", "()V", "latch", "Ljava/util/concurrent/CountDownLatch;", "getLatch", "()Ljava/util/concurrent/CountDownLatch;", "runOnce", "", "okhttp"})
    /* loaded from: target.jar:okhttp3/internal/concurrent/TaskQueue$AwaitIdleTask.class */
    private static final class AwaitIdleTask extends Task {

        @NotNull
        private final CountDownLatch latch;

        public AwaitIdleTask() {
            super(_UtilJvmKt.okHttpName + " awaitIdle", false);
            this.latch = new CountDownLatch(1);
        }

        @NotNull
        public final CountDownLatch getLatch() {
            return this.latch;
        }

        @Override // okhttp3.internal.concurrent.Task
        public long runOnce() {
            this.latch.countDown();
            return -1L;
        }
    }

    public final boolean scheduleAndDecide$okhttp(@NotNull Task task, long delayNanos, boolean recurrence) {
        int i;
        String str;
        Intrinsics.checkNotNullParameter(task, "task");
        task.initQueue$okhttp(this);
        long now = this.taskRunner.getBackend().nanoTime();
        long executeNanoTime = now + delayNanos;
        int existingIndex = this.futureTasks.indexOf(task);
        if (existingIndex != -1) {
            if (task.getNextExecuteNanoTime$okhttp() <= executeNanoTime) {
                Logger $this$taskLog$iv = this.taskRunner.getLogger$okhttp();
                if ($this$taskLog$iv.isLoggable(Level.FINE)) {
                    TaskLoggerKt.log($this$taskLog$iv, task, this, "already scheduled");
                    return false;
                }
                return false;
            }
            this.futureTasks.remove(existingIndex);
        }
        task.setNextExecuteNanoTime$okhttp(executeNanoTime);
        Logger $this$taskLog$iv2 = this.taskRunner.getLogger$okhttp();
        if ($this$taskLog$iv2.isLoggable(Level.FINE)) {
            if (recurrence) {
                str = "run again after " + TaskLoggerKt.formatDuration(executeNanoTime - now);
            } else {
                str = "scheduled after " + TaskLoggerKt.formatDuration(executeNanoTime - now);
            }
            TaskLoggerKt.log($this$taskLog$iv2, task, this, str);
        }
        List $this$indexOfFirst$iv = this.futureTasks;
        int index$iv = 0;
        Iterator<Task> it = $this$indexOfFirst$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                Task it2 = (Task) item$iv;
                if (it2.getNextExecuteNanoTime$okhttp() - now > delayNanos) {
                    i = index$iv;
                    break;
                }
                index$iv++;
            } else {
                i = -1;
                break;
            }
        }
        int insertAt = i;
        if (insertAt == -1) {
            insertAt = this.futureTasks.size();
        }
        this.futureTasks.add(insertAt, task);
        return insertAt == 0;
    }

    public final void cancelAll() {
        ReentrantLock $this$assertNotHeld$iv = this.lock;
        if (!_UtilJvmKt.assertionsEnabled || !$this$assertNotHeld$iv.isHeldByCurrentThread()) {
            ReentrantLock lock = this.taskRunner.getLock();
            lock.lock();
            try {
                if (cancelAllAndDecide$okhttp()) {
                    this.taskRunner.kickCoordinator$okhttp(this);
                }
                Unit unit = Unit.INSTANCE;
                lock.unlock();
                return;
            } catch (Throwable th) {
                lock.unlock();
                throw th;
            }
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertNotHeld$iv);
    }

    public final void shutdown() {
        ReentrantLock $this$assertNotHeld$iv = this.lock;
        if (!_UtilJvmKt.assertionsEnabled || !$this$assertNotHeld$iv.isHeldByCurrentThread()) {
            ReentrantLock lock = this.taskRunner.getLock();
            lock.lock();
            try {
                this.shutdown = true;
                if (cancelAllAndDecide$okhttp()) {
                    this.taskRunner.kickCoordinator$okhttp(this);
                }
                Unit unit = Unit.INSTANCE;
                lock.unlock();
                return;
            } catch (Throwable th) {
                lock.unlock();
                throw th;
            }
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertNotHeld$iv);
    }

    public final boolean cancelAllAndDecide$okhttp() {
        if (this.activeTask != null) {
            Task task = this.activeTask;
            Intrinsics.checkNotNull(task);
            if (task.getCancelable()) {
                this.cancelActiveTask = true;
            }
        }
        boolean tasksCanceled = false;
        for (int i = this.futureTasks.size() - 1; -1 < i; i--) {
            if (this.futureTasks.get(i).getCancelable()) {
                Logger $this$taskLog$iv = this.taskRunner.getLogger$okhttp();
                Task task$iv = this.futureTasks.get(i);
                if ($this$taskLog$iv.isLoggable(Level.FINE)) {
                    TaskLoggerKt.log($this$taskLog$iv, task$iv, this, "canceled");
                }
                tasksCanceled = true;
                this.futureTasks.remove(i);
            }
        }
        return tasksCanceled;
    }

    @NotNull
    public String toString() {
        return this.name;
    }
}
