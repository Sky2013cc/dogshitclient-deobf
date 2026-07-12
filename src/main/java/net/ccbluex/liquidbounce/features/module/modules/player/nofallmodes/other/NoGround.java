package net.ccbluex.liquidbounce.features.module.modules.player.nofallmodes.other;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.features.module.modules.player.nofallmodes.NoFallMode;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: NoGround.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/player/nofallmodes/other/NoGround;", "Lnet/ccbluex/liquidbounce/features/module/modules/player/nofallmodes/NoFallMode;", Constants.CTOR, "()V", "onPacket", "", "event", "Lnet/ccbluex/liquidbounce/event/PacketEvent;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/player/nofallmodes/other/NoGround.class */
public final class NoGround extends NoFallMode {

    @NotNull
    public static final NoGround INSTANCE = new NoGround();

    private NoGround() {
        super("NoGround");
    }

    @Override // net.ccbluex.liquidbounce.features.module.modules.player.nofallmodes.NoFallMode
    public void onPacket(@NotNull PacketEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getPacket() instanceof C03PacketPlayer) {
            event.getPacket().field_149474_g = false;
        }
    }
}
