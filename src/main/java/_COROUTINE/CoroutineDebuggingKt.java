package _COROUTINE;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* compiled from: CoroutineDebugging.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u0016\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u0002\"\u0014\u0010��\u001a\u00020\u0001X\u0080D¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0003¨\u0006\b"}, d2 = {"ARTIFICIAL_FRAME_PACKAGE_NAME", "", "getARTIFICIAL_FRAME_PACKAGE_NAME", "()Ljava/lang/String;", "artificialFrame", "Ljava/lang/StackTraceElement;", "", "name", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nCoroutineDebugging.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineDebugging.kt\n_COROUTINE/CoroutineDebuggingKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,62:1\n1#2:63\n*E\n"})
/* loaded from: target.jar:_COROUTINE/CoroutineDebuggingKt.class */
public final class CoroutineDebuggingKt {

    @NotNull
    private static final String ARTIFICIAL_FRAME_PACKAGE_NAME = "_COROUTINE";

    @NotNull
    public static final String getARTIFICIAL_FRAME_PACKAGE_NAME() {
        return ARTIFICIAL_FRAME_PACKAGE_NAME;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final StackTraceElement artificialFrame(Throwable $this$artificialFrame, String name) {
        StackTraceElement $this$artificialFrame_u24lambda_u240 = $this$artificialFrame.getStackTrace()[0];
        return new StackTraceElement(ARTIFICIAL_FRAME_PACKAGE_NAME + '.' + name, "_", $this$artificialFrame_u24lambda_u240.getFileName(), $this$artificialFrame_u24lambda_u240.getLineNumber());
    }
}
