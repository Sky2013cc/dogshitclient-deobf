package net.ccbluex.liquidbounce.features.module.modules.movement.flymodes.vulcan;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.features.module.modules.movement.flymodes.FlyMode;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: VulcanOld.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/movement/flymodes/vulcan/VulcanOld;", "Lnet/ccbluex/liquidbounce/features/module/modules/movement/flymodes/FlyMode;", Constants.CTOR, "()V", "onUpdate", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/movement/flymodes/vulcan/VulcanOld.class */
public final class VulcanOld extends FlyMode {

    @NotNull
    public static final VulcanOld INSTANCE = new VulcanOld();

    private VulcanOld() {
        super("VulcanOld");
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.movement.flymodes.FlyMode
    public void onUpdate() {
        if (!getMc().field_71439_g.field_70122_E && getMc().field_71439_g.field_70143_R > 0.0f) {
            if (getMc().field_71439_g.field_70173_aa % 2 == 0) {
                getMc().field_71439_g.field_70181_x = -0.1d;
                getMc().field_71439_g.field_70747_aH = 0.0265f;
            } else {
                getMc().field_71439_g.field_70181_x = -0.16d;
                getMc().field_71439_g.field_70747_aH = 0.0265f;
            }
        }
    }
}
