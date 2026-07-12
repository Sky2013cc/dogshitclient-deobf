package kotlin.ranges;

import com.sun.tools.doclets.internal.toolkit.util.VisibleMemberMap;
import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.WasExperimental;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ULongRange.kt */
@SinceKotlin(version = "1.5")
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010(\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018�� \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB!\b��\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0086\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0013\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u0013\u0010\t\u001a\u00020\u0002¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\r\u001a\u00020\u0002¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lkotlin/ranges/ULongProgression;", "", "Lkotlin/ULong;", VisibleMemberMap.STARTLEVEL, "endInclusive", "step", "", Constants.CTOR, "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst-s-VKNKU", "()J", "J", "last", "getLast-s-VKNKU", "getStep", "iterator", "", "isEmpty", "", "equals", "other", "", "hashCode", "", "toString", "", "Companion", "kotlin-stdlib"})
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: target.jar:kotlin/ranges/ULongProgression.class */
public class ULongProgression implements Iterable<ULong>, KMappedMarker {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final long first;
    private final long last;
    private final long step;

    public /* synthetic */ ULongProgression(long start, long endInclusive, long step, DefaultConstructorMarker $constructor_marker) {
        this(start, endInclusive, step);
    }

    private ULongProgression(long start, long endInclusive, long step) {
        if (step == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (step == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.first = start;
        this.last = UProgressionUtilKt.m2409getProgressionLastElement7ftBX0g(start, endInclusive, step);
        this.step = step;
    }

    /* renamed from: getFirst-s-VKNKU, reason: not valid java name */
    public final long m2485getFirstsVKNKU() {
        return this.first;
    }

    /* renamed from: getLast-s-VKNKU, reason: not valid java name */
    public final long m2486getLastsVKNKU() {
        return this.last;
    }

    public final long getStep() {
        return this.step;
    }

    @Override // java.lang.Iterable
    @NotNull
    public final Iterator<ULong> iterator() {
        return new ULongProgressionIterator(this.first, this.last, this.step, null);
    }

    public boolean isEmpty() {
        return this.step > 0 ? Long.compareUnsigned(this.first, this.last) > 0 : Long.compareUnsigned(this.first, this.last) < 0;
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof ULongProgression) && ((isEmpty() && ((ULongProgression) other).isEmpty()) || (this.first == ((ULongProgression) other).first && this.last == ((ULongProgression) other).last && this.step == ((ULongProgression) other).step));
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (31 * ((31 * ((int) ULong.m1388constructorimpl(this.first ^ ULong.m1388constructorimpl(this.first >>> 32)))) + ((int) ULong.m1388constructorimpl(this.last ^ ULong.m1388constructorimpl(this.last >>> 32))))) + ((int) (this.step ^ (this.step >>> 32)));
    }

    @NotNull
    public String toString() {
        return this.step > 0 ? ((Object) ULong.m1385toStringimpl(this.first)) + ".." + ((Object) ULong.m1385toStringimpl(this.last)) + " step " + this.step : ((Object) ULong.m1385toStringimpl(this.first)) + " downTo " + ((Object) ULong.m1385toStringimpl(this.last)) + " step " + (-this.step);
    }

    /* compiled from: ULongRange.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lkotlin/ranges/ULongProgression$Companion;", "", Constants.CTOR, "()V", "fromClosedRange", "Lkotlin/ranges/ULongProgression;", "rangeStart", "Lkotlin/ULong;", "rangeEnd", "step", "", "fromClosedRange-7ftBX0g", "(JJJ)Lkotlin/ranges/ULongProgression;", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/ranges/ULongProgression$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        /* renamed from: fromClosedRange-7ftBX0g, reason: not valid java name */
        public final ULongProgression m2488fromClosedRange7ftBX0g(long rangeStart, long rangeEnd, long step) {
            return new ULongProgression(rangeStart, rangeEnd, step, null);
        }
    }
}
