package net.ccbluex.liquidbounce.utils.client;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.lang.reflect.Field;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ClientUtils.kt */
@SideOnly(Side.CLIENT)
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0016\u001a\u00020\u0017J*\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\rR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n��R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/client/ClientUtils;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", Constants.CTOR, "()V", "fastRenderField", "Ljava/lang/reflect/Field;", "runTimeTicks", "", "getRunTimeTicks", "()I", "setRunTimeTicks", "(I)V", "profilerName", "", "getProfilerName", "()Ljava/lang/String;", "setProfilerName", "(Ljava/lang/String;)V", "LOGGER", "Lorg/apache/logging/log4j/Logger;", "getLOGGER", "()Lorg/apache/logging/log4j/Logger;", "disableFastRender", "", "sendEncryption", "networkManager", "Lnet/minecraft/network/NetworkManager;", "secretKey", "Ljavax/crypto/SecretKey;", "publicKey", "Ljava/security/PublicKey;", "encryptionRequest", "Lnet/minecraft/network/login/server/S01PacketEncryptionRequest;", "displayChatMessage", "message", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/ClientUtils.class */
public final class ClientUtils implements MinecraftInstance {

    @NotNull
    public static final ClientUtils INSTANCE = new ClientUtils();

    @Nullable
    private static Field fastRenderField;
    private static int runTimeTicks;

    @NotNull
    private static String profilerName;

    @NotNull
    private static final Logger LOGGER;

    private ClientUtils() {
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    static {
        Object m1167constructorimpl;
        ClientUtils clientUtils = INSTANCE;
        try {
            Result.Companion companion = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(GameSettings.class.getDeclaredField("ofFastRender"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(ResultKt.createFailure(th));
        }
        Object obj = m1167constructorimpl;
        fastRenderField = (Field) (Result.m1161isFailureimpl(obj) ? null : obj);
        profilerName = "";
        Logger logger = LogManager.getLogger("LiquidBounce");
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(...)");
        LOGGER = logger;
    }

    public final int getRunTimeTicks() {
        return runTimeTicks;
    }

    public final void setRunTimeTicks(int i) {
        runTimeTicks = i;
    }

    @NotNull
    public final String getProfilerName() {
        return profilerName;
    }

    public final void setProfilerName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        profilerName = str;
    }

    @NotNull
    public final Logger getLOGGER() {
        return LOGGER;
    }

    public final void disableFastRender() {
        Field it = fastRenderField;
        if (it != null) {
            if (!it.isAccessible()) {
                it.setAccessible(true);
            }
            it.setBoolean(INSTANCE.getMc().field_71474_y, false);
        }
    }

    public final void sendEncryption(@NotNull NetworkManager networkManager, @Nullable SecretKey secretKey, @Nullable PublicKey publicKey, @NotNull S01PacketEncryptionRequest encryptionRequest) {
        Intrinsics.checkNotNullParameter(networkManager, "networkManager");
        Intrinsics.checkNotNullParameter(encryptionRequest, "encryptionRequest");
        networkManager.func_179288_a(new C01PacketEncryptionResponse(secretKey, publicKey, encryptionRequest.func_149607_e()), (v2) -> {
            sendEncryption$lambda$2(r2, r3, v2);
        }, new GenericFutureListener[0]);
    }

    private static final void sendEncryption$lambda$2(NetworkManager $networkManager, SecretKey $secretKey, Future it) {
        $networkManager.func_150727_a($secretKey);
    }

    public final void displayChatMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        EntityPlayerSP entityPlayerSP = getMc().field_71439_g;
        if (entityPlayerSP == null) {
            LOGGER.info("(MCChat) " + message);
        } else {
            entityPlayerSP.func_145747_a(new ChatComponentText("§8[§9§lHazeClient§8]§r " + message));
        }
    }
}
