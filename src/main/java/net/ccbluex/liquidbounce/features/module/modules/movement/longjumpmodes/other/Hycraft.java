package net.ccbluex.liquidbounce.features.module.modules.movement.longjumpmodes.other;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.features.module.modules.movement.longjumpmodes.LongJumpMode;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Hycraft.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/movement/longjumpmodes/other/Hycraft;", "Lnet/ccbluex/liquidbounce/features/module/modules/movement/longjumpmodes/LongJumpMode;", Constants.CTOR, "()V", "onUpdate", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/movement/longjumpmodes/other/Hycraft.class */
public final class Hycraft extends LongJumpMode {

    @NotNull
    public static final Hycraft INSTANCE = new Hycraft();

    private Hycraft() {
        super("Hycraft");
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.movement.longjumpmodes.LongJumpMode
    public void onUpdate() {
        if (getMc().field_71439_g.field_70181_x < 0.0d) {
            getMc().field_71439_g.field_70181_x *= 0.75f;
            getMc().field_71439_g.field_70747_aH = 0.055f;
        } else {
            getMc().field_71439_g.field_70181_x += 0.02f;
            getMc().field_71439_g.field_70747_aH = 0.08f;
        }
    }
}
