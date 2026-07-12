package okhttp3.internal.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TaskRunner.kt */
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��`\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\r\u0018�� 02\u00020\u0001:\u0003/01B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0!J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0013H\u0002J\b\u0010'\u001a\u0004\u0018\u00010%J\u0010\u0010(\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0006\u0010)\u001a\u00020#J\u0015\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020\u000bH��¢\u0006\u0002\b,J\u0006\u0010-\u001a\u00020\u000bJ\u0010\u0010.\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n��R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\n\n\u0002\b\u001a\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n��R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n��¨\u00062"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner;", "", "backend", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "logger", "Ljava/util/logging/Logger;", "(Lokhttp3/internal/concurrent/TaskRunner$Backend;Ljava/util/logging/Logger;)V", "getBackend", "()Lokhttp3/internal/concurrent/TaskRunner$Backend;", "busyQueues", "", "Lokhttp3/internal/concurrent/TaskQueue;", "condition", "Ljava/util/concurrent/locks/Condition;", "getCondition", "()Ljava/util/concurrent/locks/Condition;", "coordinatorWaiting", "", "coordinatorWakeUpAt", "", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "getLogger$okhttp", "()Ljava/util/logging/Logger;", "logger$1", "nextQueueName", "", "readyQueues", "runnable", "Ljava/lang/Runnable;", "activeQueues", "", "afterRun", "", "task", "Lokhttp3/internal/concurrent/Task;", "delayNanos", "awaitTaskToRun", "beforeRun", "cancelAll", "kickCoordinator", "taskQueue", "kickCoordinator$okhttp", "newQueue", "runTask", "Backend", "Companion", "RealBackend", "okhttp"})
@SourceDebugExtension({"SMAP\nTaskRunner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner\n+ 2 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,345:1\n346#2,4:346\n346#2,4:350\n346#2,4:354\n346#2,4:358\n346#2,4:363\n1#3:362\n*S KotlinDebug\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner\n*L\n89#1:346,4\n107#1:350,4\n137#1:354,4\n163#1:358,4\n257#1:363,4\n*E\n"})
/* loaded from: target.jar:okhttp3/internal/concurrent/TaskRunner.class */
public final class TaskRunner {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final Backend backend;

    @NotNull
    private final Logger logger$1;

    @NotNull
    private final ReentrantLock lock;

    @NotNull
    private final Condition condition;
    private int nextQueueName;
    private boolean coordinatorWaiting;
    private long coordinatorWakeUpAt;

    @NotNull
    private final List<TaskQueue> busyQueues;

    @NotNull
    private final List<TaskQueue> readyQueues;

    @NotNull
    private final Runnable runnable;

    @NotNull
    private static final Logger logger;

    @JvmField
    @NotNull
    public static final TaskRunner INSTANCE;

    /* compiled from: TaskRunner.kt */
    @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018��2\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\"\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b��\u0010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\nH&J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\bH&ø\u0001��\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$Backend;", "", "coordinatorNotify", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "coordinatorWait", "nanos", "", "decorate", "Ljava/util/concurrent/BlockingQueue;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "queue", "execute", "runnable", "Ljava/lang/Runnable;", "nanoTime", "okhttp"})
    /* loaded from: target.jar:okhttp3/internal/concurrent/TaskRunner$Backend.class */
    public interface Backend {
        long nanoTime();

        void coordinatorNotify(@NotNull TaskRunner taskRunner);

        void coordinatorWait(@NotNull TaskRunner taskRunner, long j);

        @NotNull
        <T> BlockingQueue<T> decorate(@NotNull BlockingQueue<T> blockingQueue);

        void execute(@NotNull TaskRunner taskRunner, @NotNull Runnable runnable);
    }

    public TaskRunner(@NotNull Backend backend, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(backend, "backend");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.backend = backend;
        this.logger$1 = logger2;
        this.lock = new ReentrantLock();
        Condition newCondition = this.lock.newCondition();
        Intrinsics.checkNotNullExpressionValue(newCondition, "newCondition(...)");
        this.condition = newCondition;
        this.nextQueueName = Config.MAX_MULTI_BYTE_RANGES_NUM;
        this.busyQueues = new ArrayList();
        this.readyQueues = new ArrayList();
        this.runnable = new Runnable() { // from class: okhttp3.internal.concurrent.TaskRunner$runnable$1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    ReentrantLock lock = TaskRunner.this.getLock();
                    TaskRunner taskRunner = TaskRunner.this;
                    lock.lock();
                    try {
                        Task task = taskRunner.awaitTaskToRun();
                        lock.unlock();
                        if (task == null) {
                            return;
                        }
                        Logger $this$logElapsed$iv = TaskRunner.this.getLogger$okhttp();
                        TaskQueue queue$iv = task.getQueue$okhttp();
                        Intrinsics.checkNotNull(queue$iv);
                        TaskRunner taskRunner2 = TaskRunner.this;
                        long startNs$iv = -1;
                        boolean loggingEnabled$iv = $this$logElapsed$iv.isLoggable(Level.FINE);
                        if (loggingEnabled$iv) {
                            startNs$iv = queue$iv.getTaskRunner$okhttp().getBackend().nanoTime();
                            TaskLoggerKt.log($this$logElapsed$iv, task, queue$iv, "starting");
                        }
                        try {
                            try {
                                taskRunner2.runTask(task);
                                Unit unit = Unit.INSTANCE;
                                if (loggingEnabled$iv) {
                                    TaskLoggerKt.log($this$logElapsed$iv, task, queue$iv, "finished run in " + TaskLoggerKt.formatDuration(queue$iv.getTaskRunner$okhttp().getBackend().nanoTime() - startNs$iv));
                                }
                            } catch (Throwable th) {
                                lock = taskRunner2.getLock();
                                lock.lock();
                                try {
                                    taskRunner2.getBackend().execute(taskRunner2, this);
                                    Unit unit2 = Unit.INSTANCE;
                                    lock.unlock();
                                    throw th;
                                } finally {
                                }
                            }
                        } catch (Throwable th2) {
                            if (loggingEnabled$iv) {
                                long elapsedNs$iv = queue$iv.getTaskRunner$okhttp().getBackend().nanoTime() - startNs$iv;
                                if (0 != 0) {
                                    TaskLoggerKt.log($this$logElapsed$iv, task, queue$iv, "finished run in " + TaskLoggerKt.formatDuration(elapsedNs$iv));
                                } else {
                                    TaskLoggerKt.log($this$logElapsed$iv, task, queue$iv, "failed a run in " + TaskLoggerKt.formatDuration(elapsedNs$iv));
                                }
                            }
                            throw th2;
                        }
                    } finally {
                    }
                }
            }
        };
    }

    public /* synthetic */ TaskRunner(Backend backend, Logger logger2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(backend, (i & 2) != 0 ? logger : logger2);
    }

    @NotNull
    public final Backend getBackend() {
        return this.backend;
    }

    @NotNull
    public final Logger getLogger$okhttp() {
        return this.logger$1;
    }

    @NotNull
    public final ReentrantLock getLock() {
        return this.lock;
    }

    @NotNull
    public final Condition getCondition() {
        return this.condition;
    }

    public final void kickCoordinator$okhttp(@NotNull TaskQueue taskQueue) {
        Intrinsics.checkNotNullParameter(taskQueue, "taskQueue");
        ReentrantLock $this$assertHeld$iv = this.lock;
        if (!_UtilJvmKt.assertionsEnabled || $this$assertHeld$iv.isHeldByCurrentThread()) {
            if (taskQueue.getActiveTask$okhttp() == null) {
                if (!taskQueue.getFutureTasks$okhttp().isEmpty()) {
                    _UtilCommonKt.addIfAbsent(this.readyQueues, taskQueue);
                } else {
                    this.readyQueues.remove(taskQueue);
                }
            }
            if (this.coordinatorWaiting) {
                this.backend.coordinatorNotify(this);
                return;
            } else {
                this.backend.execute(this, this.runnable);
                return;
            }
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertHeld$iv);
    }

    private final void beforeRun(Task task) {
        ReentrantLock $this$assertHeld$iv = this.lock;
        if (!_UtilJvmKt.assertionsEnabled || $this$assertHeld$iv.isHeldByCurrentThread()) {
            task.setNextExecuteNanoTime$okhttp(-1L);
            TaskQueue queue = task.getQueue$okhttp();
            Intrinsics.checkNotNull(queue);
            queue.getFutureTasks$okhttp().remove(task);
            this.readyQueues.remove(queue);
            queue.setActiveTask$okhttp(task);
            this.busyQueues.add(queue);
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertHeld$iv);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runTask(Task task) {
        ReentrantLock reentrantLock;
        Thread currentThread = Thread.currentThread();
        String oldName = currentThread.getName();
        currentThread.setName(task.getName());
        long delayNanos = -1;
        try {
            delayNanos = task.runOnce();
            reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                afterRun(task, delayNanos);
                Unit unit = Unit.INSTANCE;
                reentrantLock.unlock();
                currentThread.setName(oldName);
            } finally {
            }
        } catch (Throwable th) {
            reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                afterRun(task, delayNanos);
                Unit unit2 = Unit.INSTANCE;
                reentrantLock.unlock();
                currentThread.setName(oldName);
                throw th;
            } finally {
            }
        }
    }

    private final void afterRun(Task task, long delayNanos) {
        ReentrantLock $this$assertHeld$iv = this.lock;
        if (!_UtilJvmKt.assertionsEnabled || $this$assertHeld$iv.isHeldByCurrentThread()) {
            TaskQueue queue = task.getQueue$okhttp();
            Intrinsics.checkNotNull(queue);
            if (!(queue.getActiveTask$okhttp() == task)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            boolean cancelActiveTask = queue.getCancelActiveTask$okhttp();
            queue.setCancelActiveTask$okhttp(false);
            queue.setActiveTask$okhttp(null);
            this.busyQueues.remove(queue);
            if (delayNanos != -1 && !cancelActiveTask && !queue.getShutdown$okhttp()) {
                queue.scheduleAndDecide$okhttp(task, delayNanos, true);
            }
            if (!queue.getFutureTasks$okhttp().isEmpty()) {
                this.readyQueues.add(queue);
                return;
            }
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertHeld$iv);
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x00ef, code lost:
    
        if ((!r7.readyQueues.isEmpty()) != false) goto L34;
     */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Task awaitTaskToRun() {
        ReentrantLock $this$assertHeld$iv = this.lock;
        if (!_UtilJvmKt.assertionsEnabled || $this$assertHeld$iv.isHeldByCurrentThread()) {
            while (!this.readyQueues.isEmpty()) {
                long now = this.backend.nanoTime();
                long minDelayNanos = Long.MAX_VALUE;
                Task readyTask = null;
                boolean multipleReadyTasks = false;
                Iterator<TaskQueue> it = this.readyQueues.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    TaskQueue queue = it.next();
                    Task candidate = queue.getFutureTasks$okhttp().get(0);
                    long candidateDelay = Math.max(0L, candidate.getNextExecuteNanoTime$okhttp() - now);
                    if (candidateDelay > 0) {
                        minDelayNanos = Math.min(candidateDelay, minDelayNanos);
                    } else {
                        if (readyTask != null) {
                            multipleReadyTasks = true;
                            break;
                        }
                        readyTask = candidate;
                    }
                }
                if (readyTask != null) {
                    beforeRun(readyTask);
                    if (!multipleReadyTasks) {
                        if (!this.coordinatorWaiting) {
                        }
                        return readyTask;
                    }
                    this.backend.execute(this, this.runnable);
                    return readyTask;
                }
                if (this.coordinatorWaiting) {
                    if (minDelayNanos < this.coordinatorWakeUpAt - now) {
                        this.backend.coordinatorNotify(this);
                        return null;
                    }
                    return null;
                }
                this.coordinatorWaiting = true;
                this.coordinatorWakeUpAt = now + minDelayNanos;
                try {
                    try {
                        this.backend.coordinatorWait(this, minDelayNanos);
                        this.coordinatorWaiting = false;
                    } catch (InterruptedException e) {
                        cancelAll();
                        this.coordinatorWaiting = false;
                    }
                } catch (Throwable th) {
                    this.coordinatorWaiting = false;
                    throw th;
                }
            }
            return null;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertHeld$iv);
    }

    @NotNull
    public final TaskQueue newQueue() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int name = this.nextQueueName;
            this.nextQueueName = name + 1;
            reentrantLock.unlock();
            return new TaskQueue(this, new StringBuilder().append('Q').append(name).toString());
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @NotNull
    public final List<TaskQueue> activeQueues() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            List<TaskQueue> plus = CollectionsKt.plus((Collection) this.busyQueues, (Iterable) this.readyQueues);
            reentrantLock.unlock();
            return plus;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final void cancelAll() {
        ReentrantLock $this$assertHeld$iv = this.lock;
        if (!_UtilJvmKt.assertionsEnabled || $this$assertHeld$iv.isHeldByCurrentThread()) {
            for (int i = this.busyQueues.size() - 1; -1 < i; i--) {
                this.busyQueues.get(i).cancelAllAndDecide$okhttp();
            }
            for (int i2 = this.readyQueues.size() - 1; -1 < i2; i2--) {
                TaskQueue queue = this.readyQueues.get(i2);
                queue.cancelAllAndDecide$okhttp();
                if (queue.getFutureTasks$okhttp().isEmpty()) {
                    this.readyQueues.remove(i2);
                }
            }
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertHeld$iv);
    }

    /* compiled from: TaskRunner.kt */
    @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b��\u0010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011H\u0016J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u000fH\u0016J\u0006\u0010\u0018\u001a\u00020\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$RealBackend;", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "threadFactory", "Ljava/util/concurrent/ThreadFactory;", "(Ljava/util/concurrent/ThreadFactory;)V", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "getExecutor", "()Ljava/util/concurrent/ThreadPoolExecutor;", "coordinatorNotify", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "coordinatorWait", "nanos", "", "decorate", "Ljava/util/concurrent/BlockingQueue;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "queue", "execute", "runnable", "Ljava/lang/Runnable;", "nanoTime", "shutdown", "okhttp"})
    @SourceDebugExtension({"SMAP\nTaskRunner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner$RealBackend\n+ 2 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n*L\n1#1,345:1\n346#2,4:346\n*S KotlinDebug\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner$RealBackend\n*L\n318#1:346,4\n*E\n"})
    /* loaded from: target.jar:okhttp3/internal/concurrent/TaskRunner$RealBackend.class */
    public static final class RealBackend implements Backend {

        @NotNull
        private final ThreadPoolExecutor executor;

        public RealBackend(@NotNull ThreadFactory threadFactory) {
            Intrinsics.checkNotNullParameter(threadFactory, "threadFactory");
            this.executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), threadFactory);
        }

        @NotNull
        public final ThreadPoolExecutor getExecutor() {
            return this.executor;
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public long nanoTime() {
            return System.nanoTime();
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public void coordinatorNotify(@NotNull TaskRunner taskRunner) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            taskRunner.getCondition().signal();
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public void coordinatorWait(@NotNull TaskRunner taskRunner, long nanos) throws InterruptedException {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            ReentrantLock $this$assertHeld$iv = taskRunner.getLock();
            if (_UtilJvmKt.assertionsEnabled && !$this$assertHeld$iv.isHeldByCurrentThread()) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertHeld$iv);
            }
            if (nanos > 0) {
                taskRunner.getCondition().awaitNanos(nanos);
            }
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        @NotNull
        public <T> BlockingQueue<T> decorate(@NotNull BlockingQueue<T> queue) {
            Intrinsics.checkNotNullParameter(queue, "queue");
            return queue;
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public void execute(@NotNull TaskRunner taskRunner, @NotNull Runnable runnable) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            Intrinsics.checkNotNullParameter(runnable, "runnable");
            this.executor.execute(runnable);
        }

        public final void shutdown() {
            this.executor.shutdown();
        }
    }

    /* compiled from: TaskRunner.kt */
    @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n��R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$Companion;", "", "()V", "INSTANCE", "Lokhttp3/internal/concurrent/TaskRunner;", "logger", "Ljava/util/logging/Logger;", "getLogger", "()Ljava/util/logging/Logger;", "okhttp"})
    /* loaded from: target.jar:okhttp3/internal/concurrent/TaskRunner$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final Logger getLogger() {
            return TaskRunner.logger;
        }
    }

    static {
        Logger logger2 = Logger.getLogger(TaskRunner.class.getName());
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(...)");
        logger = logger2;
        INSTANCE = new TaskRunner(new RealBackend(_UtilJvmKt.threadFactory(_UtilJvmKt.okHttpName + " TaskRunner", true)), null, 2, null);
    }
}
