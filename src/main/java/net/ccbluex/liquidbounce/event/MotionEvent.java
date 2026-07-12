package net.ccbluex.liquidbounce.event;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018��2\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lnet/ccbluex/liquidbounce/event/MotionEvent;", "Lnet/ccbluex/liquidbounce/event/Event;", SimpleTaglet.EXCLUDED, "", OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "z", "onGround", "", "eventState", "Lnet/ccbluex/liquidbounce/event/EventState;", Constants.CTOR, "(DDDZLnet/ccbluex/liquidbounce/event/EventState;)V", "getX", "()D", "setX", "(D)V", "getY", "setY", "getZ", "setZ", "getOnGround", "()Z", "setOnGround", "(Z)V", "getEventState", "()Lnet/ccbluex/liquidbounce/event/EventState;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/MotionEvent.class */
public final class MotionEvent extends Event {
    private double x;
    private double y;
    private double z;
    private boolean onGround;

    @NotNull
    private final EventState eventState;

    public final double getX() {
        return this.x;
    }

    public final void setX(double d) {
        this.x = d;
    }

    public final double getY() {
        return this.y;
    }

    public final void setY(double d) {
        this.y = d;
    }

    public final double getZ() {
        return this.z;
    }

    public final void setZ(double d) {
        this.z = d;
    }

    public final boolean getOnGround() {
        return this.onGround;
    }

    public final void setOnGround(boolean z) {
        this.onGround = z;
    }

    @NotNull
    public final EventState getEventState() {
        return this.eventState;
    }

    public MotionEvent(double x, double y, double z, boolean onGround, @NotNull EventState eventState) {
        Intrinsics.checkNotNullParameter(eventState, "eventState");
        this.x = x;
        this.y = y;
        this.z = z;
        this.onGround = onGround;
        this.eventState = eventState;
    }
}
