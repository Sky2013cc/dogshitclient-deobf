package okhttp3;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: Response.kt */
@Metadata(mv = {1, 9, 0}, k = 3, xi = 48, d1 = {"��\b\n��\n\u0002\u0018\u0002\n��\u0010��\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lokhttp3/Headers;", "invoke"})
/* loaded from: target.jar:okhttp3/Response$Builder$trailersFn$1.class */
final class Response$Builder$trailersFn$1 extends Lambda implements Function0<Headers> {
    public static final Response$Builder$trailersFn$1 INSTANCE = new Response$Builder$trailersFn$1();

    Response$Builder$trailersFn$1() {
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final Headers invoke() {
        return Headers.Companion.of(new String[0]);
    }
}
