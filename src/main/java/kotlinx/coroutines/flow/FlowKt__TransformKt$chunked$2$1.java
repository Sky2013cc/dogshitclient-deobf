package kotlinx.coroutines.flow;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Transform.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
@SourceDebugExtension({"SMAP\nTransform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt$chunked$2$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,167:1\n1#2:168\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/flow/FlowKt__TransformKt$chunked$2$1.class */
public final class FlowKt__TransformKt$chunked$2$1<T> implements FlowCollector {
    final /* synthetic */ Ref.ObjectRef<ArrayList<T>> $result;
    final /* synthetic */ int $size;
    final /* synthetic */ FlowCollector<List<? extends T>> $this_flow;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__TransformKt$chunked$2$1(Ref.ObjectRef<ArrayList<T>> objectRef, int $size, FlowCollector<? super List<? extends T>> flowCollector) {
        this.$result = objectRef;
        this.$size = $size;
        this.$this_flow = flowCollector;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0043. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0058  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t, Continuation<? super Unit> continuation) {
        FlowKt__TransformKt$chunked$2$1$emit$1 flowKt__TransformKt$chunked$2$1$emit$1;
        if (continuation instanceof FlowKt__TransformKt$chunked$2$1$emit$1) {
            flowKt__TransformKt$chunked$2$1$emit$1 = (FlowKt__TransformKt$chunked$2$1$emit$1) continuation;
            if ((flowKt__TransformKt$chunked$2$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                flowKt__TransformKt$chunked$2$1$emit$1.label -= Integer.MIN_VALUE;
                Object obj = flowKt__TransformKt$chunked$2$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (flowKt__TransformKt$chunked$2$1$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        ArrayList<T> arrayList = this.$result.element;
                        if (arrayList == null) {
                            T t2 = (T) new ArrayList(this.$size);
                            this.$result.element = t2;
                            arrayList = t2;
                        }
                        ArrayList<T> arrayList2 = arrayList;
                        arrayList2.add(t);
                        if (arrayList2.size() == this.$size) {
                            flowKt__TransformKt$chunked$2$1$emit$1.L$0 = this;
                            flowKt__TransformKt$chunked$2$1$emit$1.label = 1;
                            if (this.$this_flow.emit(arrayList2, flowKt__TransformKt$chunked$2$1$emit$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            this.$result.element = null;
                        }
                        return Unit.INSTANCE;
                    case 1:
                        this = (FlowKt__TransformKt$chunked$2$1) flowKt__TransformKt$chunked$2$1$emit$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        this.$result.element = null;
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        flowKt__TransformKt$chunked$2$1$emit$1 = new FlowKt__TransformKt$chunked$2$1$emit$1(this, continuation);
        Object obj2 = flowKt__TransformKt$chunked$2$1$emit$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (flowKt__TransformKt$chunked$2$1$emit$1.label) {
        }
    }
}
