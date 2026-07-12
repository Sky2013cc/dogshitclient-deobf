package net.ccbluex.liquidbounce.utils.movement;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.event.Listenable;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: BPSUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u00012\u00020\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0006\u0010\n\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/movement/BPSUtils;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", "Lnet/ccbluex/liquidbounce/event/Listenable;", Constants.CTOR, "()V", "lastPosX", "", "lastPosZ", "lastTimestamp", "", "getBPS", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/movement/BPSUtils.class */
public final class BPSUtils implements MinecraftInstance, Listenable {

    @NotNull
    public static final BPSUtils INSTANCE = new BPSUtils();
    private static double lastPosX;
    private static double lastPosZ;
    private static long lastTimestamp;

    private BPSUtils() {
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    @Override // net.ccbluex.liquidbounce.event.Listenable
    public boolean handleEvents() {
        return Listenable.DefaultImpls.handleEvents(this);
    }

    @Override // net.ccbluex.liquidbounce.event.Listenable
    @NotNull
    public Listenable[] getSubListeners() {
        return Listenable.DefaultImpls.getSubListeners(this);
    }

    @Override // net.ccbluex.liquidbounce.event.Listenable
    @Nullable
    public Listenable getParent() {
        return Listenable.DefaultImpls.getParent(this);
    }

    public final double getBPS() {
        EntityPlayerSP player = getMc().field_71439_g;
        if (player == null || player.field_70173_aa < 1 || getMc().field_71441_e == null) {
            return 0.0d;
        }
        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - lastTimestamp;
        double deltaX = player.field_70165_t - lastPosX;
        double deltaZ = player.field_70161_v - lastPosZ;
        double distance = Math.sqrt((deltaX * deltaX) + (deltaZ * deltaZ));
        if (deltaTime <= 0 || distance <= 0.0d) {
            return 0.0d;
        }
        double bps = distance * (1000 / deltaTime);
        lastPosX = player.field_70165_t;
        lastPosZ = player.field_70161_v;
        lastTimestamp = currentTime;
        return bps;
    }
}
