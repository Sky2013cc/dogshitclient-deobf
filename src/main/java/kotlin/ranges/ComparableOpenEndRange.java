package kotlin.ranges;

import com.sun.tools.doclets.internal.toolkit.util.VisibleMemberMap;
import java.lang.Comparable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.OpenEndRange;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Ranges.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0012\u0018��*\u000e\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00028��\u0012\u0006\u0010\u0005\u001a\u00028��¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0016\u0010\u0004\u001a\u00028��X\u0096\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0005\u001a\u00028��X\u0096\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\t¨\u0006\u0014"}, d2 = {"Lkotlin/ranges/ComparableOpenEndRange;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "Lkotlin/ranges/OpenEndRange;", VisibleMemberMap.STARTLEVEL, "endExclusive", Constants.CTOR, "(Ljava/lang/Comparable;Ljava/lang/Comparable;)V", "getStart", "()Ljava/lang/Comparable;", "Ljava/lang/Comparable;", "getEndExclusive", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/ranges/ComparableOpenEndRange.class */
class ComparableOpenEndRange<T extends Comparable<? super T>> implements OpenEndRange<T> {

    @NotNull
    private final T start;

    @NotNull
    private final T endExclusive;

    public ComparableOpenEndRange(@NotNull T start, @NotNull T endExclusive) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(endExclusive, "endExclusive");
        this.start = start;
        this.endExclusive = endExclusive;
    }

    @Override // kotlin.ranges.OpenEndRange
    public boolean contains(@NotNull T t) {
        return OpenEndRange.DefaultImpls.contains(this, t);
    }

    @Override // kotlin.ranges.OpenEndRange
    public boolean isEmpty() {
        return OpenEndRange.DefaultImpls.isEmpty(this);
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    public T getStart() {
        return this.start;
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    public T getEndExclusive() {
        return this.endExclusive;
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof ComparableOpenEndRange) && ((isEmpty() && ((ComparableOpenEndRange) other).isEmpty()) || (Intrinsics.areEqual(getStart(), ((ComparableOpenEndRange) other).getStart()) && Intrinsics.areEqual(getEndExclusive(), ((ComparableOpenEndRange) other).getEndExclusive())));
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (31 * getStart().hashCode()) + getEndExclusive().hashCode();
    }

    @NotNull
    public String toString() {
        return getStart() + "..<" + getEndExclusive();
    }
}
