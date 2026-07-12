package kotlinx.coroutines.test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import org.spongepowered.asm.util.Constants;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TestBuilders.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\t\u0010\u0006\u001a\u00020\u0007X\u0082\u0004R$\u0010\b\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0005¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/test/AtomicBoolean;", "", "initial", "", Constants.CTOR, "(Z)V", "container", "Lkotlinx/atomicfu/AtomicBoolean;", "value", "getValue", "()Z", "setValue", "kotlinx-coroutines-test"})
/* loaded from: target.jar:kotlinx/coroutines/test/AtomicBoolean.class */
public final class AtomicBoolean {
    private volatile /* synthetic */ int container$volatile;
    private static final /* synthetic */ AtomicIntegerFieldUpdater container$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(AtomicBoolean.class, "container$volatile");

    private final /* synthetic */ int getContainer$volatile() {
        return this.container$volatile;
    }

    private final /* synthetic */ void setContainer$volatile(int value) {
        this.container$volatile = value;
    }

    public AtomicBoolean(boolean initial) {
        this.container$volatile = initial ? 1 : 0;
    }

    public final boolean getValue() {
        return container$volatile$FU.get(this) == 1;
    }

    public final void setValue(boolean value) {
        container$volatile$FU.set(this, value ? 1 : 0);
    }
}
