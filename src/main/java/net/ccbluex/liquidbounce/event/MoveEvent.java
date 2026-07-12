package net.ccbluex.liquidbounce.event;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import kotlin.Metadata;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, d2 = {"Lnet/ccbluex/liquidbounce/event/MoveEvent;", "Lnet/ccbluex/liquidbounce/event/CancellableEvent;", SimpleTaglet.EXCLUDED, "", OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "z", Constants.CTOR, "(DDD)V", "getX", "()D", "setX", "(D)V", "getY", "setY", "getZ", "setZ", "isSafeWalk", "", "()Z", "setSafeWalk", "(Z)V", "zero", "", "zeroXZ", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/MoveEvent.class */
public final class MoveEvent extends CancellableEvent {
    private double x;
    private double y;
    private double z;
    private boolean isSafeWalk;

    public MoveEvent(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

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

    public final boolean isSafeWalk() {
        return this.isSafeWalk;
    }

    public final void setSafeWalk(boolean z) {
        this.isSafeWalk = z;
    }

    public final void zero() {
        this.x = 0.0d;
        this.y = 0.0d;
        this.z = 0.0d;
    }

    public final void zeroXZ() {
        this.x = 0.0d;
        this.z = 0.0d;
    }
}
