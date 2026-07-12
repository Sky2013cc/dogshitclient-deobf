package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Comparisons.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018��*\u0004\b��\u0010\u00012\u0012\u0012\u0004\u0012\u0002H\u00010\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\u0003B\u001f\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00028��0\u0002j\b\u0012\u0004\u0012\u00028��`\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028��2\u0006\u0010\r\u001a\u00028��H\u0016¢\u0006\u0002\u0010\u000eJ\u001b\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00028��0\u0002j\b\u0012\u0004\u0012\u00028��`\u0003¢\u0006\u0002\u0010\bR#\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00028��0\u0002j\b\u0012\u0004\u0012\u00028��`\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lkotlin/comparisons/ReversedComparator;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", Constants.CTOR, "(Ljava/util/Comparator;)V", "getComparator", "()Ljava/util/Comparator;", "Ljava/util/Comparator;", "compare", "", "a", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "(Ljava/lang/Object;Ljava/lang/Object;)I", "reversed", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/comparisons/ReversedComparator.class */
final class ReversedComparator<T> implements Comparator<T> {

    @NotNull
    private final Comparator<T> comparator;

    public ReversedComparator(@NotNull Comparator<T> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        this.comparator = comparator;
    }

    @NotNull
    public final Comparator<T> getComparator() {
        return this.comparator;
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        return this.comparator.compare(t2, t);
    }

    @Override // java.util.Comparator
    @NotNull
    public final Comparator<T> reversed() {
        return this.comparator;
    }
}
