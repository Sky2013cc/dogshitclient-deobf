package kotlinx.coroutines.debug.internal;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.Nullable;

/* compiled from: ConcurrentWeakMap.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\b��\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00028��\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/debug/internal/HashedWeakRef;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Ljava/lang/ref/WeakReference;", Constants.ATTR_REF, "queue", "Ljava/lang/ref/ReferenceQueue;", org.spongepowered.asm.util.Constants.CTOR, "(Ljava/lang/Object;Ljava/lang/ref/ReferenceQueue;)V", "hash", "", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/debug/internal/HashedWeakRef.class */
public final class HashedWeakRef<T> extends WeakReference<T> {

    @JvmField
    public final int hash;

    public HashedWeakRef(T t, @Nullable ReferenceQueue<T> referenceQueue) {
        super(t, referenceQueue);
        this.hash = t != null ? t.hashCode() : 0;
    }
}
