package kotlinx.coroutines.stream;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Stream.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
@DebugMetadata(f = "Stream.kt", l = {22}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"this", "collector"}, m = "collect", c = "kotlinx.coroutines.stream.StreamFlow")
/* loaded from: target.jar:kotlinx/coroutines/stream/StreamFlow$collect$1.class */
public final class StreamFlow$collect$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    /* synthetic */ Object result;
    final /* synthetic */ StreamFlow<T> this$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamFlow$collect$1(StreamFlow<T> streamFlow, Continuation<? super StreamFlow$collect$1> continuation) {
        super(continuation);
        this.this$0 = streamFlow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.collect(null, this);
    }
}
