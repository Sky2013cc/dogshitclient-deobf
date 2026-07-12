package okhttp3.internal;

import java.text.Normalizer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: -NormalizeJvm.kt */
@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0010\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H��¨\u0006\u0003"}, d2 = {"normalizeNfc", "", "string", "okhttp"})
/* loaded from: target.jar:okhttp3/internal/_NormalizeJvmKt.class */
public final class _NormalizeJvmKt {
    @NotNull
    public static final String normalizeNfc(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        String normalize = Normalizer.normalize(string, Normalizer.Form.NFC);
        Intrinsics.checkNotNullExpressionValue(normalize, "normalize(...)");
        return normalize;
    }
}
