package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: JobSupport.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/IncompleteStateBox;", "", "state", "Lkotlinx/coroutines/Incomplete;", Constants.CTOR, "(Lkotlinx/coroutines/Incomplete;)V", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/IncompleteStateBox.class */
final class IncompleteStateBox {

    @JvmField
    @NotNull
    public final Incomplete state;

    public IncompleteStateBox(@NotNull Incomplete state) {
        this.state = state;
    }
}
