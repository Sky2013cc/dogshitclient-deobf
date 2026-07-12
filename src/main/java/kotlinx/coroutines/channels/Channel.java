package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlinx.coroutines.DelicateCoroutinesApi;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.internal.SystemPropsKt;
import kotlinx.coroutines.selects.SelectClause1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Channel.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018�� \u0004*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u0004¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/channels/Channel;", "E", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Factory", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/channels/Channel.class */
public interface Channel<E> extends SendChannel<E>, ReceiveChannel<E> {

    @NotNull
    public static final Factory Factory = Factory.$$INSTANCE;
    public static final int UNLIMITED = Integer.MAX_VALUE;
    public static final int RENDEZVOUS = 0;
    public static final int CONFLATED = -1;
    public static final int BUFFERED = -2;
    public static final int OPTIONAL_CHANNEL = -3;

    @NotNull
    public static final String DEFAULT_BUFFER_PROPERTY_NAME = "kotlinx.coroutines.channels.defaultBuffer";

    /* compiled from: Channel.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlinx/coroutines/channels/Channel$DefaultImpls.class */
    public static final class DefaultImpls {
        @Deprecated(message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}), level = DeprecationLevel.ERROR)
        public static <E> boolean offer(@NotNull Channel<E> channel, E e) {
            return SendChannel.DefaultImpls.offer(channel, e);
        }

        @Deprecated(message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}), level = DeprecationLevel.ERROR)
        @Nullable
        public static <E> E poll(@NotNull Channel<E> channel) {
            return (E) ReceiveChannel.DefaultImpls.poll(channel);
        }

        @Deprecated(message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}), level = DeprecationLevel.ERROR)
        @LowPriorityInOverloadResolution
        @Nullable
        public static <E> Object receiveOrNull(@NotNull Channel<E> channel, @NotNull Continuation<? super E> continuation) {
            return ReceiveChannel.DefaultImpls.receiveOrNull(channel, continuation);
        }

        @NotNull
        public static <E> SelectClause1<E> getOnReceiveOrNull(@NotNull Channel<E> channel) {
            return ReceiveChannel.DefaultImpls.getOnReceiveOrNull(channel);
        }
    }

    /* compiled from: Channel.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n��R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087T¢\u0006\b\n��\u0012\u0004\b\f\u0010\u0003R\u0014\u0010\r\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/channels/Channel$Factory;", "", Constants.CTOR, "()V", "UNLIMITED", "", "RENDEZVOUS", "CONFLATED", "BUFFERED", "OPTIONAL_CHANNEL", "DEFAULT_BUFFER_PROPERTY_NAME", "", "getDEFAULT_BUFFER_PROPERTY_NAME$annotations", "CHANNEL_DEFAULT_CAPACITY", "getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core", "()I", "kotlinx-coroutines-core"})
    /* loaded from: target.jar:kotlinx/coroutines/channels/Channel$Factory.class */
    public static final class Factory {
        public static final int UNLIMITED = Integer.MAX_VALUE;
        public static final int RENDEZVOUS = 0;
        public static final int CONFLATED = -1;
        public static final int BUFFERED = -2;
        public static final int OPTIONAL_CHANNEL = -3;

        @NotNull
        public static final String DEFAULT_BUFFER_PROPERTY_NAME = "kotlinx.coroutines.channels.defaultBuffer";
        static final /* synthetic */ Factory $$INSTANCE = new Factory();
        private static final int CHANNEL_DEFAULT_CAPACITY = SystemPropsKt.systemProp("kotlinx.coroutines.channels.defaultBuffer", 64, 1, 2147483646);

        @DelicateCoroutinesApi
        public static /* synthetic */ void getDEFAULT_BUFFER_PROPERTY_NAME$annotations() {
        }

        private Factory() {
        }

        public final int getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core() {
            return CHANNEL_DEFAULT_CAPACITY;
        }
    }
}
