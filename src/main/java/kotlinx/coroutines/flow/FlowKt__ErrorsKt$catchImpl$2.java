package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Ref;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Errors.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
/* loaded from: target.jar:kotlinx/coroutines/flow/FlowKt__ErrorsKt$catchImpl$2.class */
public final class FlowKt__ErrorsKt$catchImpl$2<T> implements FlowCollector {
    final /* synthetic */ FlowCollector<T> $collector;
    final /* synthetic */ Ref.ObjectRef<Throwable> $fromDownstream;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__ErrorsKt$catchImpl$2(FlowCollector<? super T> flowCollector, Ref.ObjectRef<Throwable> objectRef) {
        this.$collector = flowCollector;
        this.$fromDownstream = objectRef;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0058  */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Throwable, T] */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t, Continuation<? super Unit> continuation) {
        FlowKt__ErrorsKt$catchImpl$2$emit$1 flowKt__ErrorsKt$catchImpl$2$emit$1;
        try {
            if (continuation instanceof FlowKt__ErrorsKt$catchImpl$2$emit$1) {
                flowKt__ErrorsKt$catchImpl$2$emit$1 = (FlowKt__ErrorsKt$catchImpl$2$emit$1) continuation;
                if ((flowKt__ErrorsKt$catchImpl$2$emit$1.label & Integer.MIN_VALUE) != 0) {
                    flowKt__ErrorsKt$catchImpl$2$emit$1.label -= Integer.MIN_VALUE;
                    Object obj = flowKt__ErrorsKt$catchImpl$2$emit$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (flowKt__ErrorsKt$catchImpl$2$emit$1.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            flowKt__ErrorsKt$catchImpl$2$emit$1.L$0 = this;
                            flowKt__ErrorsKt$catchImpl$2$emit$1.label = 1;
                            if (this.$collector.emit(t, flowKt__ErrorsKt$catchImpl$2$emit$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        case 1:
                            this = (FlowKt__ErrorsKt$catchImpl$2) flowKt__ErrorsKt$catchImpl$2$emit$1.L$0;
                            ResultKt.throwOnFailure(obj);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    return Unit.INSTANCE;
                }
            }
            switch (flowKt__ErrorsKt$catchImpl$2$emit$1.label) {
            }
            return Unit.INSTANCE;
        } catch (Throwable 
        /*  JADX ERROR: Method code generation error
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getCodeVar()" because "ssaVar" is null
            	at jadx.core.codegen.RegionGen.makeCatchBlock(RegionGen.java:367)
            	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:330)
            	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            */
        /*
            this = this;
            r0 = r8
            boolean r0 = r0 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1
            if (r0 == 0) goto L27
            r0 = r8
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1) r0
            r11 = r0
            r0 = r11
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r11
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L32
        L27:
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1
            r1 = r0
            r2 = r6
            r3 = r8
            r1.<init>(r2, r3)
            r11 = r0
        L32:
            r0 = r11
            java.lang.Object r0 = r0.result
            r10 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r12 = r0
            r0 = r11
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto L7f;
                default: goto La3;
            }
        L58:
            r0 = r10
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r6
            kotlinx.coroutines.flow.FlowCollector<T> r0 = r0.$collector     // Catch: java.lang.Throwable -> L94
            r1 = r7
            r2 = r11
            r3 = r11
            r4 = r6
            r3.L$0 = r4     // Catch: java.lang.Throwable -> L94
            r3 = r11
            r4 = 1
            r3.label = r4     // Catch: java.lang.Throwable -> L94
            java.lang.Object r0 = r0.emit(r1, r2)     // Catch: java.lang.Throwable -> L94
            r1 = r0
            r2 = r12
            if (r1 != r2) goto L90
            r1 = r12
            return r1
        L7f:
            r0 = r11
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2) r0
            r6 = r0
            r0 = r10
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L94
            r0 = r10
        L90:
            goto L9f
        L94:
            r9 = move-exception
            r0 = r6
            kotlin.jvm.internal.Ref$ObjectRef<java.lang.Throwable> r0 = r0.$fromDownstream
            r1 = r9
            r0.element = r1
            r0 = r9
            throw r0
        L9f:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        La3:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
