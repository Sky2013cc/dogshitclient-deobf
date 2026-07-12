package net.ccbluex.liquidbounce.features.module.modules.player.nofallmodes.other;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.features.module.modules.player.nofallmodes.NoFallMode;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Hypixel.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\n"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/player/nofallmodes/other/Hypixel;", "Lnet/ccbluex/liquidbounce/features/module/modules/player/nofallmodes/NoFallMode;", Constants.CTOR, "()V", "jump", "", "onPacket", "", "event", "Lnet/ccbluex/liquidbounce/event/PacketEvent;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/player/nofallmodes/other/Hypixel.class */
public final class Hypixel extends NoFallMode {

    @NotNull
    public static final Hypixel INSTANCE = new Hypixel();
    private static boolean jump;

    private Hypixel() {
        super("Hypixel");
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.player.nofallmodes.NoFallMode
    public void onPacket(@NotNull PacketEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        EntityPlayerSP player = getMc().field_71439_g;
        if (player == null) {
            return;
        }
        C03PacketPlayer.C04PacketPlayerPosition packet = event.getPacket();
        if (packet instanceof C03PacketPlayer.C04PacketPlayerPosition) {
            if (player.field_70143_R >= 3.3d) {
                jump = true;
            }
            if (jump && player.field_70122_E) {
                packet.field_149474_g = false;
                if (!getMc().field_71474_y.field_74314_A.func_151470_d()) {
                    player.func_70107_b(packet.func_149464_c(), packet.func_149467_d() + 0.09d, packet.func_149472_e());
                }
                jump = false;
            }
        }
    }
}
