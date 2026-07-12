package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.Waiter;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: BufferedChannel.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/channels/WaiterEB;", "", "waiter", "Lkotlinx/coroutines/Waiter;", Constants.CTOR, "(Lkotlinx/coroutines/Waiter;)V", "toString", "", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/channels/WaiterEB.class */
final class WaiterEB {

    @JvmField
    @NotNull
    public final Waiter waiter;

    public WaiterEB(@NotNull Waiter waiter) {
        this.waiter = waiter;
    }

    @NotNull
    public String toString() {
        return "WaiterEB(" + this.waiter + ')';
    }
}
