package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: ProbesSupport.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\u001a#\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0080\b\u001a\u001d\u0010\u0004\u001a\u00020\u0005\"\u0004\b��\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0080\b¨\u0006\u0006"}, d2 = {"probeCoroutineCreated", "Lkotlin/coroutines/Continuation;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "completion", "probeCoroutineResumed", "", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/ProbesSupportKt.class */
public final class ProbesSupportKt {
    @NotNull
    public static final <T> Continuation<T> probeCoroutineCreated(@NotNull Continuation<? super T> continuation) {
        return DebugProbesKt.probeCoroutineCreated(continuation);
    }

    public static final <T> void probeCoroutineResumed(@NotNull Continuation<? super T> continuation) {
        DebugProbesKt.probeCoroutineResumed(continuation);
    }
}
