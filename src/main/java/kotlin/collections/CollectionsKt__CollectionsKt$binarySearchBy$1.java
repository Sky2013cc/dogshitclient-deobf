package kotlin.collections;

import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Incorrect field signature: TK; */
/* compiled from: Collections.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 176)
/* loaded from: target.jar:kotlin/collections/CollectionsKt__CollectionsKt$binarySearchBy$1.class */
public final class CollectionsKt__CollectionsKt$binarySearchBy$1<T> implements Function1<T, Integer> {
    final /* synthetic */ Function1<T, K> $selector;
    final /* synthetic */ Comparable $key;

    /* JADX WARN: Incorrect types in method signature: (Lkotlin/jvm/functions/Function1<-TT;+TK;>;TK;)V */
    public CollectionsKt__CollectionsKt$binarySearchBy$1(Function1 $selector, Comparable $key) {
        this.$selector = $selector;
        this.$key = $key;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function1
    public final Integer invoke(T t) {
        return Integer.valueOf(ComparisonsKt.compareValues((Comparable) this.$selector.invoke(t), this.$key));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Integer invoke(Object obj) {
        return invoke((CollectionsKt__CollectionsKt$binarySearchBy$1<T>) obj);
    }
}
