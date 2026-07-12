package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Share.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
@DebugMetadata(f = "Share.kt", l = {408}, i = {}, s = {}, n = {}, m = "collect", c = "kotlinx.coroutines.flow.SubscribedSharedFlow")
/* loaded from: target.jar:kotlinx/coroutines/flow/SubscribedSharedFlow$collect$1.class */
public final class SubscribedSharedFlow$collect$1 extends ContinuationImpl {
    /* synthetic */ Object result;
    final /* synthetic */ SubscribedSharedFlow<T> this$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubscribedSharedFlow$collect$1(SubscribedSharedFlow<T> subscribedSharedFlow, Continuation<? super SubscribedSharedFlow$collect$1> continuation) {
        super(continuation);
        this.this$0 = subscribedSharedFlow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.collect(null, this);
    }
}
