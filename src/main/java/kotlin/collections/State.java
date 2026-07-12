package kotlin.collections;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: AbstractIterator.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\bÂ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��¨\u0006\t"}, d2 = {"Lkotlin/collections/State;", "", Constants.CTOR, "()V", "NOT_READY", "", "READY", "DONE", "FAILED", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/collections/State.class */
final class State {

    @NotNull
    public static final State INSTANCE = new State();
    public static final int NOT_READY = 0;
    public static final int READY = 1;
    public static final int DONE = 2;
    public static final int FAILED = 3;

    private State() {
    }
}
