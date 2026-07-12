package kotlinx.coroutines.internal;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: Synchronized.common.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u0016\n\u0002\b\u0003\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a;\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006H\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"synchronized", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "lock", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", Constants.ATTR_BLOCK, "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nSynchronized.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 2 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n*L\n1#1,30:1\n16#2:31\n*S KotlinDebug\n*F\n+ 1 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n*L\n28#1:31\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/internal/Synchronized_commonKt.class */
public final class Synchronized_commonKt {
    @InternalCoroutinesApi
    /* renamed from: synchronized, reason: not valid java name */
    public static final <T> T m2946synchronized(@NotNull Object lock, @NotNull Function0<? extends T> function0) {
        T invoke;
        synchronized (lock) {
            try {
                invoke = function0.invoke();
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        return invoke;
    }
}
