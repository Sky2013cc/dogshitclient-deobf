package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import org.jetbrains.annotations.NotNull;

/* compiled from: TimeSource.kt */
@SinceKotlin(version = "1.9")
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018��2\u00020\u0001J\u000f\u0010\u0002\u001a\u00020\u0003H&¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020��2\u0006\u0010\u0007\u001a\u00020\u0003H\u0096\u0002¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\n\u001a\u00020��2\u0006\u0010\u0007\u001a\u00020\u0003H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016¨\u0006\u000f"}, d2 = {"Lkotlin/time/TimeMark;", "", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "()J", "plus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/TimeMark;", "minus", "minus-LRDsOJo", "hasPassedNow", "", "hasNotPassedNow", "kotlin-stdlib"})
@WasExperimental(markerClass = {ExperimentalTime.class})
/* loaded from: target.jar:kotlin/time/TimeMark.class */
public interface TimeMark {
    /* renamed from: elapsedNow-UwyO8pc */
    long mo2601elapsedNowUwyO8pc();

    @NotNull
    /* renamed from: plus-LRDsOJo */
    TimeMark mo2602plusLRDsOJo(long j);

    @NotNull
    /* renamed from: minus-LRDsOJo */
    TimeMark mo2604minusLRDsOJo(long j);

    boolean hasPassedNow();

    boolean hasNotPassedNow();

    /* compiled from: TimeSource.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlin/time/TimeMark$DefaultImpls.class */
    public static final class DefaultImpls {
        @NotNull
        /* renamed from: plus-LRDsOJo, reason: not valid java name */
        public static TimeMark m2724plusLRDsOJo(@NotNull TimeMark $this, long duration) {
            return new AdjustedTimeMark($this, duration, null);
        }

        @NotNull
        /* renamed from: minus-LRDsOJo, reason: not valid java name */
        public static TimeMark m2725minusLRDsOJo(@NotNull TimeMark $this, long duration) {
            return $this.mo2602plusLRDsOJo(Duration.m2612unaryMinusUwyO8pc(duration));
        }

        public static boolean hasPassedNow(@NotNull TimeMark $this) {
            return !Duration.m2622isNegativeimpl($this.mo2601elapsedNowUwyO8pc());
        }

        public static boolean hasNotPassedNow(@NotNull TimeMark $this) {
            return Duration.m2622isNegativeimpl($this.mo2601elapsedNowUwyO8pc());
        }
    }
}
