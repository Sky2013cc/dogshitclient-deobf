package net.ccbluex.liquidbounce.utils.render.animation;

import com.sun.tools.doclets.internal.toolkit.util.VisibleMemberMap;
import jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Animation.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0007\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0018\u001a\u00020\u0016J\u0006\u0010\u0019\u001a\u00020\u0016J&\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n��R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n��R\u001e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001b"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/animation/Animation;", "", Constants.CTOR, "()V", "duration", "", "startTime", VisibleMemberMap.STARTLEVEL, "", "value", "getValue", "()D", "setValue", "(D)V", AsmConstants.END, "getEnd", "setEnd", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_TYPE, "Lnet/ccbluex/liquidbounce/utils/render/animation/AnimationType;", "", "isStarted", "()Z", "", "", "update", "reset", "fstart", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/animation/Animation.class */
public final class Animation {
    private long duration;
    private long startTime;
    private double start;
    private double value;
    private double end;

    @NotNull
    private AnimationType type = AnimationType.LINEAR;
    private boolean isStarted;

    /* compiled from: Animation.kt */
    @Metadata(mv = {2, 0, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/animation/Animation$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnimationType.values().length];
            try {
                iArr[AnimationType.LINEAR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AnimationType.EASE_IN_QUAD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AnimationType.EASE_OUT_QUAD.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AnimationType.EASE_IN_OUT_QUAD.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[AnimationType.EASE_IN_ELASTIC.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[AnimationType.EASE_OUT_ELASTIC.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[AnimationType.EASE_IN_OUT_ELASTIC.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[AnimationType.EASE_IN_BACK.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[AnimationType.EASE_OUT_BACK.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final double getValue() {
        return this.value;
    }

    public final void setValue(double d) {
        this.value = d;
    }

    public final double getEnd() {
        return this.end;
    }

    public final void setEnd(double d) {
        this.end = d;
    }

    public final boolean isStarted() {
        return this.isStarted;
    }

    public final void start(double start, double end, float duration, @NotNull AnimationType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (this.isStarted) {
            return;
        }
        if (start == this.start) {
            if ((end == this.end) && duration * 1000 == this.duration && type == this.type) {
                return;
            }
        }
        this.duration = duration * 1000;
        this.start = start;
        this.startTime = System.currentTimeMillis();
        this.value = start;
        this.end = end;
        this.type = type;
        this.isStarted = true;
    }

    public final void update() {
        double d;
        if (this.isStarted) {
            switch (WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()]) {
                case 1:
                    d = AnimationUtil.INSTANCE.linear(this.startTime, this.duration, this.start, this.end);
                    break;
                case 2:
                    d = AnimationUtil.INSTANCE.easeInQuad(this.startTime, this.duration, this.start, this.end);
                    break;
                case 3:
                    d = AnimationUtil.INSTANCE.easeOutQuad(this.startTime, this.duration, this.start, this.end);
                    break;
                case 4:
                    d = AnimationUtil.INSTANCE.easeInOutQuad(this.startTime, this.duration, this.start, this.end);
                    break;
                case 5:
                    d = AnimationUtil.INSTANCE.easeInElastic(System.currentTimeMillis() - this.startTime, this.start, this.end - this.start, this.duration);
                    break;
                case 6:
                    d = AnimationUtil.INSTANCE.easeOutElastic(System.currentTimeMillis() - this.startTime, this.start, this.end - this.start, this.duration);
                    break;
                case 7:
                    d = AnimationUtil.INSTANCE.easeInOutElastic(System.currentTimeMillis() - this.startTime, this.start, this.end - this.start, this.duration);
                    break;
                case 8:
                    d = AnimationUtil.INSTANCE.easeInBack(System.currentTimeMillis() - this.startTime, this.start, this.end - this.start, this.duration);
                    break;
                case 9:
                    d = AnimationUtil.INSTANCE.easeOutBack(System.currentTimeMillis() - this.startTime, this.start, this.end - this.start, this.duration);
                    break;
                default:
                    d = this.value;
                    break;
            }
            double result = d;
            this.value = result;
            if (System.currentTimeMillis() - this.startTime > this.duration) {
                this.isStarted = false;
                this.value = this.end;
            }
        }
    }

    public final void reset() {
        this.value = 0.0d;
        this.start = 0.0d;
        this.end = 0.0d;
        this.startTime = System.currentTimeMillis();
        this.isStarted = false;
    }

    public final void fstart(double start, double end, float duration, @NotNull AnimationType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.isStarted = false;
        start(start, end, duration, type);
    }
}
