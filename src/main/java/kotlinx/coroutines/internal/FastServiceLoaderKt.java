package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.SourceDebugExtension;

/* compiled from: FastServiceLoader.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\"\u0014\u0010��\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"ANDROID_DETECTED", "", "getANDROID_DETECTED", "()Z", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nFastServiceLoader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FastServiceLoader.kt\nkotlinx/coroutines/internal/FastServiceLoaderKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,169:1\n1#2:170\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/internal/FastServiceLoaderKt.class */
public final class FastServiceLoaderKt {
    private static final boolean ANDROID_DETECTED;

    public static final boolean getANDROID_DETECTED() {
        return ANDROID_DETECTED;
    }

    static {
        Object m1167constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(Class.forName("android.os.Build"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(ResultKt.createFailure(th));
        }
        ANDROID_DETECTED = Result.m1160isSuccessimpl(m1167constructorimpl);
    }
}
