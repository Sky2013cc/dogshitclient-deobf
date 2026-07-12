package kotlin.uuid;

import java.security.SecureRandom;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: UuidJVM.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/uuid/SecureRandomHolder;", "", Constants.CTOR, "()V", "instance", "Ljava/security/SecureRandom;", "getInstance", "()Ljava/security/SecureRandom;", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/uuid/SecureRandomHolder.class */
final class SecureRandomHolder {

    @NotNull
    public static final SecureRandomHolder INSTANCE = new SecureRandomHolder();

    @NotNull
    private static final SecureRandom instance = new SecureRandom();

    private SecureRandomHolder() {
    }

    @NotNull
    public final SecureRandom getInstance() {
        return instance;
    }
}
