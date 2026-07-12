package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: TimeSource.kt */
@SinceKotlin(version = "1.9")
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018�� \u00062\u00020\u0001:\u0003\u0004\u0005\u0006J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lkotlin/time/TimeSource;", "", "markNow", "Lkotlin/time/TimeMark;", "WithComparableMarks", "Monotonic", "Companion", "kotlin-stdlib"})
@WasExperimental(markerClass = {ExperimentalTime.class})
/* loaded from: target.jar:kotlin/time/TimeSource.class */
public interface TimeSource {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: TimeSource.kt */
    @SinceKotlin(version = "1.9")
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\bg\u0018��2\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lkotlin/time/TimeSource$WithComparableMarks;", "Lkotlin/time/TimeSource;", "markNow", "Lkotlin/time/ComparableTimeMark;", "kotlin-stdlib"})
    @WasExperimental(markerClass = {ExperimentalTime.class})
    /* loaded from: target.jar:kotlin/time/TimeSource$WithComparableMarks.class */
    public interface WithComparableMarks extends TimeSource {
        @Override // kotlin.time.TimeSource
        @NotNull
        ComparableTimeMark markNow();
    }

    @NotNull
    TimeMark markNow();

    /* compiled from: TimeSource.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001:\u0001\nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0004\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lkotlin/time/TimeSource$Monotonic;", "Lkotlin/time/TimeSource$WithComparableMarks;", Constants.CTOR, "()V", "markNow", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "markNow-z9LOYto", "()J", "toString", "", "ValueTimeMark", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/time/TimeSource$Monotonic.class */
    public static final class Monotonic implements WithComparableMarks {

        @NotNull
        public static final Monotonic INSTANCE = new Monotonic();

        private Monotonic() {
        }

        @Override // kotlin.time.TimeSource.WithComparableMarks, kotlin.time.TimeSource
        public /* bridge */ /* synthetic */ ComparableTimeMark markNow() {
            return ValueTimeMark.m2745boximpl(m2728markNowz9LOYto());
        }

        @Override // kotlin.time.TimeSource
        public /* bridge */ /* synthetic */ TimeMark markNow() {
            return ValueTimeMark.m2745boximpl(m2728markNowz9LOYto());
        }

        /* renamed from: markNow-z9LOYto, reason: not valid java name */
        public long m2728markNowz9LOYto() {
            return MonotonicTimeSource.INSTANCE.m2717markNowz9LOYto();
        }

        @NotNull
        public String toString() {
            return MonotonicTimeSource.INSTANCE.toString();
        }

