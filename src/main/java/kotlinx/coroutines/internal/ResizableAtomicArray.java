package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceArray;
import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ResizableAtomicArray.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b��\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\t\u001a\u00020\u0004J\u0018\u0010\n\u001a\u0004\u0018\u00018��2\u0006\u0010\u000b\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0002\u0010\fJ\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00018��¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\bX\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/internal/ResizableAtomicArray;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "initialLength", "", Constants.CTOR, "(I)V", "array", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "currentLength", PropertyDescriptor.GET, "index", "(I)Ljava/lang/Object;", "setSynchronized", "", "value", "(ILjava/lang/Object;)V", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/ResizableAtomicArray.class */
public final class ResizableAtomicArray<T> {

    @NotNull
    private volatile AtomicReferenceArray<T> array;

    public ResizableAtomicArray(int initialLength) {
        this.array = new AtomicReferenceArray<>(initialLength);
    }

    public final int currentLength() {
        return this.array.length();
    }

    @Nullable
    public final T get(int index) {
        AtomicReferenceArray array = this.array;
        if (index < array.length()) {
            return array.get(index);
        }
        return null;
    }

    public final void setSynchronized(int index, @Nullable T t) {
        AtomicReferenceArray curArray = this.array;
        int curLen = curArray.length();
        if (index < curLen) {
            curArray.set(index, t);
            return;
        }
        AtomicReferenceArray newArray = new AtomicReferenceArray(RangesKt.coerceAtLeast(index + 1, 2 * curLen));
        for (int i = 0; i < curLen; i++) {
            newArray.set(i, curArray.get(i));
        }
        newArray.set(index, t);
        this.array = newArray;
    }
}
