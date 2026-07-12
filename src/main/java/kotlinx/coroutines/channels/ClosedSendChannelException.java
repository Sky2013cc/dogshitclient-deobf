package kotlinx.coroutines.channels;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Channel.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018��2\u00060\u0001j\u0002`\u0002B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/channels/ClosedSendChannelException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "message", "", Constants.CTOR, "(Ljava/lang/String;)V", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/channels/ClosedSendChannelException.class */
public final class ClosedSendChannelException extends IllegalStateException {
    public ClosedSendChannelException(@Nullable String message) {
        super(message);
    }
}
