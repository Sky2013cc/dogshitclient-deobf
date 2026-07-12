package net.ccbluex.liquidbounce.features.module.modules.player.nofallmodes.aac;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.event.JumpEvent;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.features.module.modules.player.nofallmodes.NoFallMode;
import net.minecraft.client.entity.EntityPlayerSP;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: LAAC.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\r"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/player/nofallmodes/aac/LAAC;", "Lnet/ccbluex/liquidbounce/features/module/modules/player/nofallmodes/NoFallMode;", Constants.CTOR, "()V", "jumped", "", "onUpdate", "", "onJump", "event", "Lnet/ccbluex/liquidbounce/event/JumpEvent;", "onMove", "Lnet/ccbluex/liquidbounce/event/MoveEvent;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/player/nofallmodes/aac/LAAC.class */
public final class LAAC extends NoFallMode {

    @NotNull
    public static final LAAC INSTANCE = new LAAC();
    private static boolean jumped;

    private LAAC() {
        super("LAAC");
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.player.nofallmodes.NoFallMode
    public void onUpdate() {
        EntityPlayerSP thePlayer = getMc().field_71439_g;
        if (thePlayer.field_70122_E) {
            jumped = false;
        }
        if (thePlayer.field_70181_x > 0.0d) {
            jumped = true;
        }
        if (!jumped && thePlayer.field_70122_E && !thePlayer.func_70617_f_() && !thePlayer.func_70090_H() && !thePlayer.field_70134_J) {
            thePlayer.field_70181_x = -6.0d;
        }
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.player.nofallmodes.NoFallMode
    public void onJump(@NotNull JumpEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        jumped = true;
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.player.nofallmodes.NoFallMode
    public void onMove(@NotNull MoveEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        EntityPlayerSP thePlayer = getMc().field_71439_g;
        if (!jumped && !thePlayer.field_70122_E && !thePlayer.func_70617_f_() && !thePlayer.func_70090_H() && !thePlayer.field_70134_J && thePlayer.field_70181_x < 0.0d) {
            event.zeroXZ();
        }
    }
}
