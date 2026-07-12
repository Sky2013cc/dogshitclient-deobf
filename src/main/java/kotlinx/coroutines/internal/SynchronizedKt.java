package kotlinx.coroutines.internal;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: Synchronized.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u001c\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a.\u0010\u0003\u001a\u0002H\u0004\"\u0004\b��\u0010\u00042\n\u0010\u0005\u001a\u00060\u0001j\u0002`\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00040\bH\u0087\b¢\u0006\u0002\u0010\t*\u0010\b\u0007\u0010��\"\u00020\u00012\u00020\u0001B\u0002\b\u0002¨\u0006\n"}, d2 = {"SynchronizedObject", "", "Lkotlinx/coroutines/InternalCoroutinesApi;", "synchronizedImpl", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "lock", "Lkotlinx/coroutines/internal/SynchronizedObject;", Constants.ATTR_BLOCK, "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/SynchronizedKt.class */
public final class SynchronizedKt {
    @InternalCoroutinesApi
    public static /* synthetic */ void SynchronizedObject$annotations() {
    }

    @InternalCoroutinesApi
    public static final <T> T synchronizedImpl(@NotNull Object lock, @NotNull Function0<? extends T> function0) {
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
