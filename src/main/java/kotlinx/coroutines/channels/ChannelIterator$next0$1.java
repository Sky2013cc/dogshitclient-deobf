package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.channels.ChannelIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Channel.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
@DebugMetadata(f = "Channel.kt", l = {1094}, i = {0}, s = {"L$0"}, n = {"$this"}, m = "next", c = "kotlinx.coroutines.channels.ChannelIterator$DefaultImpls")
/* loaded from: target.jar:kotlinx/coroutines/channels/ChannelIterator$next0$1.class */
public final class ChannelIterator$next0$1<E> extends ContinuationImpl {
    Object L$0;
    /* synthetic */ Object result;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelIterator$next0$1(Continuation<? super ChannelIterator$next0$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return ChannelIterator.DefaultImpls.next(null, this);
    }
}
