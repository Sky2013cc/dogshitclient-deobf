package net.ccbluex.liquidbounce.features.module.modules.movement.flymodes.other;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.features.module.modules.movement.flymodes.FlyMode;
import net.minecraft.util.EnumParticleTypes;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Jetpack.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/movement/flymodes/other/Jetpack;", "Lnet/ccbluex/liquidbounce/features/module/modules/movement/flymodes/FlyMode;", Constants.CTOR, "()V", "onUpdate", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/movement/flymodes/other/Jetpack.class */
public final class Jetpack extends FlyMode {

    @NotNull
    public static final Jetpack INSTANCE = new Jetpack();

    private Jetpack() {
        super("Jetpack");
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.movement.flymodes.FlyMode
    public void onUpdate() {
        if (!getMc().field_71474_y.field_74314_A.func_151470_d()) {
            return;
        }
        getMc().field_71452_i.func_178927_a(EnumParticleTypes.FLAME.func_179348_c(), getMc().field_71439_g.field_70165_t, getMc().field_71439_g.field_70163_u + 0.2d, getMc().field_71439_g.field_70161_v, -getMc().field_71439_g.field_70159_w, -0.5d, -getMc().field_71439_g.field_70179_y, new int[0]);
        getMc().field_71439_g.field_70181_x += 0.15d;
        getMc().field_71439_g.field_70159_w *= 1.1d;
        getMc().field_71439_g.field_70179_y *= 1.1d;
    }
}
