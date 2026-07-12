package net.ccbluex.liquidbounce.features.module.modules.combat;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import kotlin.Metadata;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Backtrack.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/combat/BacktrackData;", "", SimpleTaglet.EXCLUDED, "", OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "z", "time", "", Constants.CTOR, "(DDDJ)V", "getX", "()D", "getY", "getZ", "getTime", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/combat/BacktrackData.class */
public final class BacktrackData {
    private final double x;
    private final double y;
    private final double z;
    private final long time;

    public final double component1() {
        return this.x;
    }

    public final double component2() {
        return this.y;
    }

    public final double component3() {
        return this.z;
    }

    public final long component4() {
        return this.time;
    }

    @NotNull
    public final BacktrackData copy(double x, double y, double z, long time) {
        return new BacktrackData(x, y, z, time);
    }

    public static /* synthetic */ BacktrackData copy$default(BacktrackData backtrackData, double d, double d2, double d3, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            d = backtrackData.x;
        }
        if ((i & 2) != 0) {
            d2 = backtrackData.y;
        }
        if ((i & 4) != 0) {
            d3 = backtrackData.z;
        }
        if ((i & 8) != 0) {
            j = backtrackData.time;
        }
        return backtrackData.copy(d, d2, d3, j);
    }

    @NotNull
    public String toString() {
        return "BacktrackData(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", time=" + this.time + ')';
    }

    public int hashCode() {
        int result = Double.hashCode(this.x);
        return (((((result * 31) + Double.hashCode(this.y)) * 31) + Double.hashCode(this.z)) * 31) + Long.hashCode(this.time);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BacktrackData)) {
            return false;
        }
        BacktrackData backtrackData = (BacktrackData) other;
        return Double.compare(this.x, backtrackData.x) == 0 && Double.compare(this.y, backtrackData.y) == 0 && Double.compare(this.z, backtrackData.z) == 0 && this.time == backtrackData.time;
    }

    public BacktrackData(double x, double y, double z, long time) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.time = time;
    }

    public final double getX() {
        return this.x;
    }

    public final double getY() {
        return this.y;
    }

    public final double getZ() {
        return this.z;
    }

    public final long getTime() {
        return this.time;
    }
}
