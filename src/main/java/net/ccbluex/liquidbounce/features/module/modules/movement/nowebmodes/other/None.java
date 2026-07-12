package net.ccbluex.liquidbounce.features.module.modules.movement.nowebmodes.other;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.features.module.modules.movement.nowebmodes.NoWebMode;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: None.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/movement/nowebmodes/other/None;", "Lnet/ccbluex/liquidbounce/features/module/modules/movement/nowebmodes/NoWebMode;", Constants.CTOR, "()V", "onUpdate", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/movement/nowebmodes/other/None.class */
public final class None extends NoWebMode {

    @NotNull
    public static final None INSTANCE = new None();

    private None() {
        super("None");
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.movement.nowebmodes.NoWebMode
    public void onUpdate() {
        getMc().field_71439_g.field_70134_J = false;
    }
}
