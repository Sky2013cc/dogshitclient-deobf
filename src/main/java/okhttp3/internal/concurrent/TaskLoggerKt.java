package okhttp3.internal.concurrent;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.time.DurationKt;
import okhttp3.internal.http2.Http2Connection;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: TaskLogger.kt */
@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"��.\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000e\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a$\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001H\u0002\u001a9\u0010\f\u001a\u0002H\r\"\u0004\b��\u0010\r*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fH\u0080\bø\u0001��¢\u0006\u0002\u0010\u0010\u001a.\u0010\u0011\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0080\bø\u0001��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"formatDuration", "", "ns", "", "log", "", "Ljava/util/logging/Logger;", "task", "Lokhttp3/internal/concurrent/Task;", "queue", "Lokhttp3/internal/concurrent/TaskQueue;", "message", "logElapsed", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, Constants.ATTR_BLOCK, "Lkotlin/Function0;", "(Ljava/util/logging/Logger;Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "taskLog", "messageBlock", "okhttp"})
/* loaded from: target.jar:okhttp3/internal/concurrent/TaskLoggerKt.class */
public final class TaskLoggerKt {
    public static final void taskLog(@NotNull Logger $this$taskLog, @NotNull Task task, @NotNull TaskQueue queue, @NotNull Function0<String> messageBlock) {
        Intrinsics.checkNotNullParameter($this$taskLog, "<this>");
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(queue, "queue");
        Intrinsics.checkNotNullParameter(messageBlock, "messageBlock");
        if ($this$taskLog.isLoggable(Level.FINE)) {
            log($this$taskLog, task, queue, messageBlock.invoke());
        }
    }

    public static final <T> T logElapsed(@NotNull Logger $this$logElapsed, @NotNull Task task, @NotNull TaskQueue queue, @NotNull Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter($this$logElapsed, "<this>");
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(queue, "queue");
        Intrinsics.checkNotNullParameter(block, "block");
        long startNs = -1;
        boolean loggingEnabled = $this$logElapsed.isLoggable(Level.FINE);
        if (loggingEnabled) {
            startNs = queue.getTaskRunner$okhttp().getBackend().nanoTime();
            log($this$logElapsed, task, queue, "starting");
        }
        boolean completedNormally = false;
        try {
            T invoke = block.invoke();
            completedNormally = true;
            InlineMarker.finallyStart(1);
            if (loggingEnabled) {
                log($this$logElapsed, task, queue, "finished run in " + formatDuration(queue.getTaskRunner$okhttp().getBackend().nanoTime() - startNs));
            }
            InlineMarker.finallyEnd(1);
            return invoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            if (loggingEnabled) {
                long elapsedNs = queue.getTaskRunner$okhttp().getBackend().nanoTime() - startNs;
                if (completedNormally) {
                    log($this$logElapsed, task, queue, "finished run in " + formatDuration(elapsedNs));
                } else {
                    log($this$logElapsed, task, queue, "failed a run in " + formatDuration(elapsedNs));
                }
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void log(Logger $this$log, Task task, TaskQueue queue, String message) {
        StringBuilder append = new StringBuilder().append(queue.getName$okhttp()).append(' ');
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {message};
        String format = String.format("%-22s", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        $this$log.fine(append.append(format).append(": ").append(task.getName()).toString());
    }

    @NotNull
    public static final String formatDuration(long ns) {
        String str;
        if (ns <= -999500000) {
            str = ((ns - 500000000) / Http2Connection.DEGRADED_PONG_TIMEOUT_NS) + " s ";
        } else if (ns <= -999500) {
            str = ((ns - 500000) / DurationKt.NANOS_IN_MILLIS) + " ms";
        } else if (ns <= 0) {
            str = ((ns - 500) / 1000) + " µs";
        } else if (ns < 999500) {
            str = ((ns + 500) / 1000) + " µs";
        } else {
            str = ns < 999500000 ? ((ns + 500000) / DurationKt.NANOS_IN_MILLIS) + " ms" : ((ns + 500000000) / Http2Connection.DEGRADED_PONG_TIMEOUT_NS) + " s ";
        }
        String s = str;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {s};
        String format = String.format("%6s", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}
