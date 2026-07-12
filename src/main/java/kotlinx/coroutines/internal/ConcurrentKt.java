package kotlinx.coroutines.internal;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: Concurrent.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��,\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\b\n��\u001a*\u0010\u0002\u001a\u0002H\u0003\"\u0004\b��\u0010\u0003*\u00060\u0001j\u0002`\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0006H\u0080\b¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\u0004\b��\u0010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0080\b*\f\b��\u0010��\"\u00020\u00012\u00020\u0001*\u001e\b��\u0010\b\u001a\u0004\b��\u0010\u0003\"\b\u0012\u0004\u0012\u0002H\u00030\t2\b\u0012\u0004\u0012\u0002H\u00030\t¨\u0006\u000f"}, d2 = {"ReentrantLock", "Ljava/util/concurrent/locks/ReentrantLock;", "withLock", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/internal/ReentrantLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "WorkaroundAtomicReference", "Ljava/util/concurrent/atomic/AtomicReference;", "identitySet", "", "E", "expectedSize", "", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/ConcurrentKt.class */
public final class ConcurrentKt {
    public static final <T> T withLock(@NotNull ReentrantLock $this$withLock, @NotNull Function0<? extends T> function0) {
        ReentrantLock reentrantLock = $this$withLock;
        reentrantLock.lock();
        try {
            T invoke = function0.invoke();
            InlineMarker.finallyStart(1);
            reentrantLock.unlock();
            InlineMarker.finallyEnd(1);
            return invoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            reentrantLock.unlock();
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    @NotNull
    public static final <E> Set<E> identitySet(int expectedSize) {
        return Collections.newSetFromMap(new IdentityHashMap(expectedSize));
    }
}
