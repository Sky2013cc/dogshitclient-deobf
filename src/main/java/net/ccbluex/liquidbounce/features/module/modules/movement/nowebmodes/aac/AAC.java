package net.ccbluex.liquidbounce.features.module.modules.movement.nowebmodes.aac;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.features.module.modules.movement.nowebmodes.NoWebMode;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: AAC.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/movement/nowebmodes/aac/AAC;", "Lnet/ccbluex/liquidbounce/features/module/modules/movement/nowebmodes/NoWebMode;", Constants.CTOR, "()V", "onUpdate", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/movement/nowebmodes/aac/AAC.class */
public final class AAC extends NoWebMode {

    @NotNull
    public static final AAC INSTANCE = new AAC();

    private AAC() {
        super("AAC");
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.movement.nowebmodes.NoWebMode
    public void onUpdate() {
        if (!getMc().field_71439_g.field_70134_J) {
            return;
        }
        getMc().field_71439_g.field_70747_aH = 0.59f;
        if (!getMc().field_71474_y.field_74311_E.func_151470_d()) {
            getMc().field_71439_g.field_70181_x = 0.0d;
        }
    }
}
