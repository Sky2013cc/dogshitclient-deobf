package net.ccbluex.liquidbounce.utils.pathfinding;

import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3d;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: PathUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0010\u0006\n\u0002\b\f\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bJ,\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bJ8\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002¨\u0006\u0014"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/pathfinding/PathUtils;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", Constants.CTOR, "()V", "findBlinkPath", "", "Ljavax/vecmath/Vector3d;", "tpX", "", "tpY", "tpZ", "findPath", "offset", "getDistance", "x1", "y1", "z1", "x2", "y2", "z2", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/pathfinding/PathUtils.class */
public final class PathUtils implements MinecraftInstance {

    @NotNull
    public static final PathUtils INSTANCE = new PathUtils();

    private PathUtils() {
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    @NotNull
    public final List<Vector3d> findBlinkPath(double tpX, double tpY, double tpZ) {
        List positions = new ArrayList();
        double curX = getMc().field_71439_g.field_70165_t;
        double curY = getMc().field_71439_g.field_70163_u;
        double curZ = getMc().field_71439_g.field_70161_v;
        double distance = Math.abs(curX - tpX) + Math.abs(curY - tpY) + Math.abs(curZ - tpZ);
        int count = 0;
        while (distance > 0.0d) {
            distance = Math.abs(curX - tpX) + Math.abs(curY - tpY) + Math.abs(curZ - tpZ);
            double diffX = curX - tpX;
            double diffY = curY - tpY;
            double diffZ = curZ - tpZ;
            double offset = (count & 1) == 0 ? 0.4d : 0.1d;
            double minX = RangesKt.coerceIn(diffX, -offset, offset);
            curX -= minX;
            double minY = RangesKt.coerceIn(diffY, -0.25d, 0.25d);
            curY -= minY;
            double minZ = RangesKt.coerceIn(diffZ, -offset, offset);
            curZ -= minZ;
            positions.add(new Vector3d(curX, curY, curZ));
            count++;
        }
        return positions;
    }

    @NotNull
    public final List<Vector3d> findPath(double tpX, double tpY, double tpZ, double offset) {
        List positions = new ArrayList();
        double steps = Math.ceil(getDistance(getMc().field_71439_g.field_70165_t, getMc().field_71439_g.field_70163_u, getMc().field_71439_g.field_70161_v, tpX, tpY, tpZ) / offset);
        double dX = tpX - getMc().field_71439_g.field_70165_t;
        double dY = tpY - getMc().field_71439_g.field_70163_u;
        double dZ = tpZ - getMc().field_71439_g.field_70161_v;
        double d = 1.0d;
        while (true) {
            double d2 = d;
            if (d2 <= steps) {
                positions.add(new Vector3d(getMc().field_71439_g.field_70165_t + ((dX * d2) / steps), getMc().field_71439_g.field_70163_u + ((dY * d2) / steps), getMc().field_71439_g.field_70161_v + ((dZ * d2) / steps)));
                d = d2 + 1.0d;
            } else {
                return positions;
            }
        }
    }

    private final double getDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
        double xDiff = x1 - x2;
        double yDiff = y1 - y2;
        double zDiff = z1 - z2;
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff) + (zDiff * zDiff));
    }
}
