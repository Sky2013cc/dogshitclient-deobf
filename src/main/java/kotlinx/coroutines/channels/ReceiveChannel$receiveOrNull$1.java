package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.channels.ReceiveChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Channel.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
@DebugMetadata(f = "Channel.kt", l = {772}, i = {}, s = {}, n = {}, m = "receiveOrNull", c = "kotlinx.coroutines.channels.ReceiveChannel$DefaultImpls")
/* loaded from: target.jar:kotlinx/coroutines/channels/ReceiveChannel$receiveOrNull$1.class */
public final class ReceiveChannel$receiveOrNull$1<E> extends ContinuationImpl {
    /* synthetic */ Object result;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReceiveChannel$receiveOrNull$1(Continuation<? super ReceiveChannel$receiveOrNull$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return ReceiveChannel.DefaultImpls.receiveOrNull(null, this);
    }
}
