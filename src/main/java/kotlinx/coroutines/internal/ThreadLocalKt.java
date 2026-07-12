package kotlinx.coroutines.internal;

import kotlin.Metadata;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: ThreadLocal.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a&\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u0002H\u00010\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\u0004\"\u0004\b��\u0010\u00012\u0006\u0010\u0005\u001a\u00020\u0006H��*\u001e\b��\u0010��\u001a\u0004\b��\u0010\u0001\"\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0002¨\u0006\u0007"}, d2 = {"CommonThreadLocal", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Ljava/lang/ThreadLocal;", "commonThreadLocal", "Lkotlinx/coroutines/internal/CommonThreadLocal;", "name", "Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/ThreadLocalKt.class */
public final class ThreadLocalKt {
    @NotNull
    public static final <T> ThreadLocal<T> commonThreadLocal(@NotNull Symbol name) {
        return new ThreadLocal<>();
    }
}
