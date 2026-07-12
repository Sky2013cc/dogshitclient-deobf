package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0007\n\u0002\b\n\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lnet/ccbluex/liquidbounce/event/RotationSetEvent;", "Lnet/ccbluex/liquidbounce/event/CancellableEvent;", "yawDiff", "", "pitchDiff", Constants.CTOR, "(FF)V", "getYawDiff", "()F", "setYawDiff", "(F)V", "getPitchDiff", "setPitchDiff", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/RotationSetEvent.class */
public final class RotationSetEvent extends CancellableEvent {
    private float yawDiff;
    private float pitchDiff;

    public RotationSetEvent(float yawDiff, float pitchDiff) {
        this.yawDiff = yawDiff;
        this.pitchDiff = pitchDiff;
    }

    public final float getYawDiff() {
        return this.yawDiff;
    }

    public final void setYawDiff(float f) {
        this.yawDiff = f;
    }

    public final float getPitchDiff() {
        return this.pitchDiff;
    }

    public final void setPitchDiff(float f) {
        this.pitchDiff = f;
    }
}
