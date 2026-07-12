package net.ccbluex.liquidbounce.utils.simulation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;

/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/simulation/SimulatedPlayerJavaExtensions.class */
public class SimulatedPlayerJavaExtensions {
    public Pair<Double, Double> checkForCollision(SimulatedPlayer simPlayer, double velocityX, double velocityZ) {
        double d;
        double d2;
        double d3;
        EntityPlayerSP player = Minecraft.func_71410_x().field_71439_g;
        World worldObj = player.field_70170_p;
        double d32 = velocityX;
        double d5 = velocityZ;
        while (velocityX != 0.0d && worldObj.func_72945_a(player, simPlayer.getBox().func_72317_d(velocityX, -1.0d, 0.0d)).isEmpty()) {
            if (velocityX >= 0.05d || velocityX < (-0.05d)) {
                d3 = velocityX > 0.0d ? velocityX - 0.05d : velocityX + 0.05d;
            } else {
                d3 = 0.0d;
            }
            velocityX = d3;
            d32 = velocityX;
        }
        while (velocityZ != 0.0d && worldObj.func_72945_a(player, simPlayer.getBox().func_72317_d(0.0d, -1.0d, velocityZ)).isEmpty()) {
            if (velocityZ >= 0.05d || velocityZ < (-0.05d)) {
                d2 = velocityZ > 0.0d ? velocityZ - 0.05d : velocityZ + 0.05d;
            } else {
                d2 = 0.0d;
            }
            velocityZ = d2;
            d5 = velocityZ;
        }
        while (velocityX != 0.0d && velocityZ != 0.0d && worldObj.func_72945_a(player, simPlayer.getBox().func_72317_d(velocityX, -1.0d, velocityZ)).isEmpty()) {
            if (velocityX >= 0.05d || velocityX < (-0.05d)) {
                velocityX = velocityX > 0.0d ? velocityX - 0.05d : velocityX + 0.05d;
            } else {
                velocityX = 0.0d;
            }
            d32 = velocityX;
            if (velocityZ >= 0.05d || velocityZ < (-0.05d)) {
                d = velocityZ > 0.0d ? velocityZ - 0.05d : velocityZ + 0.05d;
            } else {
                d = 0.0d;
            }
            velocityZ = d;
            d5 = velocityZ;
        }
        return Pair.of(Double.valueOf(d32), Double.valueOf(d5));
    }
}
