package net.ccbluex.liquidbounce.utils.render;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import org.spongepowered.asm.util.Constants;

/* compiled from: EasingObject.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u0007\n\u0002\b\u000f\u0018��2\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010¨\u0006\u0014"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/EasingObject;", "", "lastTime", "", "lastValue", "", "currentValue", Constants.CTOR, "(JFF)V", "getLastTime", "()J", "setLastTime", "(J)V", "getLastValue", "()F", "setLastValue", "(F)V", "getCurrentValue", "setCurrentValue", "update", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/EasingObject.class */
public final class EasingObject {
    private long lastTime;
    private float lastValue;
    private float currentValue;

    public EasingObject() {
        this(0L, 0.0f, 0.0f, 7, null);
    }

    public EasingObject(long lastTime, float lastValue, float currentValue) {
        this.lastTime = lastTime;
        this.lastValue = lastValue;
        this.currentValue = currentValue;
    }

    public /* synthetic */ EasingObject(long j, float f, float f2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, (i & 2) != 0 ? -1.0f : f, (i & 4) != 0 ? -1.0f : f2);
    }

    public final long getLastTime() {
        return this.lastTime;
    }

    public final void setLastTime(long j) {
        this.lastTime = j;
    }

    public final float getLastValue() {
        return this.lastValue;
    }

    public final void setLastValue(float f) {
        this.lastValue = f;
    }

    public final float getCurrentValue() {
        return this.currentValue;
    }

    public final void setCurrentValue(float f) {
        this.currentValue = f;
    }

    public final float update(float currentValue) {
        if (!(currentValue == this.currentValue)) {
            this.lastValue = RangesKt.coerceAtMost(currentValue, this.currentValue);
            this.currentValue = currentValue;
            this.lastTime = System.currentTimeMillis();
        }
        return (AnimationUtils.INSTANCE.easeOutElastic(RangesKt.coerceIn(((float) (System.currentTimeMillis() - this.lastTime)) / 500.0f, 0.0f, 1.0f)) * (currentValue - this.lastValue)) + this.lastValue;
    }
}
