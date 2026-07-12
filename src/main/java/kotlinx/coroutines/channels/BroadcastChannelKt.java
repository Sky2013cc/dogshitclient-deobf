package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

/* compiled from: BroadcastChannel.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\u001a\u001c\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0007"}, d2 = {"BroadcastChannel", "Lkotlinx/coroutines/channels/BroadcastChannel;", "E", "capacity", "", "NO_ELEMENT", "Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/channels/BroadcastChannelKt.class */
public final class BroadcastChannelKt {

    @NotNull
    private static final Symbol NO_ELEMENT = new Symbol("NO_ELEMENT");

    public static final /* synthetic */ Symbol access$getNO_ELEMENT$p() {
        return NO_ELEMENT;
    }

    @Deprecated(message = "BroadcastChannel is deprecated in the favour of SharedFlow and StateFlow, and is no longer supported", level = DeprecationLevel.ERROR)
    @ObsoleteCoroutinesApi
    @NotNull
    public static final <E> BroadcastChannel<E> BroadcastChannel(int capacity) {
        switch (capacity) {
            case -2:
                return new BroadcastChannelImpl(Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core());
            case -1:
                return new ConflatedBroadcastChannel();
            case 0:
                throw new IllegalArgumentException("Unsupported 0 capacity for BroadcastChannel");
            case Integer.MAX_VALUE:
                throw new IllegalArgumentException("Unsupported UNLIMITED capacity for BroadcastChannel");
            default:
                return new BroadcastChannelImpl(capacity);
        }
    }
}
