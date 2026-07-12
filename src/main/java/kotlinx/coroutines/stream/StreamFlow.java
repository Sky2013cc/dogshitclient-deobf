package kotlinx.coroutines.stream;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Stream.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028��0\fH\u0096@¢\u0006\u0002\u0010\rR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004X\u0082\u0004¢\u0006\u0002\n��R\t\u0010\u0007\u001a\u00020\bX\u0082\u0004¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/stream/StreamFlow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/flow/Flow;", "stream", "Ljava/util/stream/Stream;", Constants.CTOR, "(Ljava/util/stream/Stream;)V", "consumed", "Lkotlinx/atomicfu/AtomicBoolean;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/stream/StreamFlow.class */
public final class StreamFlow<T> implements Flow<T> {

    @NotNull
    private final Stream<T> stream;
    private volatile /* synthetic */ int consumed$volatile;
    private static final /* synthetic */ AtomicIntegerFieldUpdater consumed$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(StreamFlow.class, "consumed$volatile");

    private final /* synthetic */ int getConsumed$volatile() {
        return this.consumed$volatile;
    }

    private final /* synthetic */ void setConsumed$volatile(int value) {
        this.consumed$volatile = value;
    }

    public StreamFlow(@NotNull Stream<T> stream) {
        this.stream = stream;
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008a A[Catch: all -> 0x00f0, TRY_LEAVE, TryCatch #0 {all -> 0x00f0, blocks: (B:14:0x0076, B:16:0x0081, B:18:0x008a, B:32:0x00d8), top: B:7:0x0043 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0058  */
    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        StreamFlow$collect$1 streamFlow$collect$1;
        Iterator<T> it;
        try {
            if (continuation instanceof StreamFlow$collect$1) {
                streamFlow$collect$1 = (StreamFlow$collect$1) continuation;
                if ((streamFlow$collect$1.label & Integer.MIN_VALUE) != 0) {
                    streamFlow$collect$1.label -= Integer.MIN_VALUE;
                    Object $result = streamFlow$collect$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (streamFlow$collect$1.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            if (!consumed$volatile$FU.compareAndSet(this, 0, 1)) {
                                throw new IllegalStateException("Stream.consumeAsFlow can be collected only once".toString());
                            }
                            it = this.stream.iterator();
                            break;
                        case 1:
                            it = (Iterator) streamFlow$collect$1.L$2;
                            flowCollector = (FlowCollector) streamFlow$collect$1.L$1;
                            this = (StreamFlow) streamFlow$collect$1.L$0;
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    while (it.hasNext()) {
                        Object value = it.next();
                        streamFlow$collect$1.L$0 = this;
                        streamFlow$collect$1.L$1 = flowCollector;
                        streamFlow$collect$1.L$2 = it;
                        streamFlow$collect$1.label = 1;
                        if (flowCollector.emit(value, streamFlow$collect$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    this.stream.close();
                    return Unit.INSTANCE;
                }
            }
            switch (streamFlow$collect$1.label) {
            }
            while (it.hasNext()) {
            }
            this.stream.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            this.stream.close();
            throw th;
        }
        streamFlow$collect$1 = new StreamFlow$collect$1(this, continuation);
        Object $result2 = streamFlow$collect$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }
}