        /* compiled from: TimeSource.kt */
        @SinceKotlin(version = "1.9")
        @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0087@\u0018��2\u00020\u0001B\u0015\b��\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u0006J\u0018\u0010\u000b\u001a\u00020��2\u0006\u0010\f\u001a\u00020\tH\u0096\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u000f\u001a\u00020��2\u0006\u0010\f\u001a\u00020\tH\u0096\u0002¢\u0006\u0004\b\u0010\u0010\u000eJ\u000f\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0016\u0010\u0014J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0001H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020��H\u0086\u0002¢\u0006\u0004\b\u001a\u0010\u000eJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020��H\u0086\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\u001cHÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u0014\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0080\u0004¢\u0006\u0004\n\u0002\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00060\u0003j\u0002`\u0004¨\u0006$"}, d2 = {"Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "Lkotlin/time/ComparableTimeMark;", "reading", "", "Lkotlin/time/ValueTimeMarkReading;", "constructor-impl", "(J)J", "J", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "plus", "duration", "plus-LRDsOJo", "(JJ)J", "minus", "minus-LRDsOJo", "hasPassedNow", "", "hasPassedNow-impl", "(J)Z", "hasNotPassedNow", "hasNotPassedNow-impl", "other", "minus-UwyO8pc", "(JLkotlin/time/ComparableTimeMark;)J", "minus-6eNON_k", "compareTo", "", "compareTo-6eNON_k", "(JJ)I", "equals", "", "hashCode", "toString", "", "kotlin-stdlib"})
        @JvmInline
        @WasExperimental(markerClass = {ExperimentalTime.class})
        /* loaded from: target.jar:kotlin/time/TimeSource$Monotonic$ValueTimeMark.class */
        public static final class ValueTimeMark implements ComparableTimeMark {
            private final long reading;

            /* renamed from: toString-impl, reason: not valid java name */
            public static String m2740toStringimpl(long arg0) {
                return "ValueTimeMark(reading=" + arg0 + ')';
            }

            public String toString() {
                return m2740toStringimpl(this.reading);
            }

            /* renamed from: hashCode-impl, reason: not valid java name */
            public static int m2741hashCodeimpl(long arg0) {
                return Long.hashCode(arg0);
            }

            @Override // kotlin.time.ComparableTimeMark
            public int hashCode() {
                return m2741hashCodeimpl(this.reading);
            }

            /* renamed from: equals-impl, reason: not valid java name */
            public static boolean m2742equalsimpl(long arg0, Object other) {
                return (other instanceof ValueTimeMark) && arg0 == ((ValueTimeMark) other).m2746unboximpl();
            }

            @Override // kotlin.time.ComparableTimeMark
            public boolean equals(Object other) {
                return m2742equalsimpl(this.reading, other);
            }

            /* renamed from: constructor-impl, reason: not valid java name */
            public static long m2744constructorimpl(long reading) {
                return reading;
            }

            /* renamed from: box-impl, reason: not valid java name */
            public static final /* synthetic */ ValueTimeMark m2745boximpl(long v) {
                return new ValueTimeMark(v);
            }

            /* renamed from: unbox-impl, reason: not valid java name */
            public final /* synthetic */ long m2746unboximpl() {
                return this.reading;
            }

            /* renamed from: equals-impl0, reason: not valid java name */
            public static final boolean m2747equalsimpl0(long p1, long p2) {
                return p1 == p2;
            }

            /* renamed from: compareTo-impl, reason: not valid java name */
            public static int m2743compareToimpl(long arg0, @NotNull ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return m2745boximpl(arg0).compareTo(other);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.Comparable
            public int compareTo(@NotNull ComparableTimeMark other) {
                return ComparableTimeMark.DefaultImpls.compareTo(this, other);
            }

            @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
            /* renamed from: plus-LRDsOJo */
            public /* bridge */ /* synthetic */ ComparableTimeMark mo2602plusLRDsOJo(long duration) {
                return m2745boximpl(m2732plusLRDsOJo(duration));
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: plus-LRDsOJo */
            public /* bridge */ /* synthetic */ TimeMark mo2602plusLRDsOJo(long duration) {
                return m2745boximpl(m2732plusLRDsOJo(duration));
            }

            @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
            /* renamed from: minus-LRDsOJo */
            public /* bridge */ /* synthetic */ ComparableTimeMark mo2604minusLRDsOJo(long duration) {
                return m2745boximpl(m2734minusLRDsOJo(duration));
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: minus-LRDsOJo */
            public /* bridge */ /* synthetic */ TimeMark mo2604minusLRDsOJo(long duration) {
                return m2745boximpl(m2734minusLRDsOJo(duration));
            }

            private /* synthetic */ ValueTimeMark(long reading) {
                this.reading = reading;
            }

            /* renamed from: elapsedNow-UwyO8pc, reason: not valid java name */
            public static long m2730elapsedNowUwyO8pc(long arg0) {
                return MonotonicTimeSource.INSTANCE.m2718elapsedFrom6eNON_k(arg0);
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: elapsedNow-UwyO8pc */
            public long mo2601elapsedNowUwyO8pc() {
                return m2730elapsedNowUwyO8pc(this.reading);
            }

            /* renamed from: plus-LRDsOJo, reason: not valid java name */
            public static long m2731plusLRDsOJo(long arg0, long duration) {
                return MonotonicTimeSource.INSTANCE.m2720adjustReading6QKq23U(arg0, duration);
            }

            /* renamed from: plus-LRDsOJo, reason: not valid java name */
            public long m2732plusLRDsOJo(long duration) {
                return m2731plusLRDsOJo(this.reading, duration);
            }

            /* renamed from: minus-LRDsOJo, reason: not valid java name */
            public static long m2733minusLRDsOJo(long arg0, long duration) {
                return MonotonicTimeSource.INSTANCE.m2720adjustReading6QKq23U(arg0, Duration.m2612unaryMinusUwyO8pc(duration));
            }

            /* renamed from: minus-LRDsOJo, reason: not valid java name */
            public long m2734minusLRDsOJo(long duration) {
                return m2733minusLRDsOJo(this.reading, duration);
            }

            /* renamed from: hasPassedNow-impl, reason: not valid java name */
            public static boolean m2735hasPassedNowimpl(long arg0) {
                return !Duration.m2622isNegativeimpl(m2730elapsedNowUwyO8pc(arg0));
            }

            @Override // kotlin.time.TimeMark
            public boolean hasPassedNow() {
                return m2735hasPassedNowimpl(this.reading);
            }

            /* renamed from: hasNotPassedNow-impl, reason: not valid java name */
            public static boolean m2736hasNotPassedNowimpl(long arg0) {
                return Duration.m2622isNegativeimpl(m2730elapsedNowUwyO8pc(arg0));
            }

            @Override // kotlin.time.TimeMark
            public boolean hasNotPassedNow() {
                return m2736hasNotPassedNowimpl(this.reading);
            }

            @Override // kotlin.time.ComparableTimeMark
            /* renamed from: minus-UwyO8pc */
            public long mo2603minusUwyO8pc(@NotNull ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return m2737minusUwyO8pc(this.reading, other);
            }

            /* renamed from: minus-UwyO8pc, reason: not valid java name */
            public static long m2737minusUwyO8pc(long arg0, @NotNull ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                if (!(other instanceof ValueTimeMark)) {
                    throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + ((Object) m2740toStringimpl(arg0)) + " and " + other);
                }
                return m2738minus6eNON_k(arg0, ((ValueTimeMark) other).m2746unboximpl());
            }

            /* renamed from: minus-6eNON_k, reason: not valid java name */
            public static final long m2738minus6eNON_k(long arg0, long other) {
                return MonotonicTimeSource.INSTANCE.m2719differenceBetweenfRLX17w(arg0, other);
            }

            /* renamed from: compareTo-6eNON_k, reason: not valid java name */
            public static final int m2739compareTo6eNON_k(long arg0, long other) {
                return Duration.m2627compareToLRDsOJo(m2738minus6eNON_k(arg0, other), Duration.Companion.m2659getZEROUwyO8pc());
            }
        }
    }

    /* compiled from: TimeSource.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlin/time/TimeSource$Companion;", "", Constants.CTOR, "()V", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/time/TimeSource$Companion.class */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }
}
