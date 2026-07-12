package net.ccbluex.liquidbounce.utils.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;
import org.jetbrains.annotations.NotNull;

/* compiled from: NetworkExtension.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n��\u001a\n\u0010��\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"getFullName", "", "Lnet/minecraft/client/network/NetworkPlayerInfo;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/extensions/NetworkExtensionKt.class */
public final class NetworkExtensionKt {
    @NotNull
    public static final String getFullName(@NotNull NetworkPlayerInfo $this$getFullName) {
        Intrinsics.checkNotNullParameter($this$getFullName, "<this>");
        if ($this$getFullName.func_178854_k() != null) {
            String func_150254_d = $this$getFullName.func_178854_k().func_150254_d();
            Intrinsics.checkNotNullExpressionValue(func_150254_d, "getFormattedText(...)");
            return func_150254_d;
        }
        ScorePlayerTeam team = $this$getFullName.func_178850_i();
        String name = $this$getFullName.func_178845_a().getName();
        if (team != null) {
            String func_142053_d = team.func_142053_d(name);
            if (func_142053_d != null) {
                return func_142053_d;
            }
        }
        Intrinsics.checkNotNull(name);
        return name;
    }
}
