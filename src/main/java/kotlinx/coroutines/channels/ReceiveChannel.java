package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DelicateCoroutinesApi;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.selects.SelectClause1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Channel.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0003\n\u0002\b\u0006\bf\u0018��*\u0006\b��\u0010\u0001 \u00012\u00020\u0002J\u000e\u0010\n\u001a\u00028��H¦@¢\u0006\u0002\u0010\u000bJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028��0\u0011H¦@¢\u0006\u0004\b\u0012\u0010\u000bJ\u0015\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028��0\u0011H&¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028��0\u0019H¦\u0002J\u001f\u0010\u001a\u001a\u00020\u001b2\u0010\b\u0002\u0010\u001c\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001eH&¢\u0006\u0002\u0010\u001fJ\b\u0010\u001a\u001a\u00020\u001bH\u0017J\u0014\u0010\u001a\u001a\u00020\u00042\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010 H'J\u000f\u0010!\u001a\u0004\u0018\u00018��H\u0017¢\u0006\u0002\u0010\u0017J\u0010\u0010\"\u001a\u0004\u0018\u00018��H\u0097@¢\u0006\u0002\u0010\u000bR\u001a\u0010\u0003\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0007R\u001a\u0010\b\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0006\u001a\u0004\b\b\u0010\u0007R\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00028��0\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00110\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000fR\"\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018��0\r8VX\u0097\u0004¢\u0006\f\u0012\u0004\b$\u0010\u0006\u001a\u0004\b%\u0010\u000f¨\u0006&"}, d2 = {"Lkotlinx/coroutines/channels/ReceiveChannel;", "E", "", "isClosedForReceive", "", "isClosedForReceive$annotations", "()V", "()Z", "isEmpty", "isEmpty$annotations", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "receiveCatching", "Lkotlinx/coroutines/channels/ChannelResult;", "receiveCatching-JP2dKIU", "onReceiveCatching", "getOnReceiveCatching", "tryReceive", "tryReceive-PtdJZtk", "()Ljava/lang/Object;", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "cancel", "", "cause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "", "poll", "receiveOrNull", "onReceiveOrNull", "getOnReceiveOrNull$annotations", "getOnReceiveOrNull", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/channels/ReceiveChannel.class */
public interface ReceiveChannel<E> {
    boolean isClosedForReceive();

    boolean isEmpty();

    @Nullable
    Object receive(@NotNull Continuation<? super E> continuation);

    @NotNull
    SelectClause1<E> getOnReceive();

    @Nullable
    /* renamed from: receiveCatching-JP2dKIU */
    Object mo2813receiveCatchingJP2dKIU(@NotNull Continuation<? super ChannelResult<? extends E>> continuation);

    @NotNull
    SelectClause1<ChannelResult<E>> getOnReceiveCatching();

    @NotNull
    /* renamed from: tryReceive-PtdJZtk */
    Object mo2816tryReceivePtdJZtk();

    @NotNull
    ChannelIterator<E> iterator();

    void cancel(@Nullable CancellationException cancellationException);

    @Deprecated(message = "Since 1.2.0, binary compatibility with versions <= 1.1.x", level = DeprecationLevel.HIDDEN)
    /* synthetic */ void cancel();

    @Deprecated(message = "Since 1.2.0, binary compatibility with versions <= 1.1.x", level = DeprecationLevel.HIDDEN)
    /* synthetic */ boolean cancel(Throwable th);

    @Deprecated(message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}), level = DeprecationLevel.ERROR)
    @Nullable
    E poll();

    @Deprecated(message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}), level = DeprecationLevel.ERROR)
    @LowPriorityInOverloadResolution
    @Nullable
    Object receiveOrNull(@NotNull Continuation<? super E> continuation);

    @NotNull
    SelectClause1<E> getOnReceiveOrNull();

    /* compiled from: Channel.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlinx/coroutines/channels/ReceiveChannel$DefaultImpls.class */
    public static final class DefaultImpls {
        @DelicateCoroutinesApi
        public static /* synthetic */ void isClosedForReceive$annotations() {
        }

        @ExperimentalCoroutinesApi
        public static /* synthetic */ void isEmpty$annotations() {
        }

        @Deprecated(message = "Deprecated in favor of onReceiveCatching extension", replaceWith = @ReplaceWith(expression = "onReceiveCatching", imports = {}), level = DeprecationLevel.ERROR)
        public static /* synthetic */ void getOnReceiveOrNull$annotations() {
        }

        public static /* synthetic */ void cancel$default(ReceiveChannel receiveChannel, CancellationException cancellationException, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if ((i & 1) != 0) {
                cancellationException = null;
            }
            receiveChannel.cancel(cancellationException);
        }

        public static /* synthetic */ boolean cancel$default(ReceiveChannel receiveChannel, Throwable th, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if ((i & 1) != 0) {
                th = null;
            }
            return receiveChannel.cancel(th);
        }

        @Deprecated(message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}), level = DeprecationLevel.ERROR)
        @Nullable
        public static <E> E poll(@NotNull ReceiveChannel<? extends E> receiveChannel) {
            Object mo2816tryReceivePtdJZtk = receiveChannel.mo2816tryReceivePtdJZtk();
            if (ChannelResult.m2840isSuccessimpl(mo2816tryReceivePtdJZtk)) {
                return (E) ChannelResult.m2844getOrThrowimpl(mo2816tryReceivePtdJZtk);
            }
            Throwable m2845exceptionOrNullimpl = ChannelResult.m2845exceptionOrNullimpl(mo2816tryReceivePtdJZtk);
            if (m2845exceptionOrNullimpl == null) {
                return null;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(m2845exceptionOrNullimpl);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0069  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0078  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0050  */
        @Deprecated(message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}), level = DeprecationLevel.ERROR)
        @LowPriorityInOverloadResolution
        @Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static <E> Object receiveOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
            ReceiveChannel$receiveOrNull$1 receiveChannel$receiveOrNull$1;
            Object m2851unboximpl;
            if (continuation instanceof ReceiveChannel$receiveOrNull$1) {
                receiveChannel$receiveOrNull$1 = (ReceiveChannel$receiveOrNull$1) continuation;
                if ((receiveChannel$receiveOrNull$1.label & Integer.MIN_VALUE) != 0) {
                    receiveChannel$receiveOrNull$1.label -= Integer.MIN_VALUE;
                    Object $result = receiveChannel$receiveOrNull$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (receiveChannel$receiveOrNull$1.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            receiveChannel$receiveOrNull$1.label = 1;
                            m2851unboximpl = receiveChannel.mo2813receiveCatchingJP2dKIU(receiveChannel$receiveOrNull$1);
                            if (m2851unboximpl == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        case 1:
                            ResultKt.throwOnFailure($result);
                            m2851unboximpl = ((ChannelResult) $result).m2851unboximpl();
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    return ChannelResult.m2843getOrNullimpl(m2851unboximpl);
                }
            }
            receiveChannel$receiveOrNull$1 = new ReceiveChannel$receiveOrNull$1(continuation);
            Object $result2 = receiveChannel$receiveOrNull$1.result;
            Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (receiveChannel$receiveOrNull$1.label) {
            }
            return ChannelResult.m2843getOrNullimpl(m2851unboximpl);
        }

        @NotNull
        public static <E> SelectClause1<E> getOnReceiveOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel) {
            Intrinsics.checkNotNull(receiveChannel, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel<E of kotlinx.coroutines.channels.ReceiveChannel>");
            return ((BufferedChannel) receiveChannel).getOnReceiveOrNull();
        }
    }
}
