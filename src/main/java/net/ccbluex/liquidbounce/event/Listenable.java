package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Listenable.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0011\n\u0002\b\u0006\bf\u0018��2\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020��0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010��8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lnet/ccbluex/liquidbounce/event/Listenable;", "", "handleEvents", "", "subListeners", "", "getSubListeners", "()[Lnet/ccbluex/liquidbounce/event/Listenable;", "parent", "getParent", "()Lnet/ccbluex/liquidbounce/event/Listenable;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/Listenable.class */
public interface Listenable {
    boolean handleEvents();

    @NotNull
    Listenable[] getSubListeners();

    @Nullable
    Listenable getParent();

    /* compiled from: Listenable.kt */
    @Metadata(mv = {2, 0, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:net/ccbluex/liquidbounce/event/Listenable$DefaultImpls.class */
    public static final class DefaultImpls {
        public static boolean handleEvents(@NotNull Listenable $this) {
            Listenable parent = $this.getParent();
            if (parent != null) {
                return parent.handleEvents();
            }
            return true;
        }

        @NotNull
        public static Listenable[] getSubListeners(@NotNull Listenable $this) {
            return new Listenable[0];
        }

        @Nullable
        public static Listenable getParent(@NotNull Listenable $this) {
            return null;
        }
    }
}
