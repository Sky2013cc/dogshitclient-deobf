package net.ccbluex.liquidbounce.features.module.modules.movement.flymodes.aac;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.features.module.modules.movement.flymodes.FlyMode;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: AAC3312Glide.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0010\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\b"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/movement/flymodes/aac/AAC3312Glide;", "Lnet/ccbluex/liquidbounce/features/module/modules/movement/flymodes/FlyMode;", Constants.CTOR, "()V", "tick", "", "onUpdate", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/movement/flymodes/aac/AAC3312Glide.class */
public final class AAC3312Glide extends FlyMode {

    @NotNull
    public static final AAC3312Glide INSTANCE = new AAC3312Glide();
    private static int tick;

    private AAC3312Glide() {
        super("AAC3.3.12-Glide");
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.movement.flymodes.FlyMode
    public void onUpdate() {
        if (!getMc().field_71439_g.field_70122_E) {
            tick++;
        }
        if (tick != 2) {
            if (tick != 12) {
                if (tick >= 12 && !getMc().field_71439_g.field_70122_E) {
                    tick = 0;
                    getMc().field_71439_g.field_70181_x = 0.015d;
                    return;
                }
                return;
            }
            getMc().field_71428_T.field_74278_d = 0.1f;
            return;
        }
        getMc().field_71428_T.field_74278_d = 1.0f;
    }
}
