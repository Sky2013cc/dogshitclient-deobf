package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.selects.SelectClause2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: BroadcastChannel.kt */
@Deprecated(message = "ConflatedBroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported", level = DeprecationLevel.ERROR)
@ObsoleteCoroutinesApi
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��V\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0017\b\u0002\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\t\b\u0016¢\u0006\u0004\b\u0005\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00028��¢\u0006\u0004\b\u0005\u0010\tJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0097\u0001J\u001e\u0010\u000e\u001a\u00020\u00122\u000e\u0010\u0010\u001a\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u0014H\u0096\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0001J.\u0010\u0017\u001a\u00020\u00122#\u0010\u0018\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00120\u0019H\u0096\u0001J\u0016\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00028��H\u0097\u0001¢\u0006\u0002\u0010\u001eJ\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028��0 H\u0096\u0001J\u0016\u0010!\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00028��H\u0096A¢\u0006\u0002\u0010\"J\u001e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00120$2\u0006\u0010\u001d\u001a\u00028��H\u0096\u0001¢\u0006\u0004\b%\u0010&R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\b\u001a\u00028��8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\f\u001a\u0004\u0018\u00018��8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0014\u0010'\u001a\u00020\u000f8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b'\u0010(R$\u0010)\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0+0*X\u0096\u0005¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u0006."}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;", "E", "Lkotlinx/coroutines/channels/BroadcastChannel;", "broadcast", "Lkotlinx/coroutines/channels/BroadcastChannelImpl;", Constants.CTOR, "(Lkotlinx/coroutines/channels/BroadcastChannelImpl;)V", "()V", "value", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "valueOrNull", "getValueOrNull", "cancel", "", "cause", "", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "close", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "offer", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_ELEMENT, "(Ljava/lang/Object;)Z", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "isClosedForSend", "()Z", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/channels/ConflatedBroadcastChannel.class */
public final class ConflatedBroadcastChannel<E> implements BroadcastChannel<E> {

    @NotNull
    private final BroadcastChannelImpl<E> broadcast;

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    @NotNull
    public ReceiveChannel<E> openSubscription() {
        return this.broadcast.openSubscription();
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public void cancel(@Nullable CancellationException cause) {
        this.broadcast.cancel(cause);
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    @Deprecated(message = "Binary compatibility only", level = DeprecationLevel.HIDDEN)
    public /* synthetic */ boolean cancel(Throwable cause) {
        return this.broadcast.cancel(cause);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this.broadcast.isClosedForSend();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Nullable
    public Object send(E e, @NotNull Continuation<? super Unit> continuation) {
        return this.broadcast.send(e, continuation);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    public SelectClause2<E, SendChannel<E>> getOnSend() {
        return this.broadcast.getOnSend();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    /* renamed from: trySend-JP2dKIU */
    public Object mo2809trySendJP2dKIU(E e) {
        return this.broadcast.mo2809trySendJP2dKIU(e);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean close(@Nullable Throwable cause) {
        return this.broadcast.close(cause);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> function1) {
        this.broadcast.invokeOnClose(function1);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Deprecated(message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}), level = DeprecationLevel.ERROR)
    public boolean offer(E e) {
        return this.broadcast.offer(e);
    }

    private ConflatedBroadcastChannel(BroadcastChannelImpl<E> broadcastChannelImpl) {
        this.broadcast = broadcastChannelImpl;
    }

    public ConflatedBroadcastChannel() {
        this(new BroadcastChannelImpl(-1));
    }

    public ConflatedBroadcastChannel(E e) {
        this();
        mo2809trySendJP2dKIU(e);
    }

    public final E getValue() {
        return this.broadcast.getValue();
    }

    @Nullable
    public final E getValueOrNull() {
        return this.broadcast.getValueOrNull();
    }
}
