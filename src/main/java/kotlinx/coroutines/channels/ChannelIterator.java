package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Channel.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018��*\u0006\b��\u0010\u0001 \u00012\u00020\u0002J\u000e\u0010\u0003\u001a\u00020\u0004H¦B¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00028��H\u0097@¢\u0006\u0004\b\u0007\u0010\u0005J\u000e\u0010\u0007\u001a\u00028��H¦\u0002¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/channels/ChannelIterator;", "E", "", "hasNext", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "next0", "next", "()Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/channels/ChannelIterator.class */
public interface ChannelIterator<E> {
    @Nullable
    Object hasNext(@NotNull Continuation<? super Boolean> continuation);

    @Deprecated(message = "Since 1.3.0, binary compatibility with versions <= 1.2.x", level = DeprecationLevel.HIDDEN)
    @JvmName(name = "next")
    /* synthetic */ Object next(Continuation continuation);

    E next();

    /* compiled from: Channel.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlinx/coroutines/channels/ChannelIterator$DefaultImpls.class */
    public static final class DefaultImpls {
        /* JADX WARN: Removed duplicated region for block: B:15:0x0084  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x008e  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x006e  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0095  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0050  */
        @Deprecated(message = "Since 1.3.0, binary compatibility with versions <= 1.2.x", level = DeprecationLevel.HIDDEN)
        @JvmName(name = "next")
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static /* synthetic */ Object next(ChannelIterator $this, Continuation $completion) {
            ChannelIterator$next0$1 channelIterator$next0$1;
            Object obj;
            if ($completion instanceof ChannelIterator$next0$1) {
                channelIterator$next0$1 = (ChannelIterator$next0$1) $completion;
                if ((channelIterator$next0$1.label & Integer.MIN_VALUE) != 0) {
                    channelIterator$next0$1.label -= Integer.MIN_VALUE;
                    Object $result = channelIterator$next0$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (channelIterator$next0$1.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            channelIterator$next0$1.L$0 = $this;
                            channelIterator$next0$1.label = 1;
                            obj = $this.hasNext(channelIterator$next0$1);
                            if (obj == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        case 1:
                            $this = (ChannelIterator) channelIterator$next0$1.L$0;
                            ResultKt.throwOnFailure($result);
                            obj = $result;
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    if (((Boolean) obj).booleanValue()) {
                        throw new ClosedReceiveChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE);
                    }
                    return $this.next();
                }
            }
            channelIterator$next0$1 = new ChannelIterator$next0$1($completion);
            Object $result2 = channelIterator$next0$1.result;
            Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (channelIterator$next0$1.label) {
            }
            if (((Boolean) obj).booleanValue()) {
            }
        }
    }
}
