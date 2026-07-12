package net.ccbluex.liquidbounce.features.module.modules.movement.nowebmodes.intave;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.features.module.modules.movement.nowebmodes.NoWebMode;
import net.minecraft.client.entity.EntityPlayerSP;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: IntaveOld.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/movement/nowebmodes/intave/IntaveOld;", "Lnet/ccbluex/liquidbounce/features/module/modules/movement/nowebmodes/NoWebMode;", Constants.CTOR, "()V", "onUpdate", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/movement/nowebmodes/intave/IntaveOld.class */
public final class IntaveOld extends NoWebMode {

    @NotNull
    public static final IntaveOld INSTANCE = new IntaveOld();

    private IntaveOld() {
        super("IntaveOld");
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.movement.nowebmodes.NoWebMode
    public void onUpdate() {
        EntityPlayerSP thePlayer = getMc().field_71439_g;
        if (thePlayer != null && thePlayer.field_70134_J) {
            if ((thePlayer.field_71158_b.field_78902_a == 0.0f) && getMc().field_71474_y.field_74351_w.func_151470_d() && thePlayer.field_70124_G) {
                thePlayer.field_70747_aH = 0.74f;
            } else {
                thePlayer.field_70747_aH = 0.2f;
                thePlayer.field_70122_E = true;
            }
        }
    }
}
