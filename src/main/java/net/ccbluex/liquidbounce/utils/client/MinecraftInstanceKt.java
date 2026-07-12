package net.ccbluex.liquidbounce.utils.client;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/* compiled from: MinecraftInstance.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\u001e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0007\n��\n\u0002\u0010\u000e\n��\u001a\u001c\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a\n\u0010\u0007\u001a\u00020\u0004*\u00020\b¨\u0006\t"}, d2 = {"playSound", "", "Lnet/minecraft/client/Minecraft;", "resourceLocation", "Lnet/minecraft/util/ResourceLocation;", "pitch", "", "asResourceLocation", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/MinecraftInstanceKt.class */
public final class MinecraftInstanceKt {
    public static /* synthetic */ void playSound$default(Minecraft minecraft, ResourceLocation resourceLocation, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 1.0f;
        }
        playSound(minecraft, resourceLocation, f);
    }

    public static final void playSound(@NotNull Minecraft $this$playSound, @NotNull ResourceLocation resourceLocation, float pitch) {
        Intrinsics.checkNotNullParameter($this$playSound, "<this>");
        Intrinsics.checkNotNullParameter(resourceLocation, "resourceLocation");
        SoundHandler func_147118_V = $this$playSound.func_147118_V();
        Intrinsics.checkNotNullExpressionValue(func_147118_V, "getSoundHandler(...)");
        synchronized (func_147118_V) {
            $this$playSound.func_147118_V().func_147682_a(PositionedSoundRecord.func_147674_a(resourceLocation, pitch));
            Unit unit = Unit.INSTANCE;
        }
    }

    @NotNull
    public static final ResourceLocation asResourceLocation(@NotNull String $this$asResourceLocation) {
        Intrinsics.checkNotNullParameter($this$asResourceLocation, "<this>");
        return new ResourceLocation($this$asResourceLocation);
    }
}
