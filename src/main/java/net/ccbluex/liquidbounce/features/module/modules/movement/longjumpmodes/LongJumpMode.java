package net.ccbluex.liquidbounce.features.module.modules.movement.longjumpmodes;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.event.JumpEvent;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: LongJumpMode.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\tH\u0016J\b\u0010\u0010\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/movement/longjumpmodes/LongJumpMode;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", "modeName", "", Constants.CTOR, "(Ljava/lang/String;)V", "getModeName", "()Ljava/lang/String;", "onUpdate", "", "onMove", "event", "Lnet/ccbluex/liquidbounce/event/MoveEvent;", "onJump", "Lnet/ccbluex/liquidbounce/event/JumpEvent;", "onEnable", "onDisable", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/movement/longjumpmodes/LongJumpMode.class */
public class LongJumpMode implements MinecraftInstance {

    @NotNull
    private final String modeName;

    public LongJumpMode(@NotNull String modeName) {
        Intrinsics.checkNotNullParameter(modeName, "modeName");
        this.modeName = modeName;
    }

    @NotNull
    public final String getModeName() {
        return this.modeName;
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    public void onUpdate() {
    }

    public void onMove(@NotNull MoveEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
    }

    public void onJump(@NotNull JumpEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
    }

    public void onEnable() {
    }

    public void onDisable() {
    }
}
