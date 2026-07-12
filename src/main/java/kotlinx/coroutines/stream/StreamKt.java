package kotlinx.coroutines.stream;

import java.util.stream.Stream;
import kotlin.Metadata;
import kotlinx.coroutines.flow.Flow;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: Stream.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u001c\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¨\u0006\u0004"}, d2 = {"consumeAsFlow", "Lkotlinx/coroutines/flow/Flow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Ljava/util/stream/Stream;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/stream/StreamKt.class */
public final class StreamKt {
    @NotNull
    public static final <T> Flow<T> consumeAsFlow(@NotNull Stream<T> stream) {
        return new StreamFlow(stream);
    }
}
