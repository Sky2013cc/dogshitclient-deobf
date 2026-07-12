package net.ccbluex.liquidbounce.injection.forge;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: SplashProgressLock.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0004\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lnet/ccbluex/liquidbounce/injection/forge/SplashProgressLock;", "", Constants.CTOR, "()V", "isAnimationRunning", "", "()Z", "setAnimationRunning", "(Z)V", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/injection/forge/SplashProgressLock.class */
public final class SplashProgressLock {

    @NotNull
    public static final SplashProgressLock INSTANCE = new SplashProgressLock();
    private static boolean isAnimationRunning = true;

    private SplashProgressLock() {
    }

    public final boolean isAnimationRunning() {
        return isAnimationRunning;
    }

    public final void setAnimationRunning(boolean z) {
        isAnimationRunning = z;
    }
}
