package net.ccbluex.liquidbounce.utils.client;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: MinecraftInstance.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018�� \u00062\u00020\u0001:\u0001\u0006R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", "", "mc", "Lnet/minecraft/client/Minecraft;", "getMc", "()Lnet/minecraft/client/Minecraft;", "Companion", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/MinecraftInstance.class */
public interface MinecraftInstance {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    @JvmField
    @NotNull
    public static final Minecraft mc;

    @NotNull
    Minecraft getMc();

    /* compiled from: MinecraftInstance.kt */
    @Metadata(mv = {2, 0, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/MinecraftInstance$DefaultImpls.class */
    public static final class DefaultImpls {
        @NotNull
        public static Minecraft getMc(@NotNull MinecraftInstance $this) {
            return MinecraftInstance.mc;
        }
    }

    /* compiled from: MinecraftInstance.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\u0001¨\u0006\u0006"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance$Companion;", "", Constants.CTOR, "()V", "mc", "Lnet/minecraft/client/Minecraft;", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/MinecraftInstance$Companion.class */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    static {
        Minecraft func_71410_x = Minecraft.func_71410_x();
        Intrinsics.checkNotNullExpressionValue(func_71410_x, "getMinecraft(...)");
        mc = func_71410_x;
    }
}
