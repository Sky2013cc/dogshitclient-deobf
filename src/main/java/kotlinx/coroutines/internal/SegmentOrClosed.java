package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Segment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ConcurrentLinkedList.kt */
@JvmInline
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0081@\u0018��*\u000e\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u0011\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\u00028��8F¢\u0006\f\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u0088\u0001\u0004\u0092\u0001\u0004\u0018\u00010\u0003¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/internal/SegmentOrClosed;", "S", "Lkotlinx/coroutines/internal/Segment;", "", "value", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "isClosed", "", "isClosed-impl", "(Ljava/lang/Object;)Z", "segment", "getSegment$annotations", "()V", "getSegment-impl", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/Segment;", "equals", "other", "hashCode", "", "toString", "", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/SegmentOrClosed.class */
public final class SegmentOrClosed<S extends Segment<S>> {

    @Nullable
    private final Object value;

    public static /* synthetic */ void getSegment$annotations() {
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m2938toStringimpl(Object arg0) {
        return "SegmentOrClosed(value=" + arg0 + ')';
    }

    public String toString() {
        return m2938toStringimpl(this.value);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m2939hashCodeimpl(Object arg0) {
        if (arg0 == null) {
            return 0;
        }
        return arg0.hashCode();
    }

    public int hashCode() {
        return m2939hashCodeimpl(this.value);
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m2940equalsimpl(Object arg0, Object other) {
        return (other instanceof SegmentOrClosed) && Intrinsics.areEqual(arg0, ((SegmentOrClosed) other).m2943unboximpl());
    }

    public boolean equals(Object other) {
        return m2940equalsimpl(this.value, other);
    }

    @NotNull
    /* renamed from: constructor-impl, reason: not valid java name */
    public static <S extends Segment<S>> Object m2941constructorimpl(@Nullable Object value) {
        return value;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ SegmentOrClosed m2942boximpl(Object v) {
        return new SegmentOrClosed(v);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m2943unboximpl() {
        return this.value;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2944equalsimpl0(Object p1, Object p2) {
        return Intrinsics.areEqual(p1, p2);
    }

    private /* synthetic */ SegmentOrClosed(Object value) {
        this.value = value;
    }

    /* renamed from: isClosed-impl, reason: not valid java name */
    public static final boolean m2936isClosedimpl(Object arg0) {
        return arg0 == ConcurrentLinkedListKt.CLOSED;
    }

    @NotNull
    /* renamed from: getSegment-impl, reason: not valid java name */
    public static final S m2937getSegmentimpl(Object arg0) {
        if (arg0 == ConcurrentLinkedListKt.CLOSED) {
            throw new IllegalStateException("Does not contain segment".toString());
        }
        Intrinsics.checkNotNull(arg0, "null cannot be cast to non-null type S of kotlinx.coroutines.internal.SegmentOrClosed");
        return (S) arg0;
    }
}
