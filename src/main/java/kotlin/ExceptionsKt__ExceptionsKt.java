package kotlin;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import kotlin.internal.HidesMembers;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Exceptions.kt */
@Metadata(mv = {2, 1, 0}, k = 5, xi = 49, d1 = {"��4\n��\n\u0002\u0010\u0002\n\u0002\u0010\u0003\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\u001a\r\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0007\u001a\u0014\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0002H\u0007\"!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\u00028F¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"printStackTrace", "", "", "writer", "Ljava/io/PrintWriter;", "stream", "Ljava/io/PrintStream;", "stackTrace", "", "Ljava/lang/StackTraceElement;", "getStackTrace$annotations", "(Ljava/lang/Throwable;)V", "getStackTrace", "(Ljava/lang/Throwable;)[Ljava/lang/StackTraceElement;", "stackTraceToString", "", "addSuppressed", "exception", "suppressedExceptions", "", "getSuppressedExceptions$annotations", "getSuppressedExceptions", "(Ljava/lang/Throwable;)Ljava/util/List;", "kotlin-stdlib"}, xs = "kotlin/ExceptionsKt")
/* loaded from: target.jar:kotlin/ExceptionsKt__ExceptionsKt.class */
public class ExceptionsKt__ExceptionsKt {
    public static /* synthetic */ void getStackTrace$annotations(Throwable th) {
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void getSuppressedExceptions$annotations(Throwable th) {
    }

    @InlineOnly
    private static final void printStackTrace(Throwable $this$printStackTrace) {
        Intrinsics.checkNotNullParameter($this$printStackTrace, "<this>");
        $this$printStackTrace.printStackTrace();
    }

    @InlineOnly
    private static final void printStackTrace(Throwable $this$printStackTrace, PrintWriter writer) {
        Intrinsics.checkNotNullParameter($this$printStackTrace, "<this>");
        Intrinsics.checkNotNullParameter(writer, "writer");
        $this$printStackTrace.printStackTrace(writer);
    }

    @InlineOnly
    private static final void printStackTrace(Throwable $this$printStackTrace, PrintStream stream) {
        Intrinsics.checkNotNullParameter($this$printStackTrace, "<this>");
        Intrinsics.checkNotNullParameter(stream, "stream");
        $this$printStackTrace.printStackTrace(stream);
    }

    @NotNull
    public static final StackTraceElement[] getStackTrace(@NotNull Throwable $this$stackTrace) {
        Intrinsics.checkNotNullParameter($this$stackTrace, "<this>");
        StackTraceElement[] stackTrace = $this$stackTrace.getStackTrace();
        Intrinsics.checkNotNull(stackTrace);
        return stackTrace;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final String stackTraceToString(@NotNull Throwable $this$stackTraceToString) {
        Intrinsics.checkNotNullParameter($this$stackTraceToString, "<this>");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        $this$stackTraceToString.printStackTrace(pw);
        pw.flush();
        String stringWriter = sw.toString();
        Intrinsics.checkNotNullExpressionValue(stringWriter, "toString(...)");
        return stringWriter;
    }

    @SinceKotlin(version = "1.1")
    @HidesMembers
    public static final void addSuppressed(@NotNull Throwable $this$addSuppressed, @NotNull Throwable exception) {
        Intrinsics.checkNotNullParameter($this$addSuppressed, "<this>");
        Intrinsics.checkNotNullParameter(exception, "exception");
        if ($this$addSuppressed != exception) {
            PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed($this$addSuppressed, exception);
        }
    }

    @NotNull
    public static final List<Throwable> getSuppressedExceptions(@NotNull Throwable $this$suppressedExceptions) {
        Intrinsics.checkNotNullParameter($this$suppressedExceptions, "<this>");
        return PlatformImplementationsKt.IMPLEMENTATIONS.getSuppressed($this$suppressedExceptions);
    }
}
