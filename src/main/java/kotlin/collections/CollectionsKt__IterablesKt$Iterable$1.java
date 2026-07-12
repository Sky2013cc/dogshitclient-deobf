package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: Iterables.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 176, d1 = {"��\u0011\n��\n\u0002\u0010\u001c\n��\n\u0002\u0010(\n��*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00028��0\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028��0\u0003H\u0096\u0002¨\u0006\u0004"}, d2 = {"kotlin/collections/CollectionsKt__IterablesKt$Iterable$1", "", "iterator", "", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/collections/CollectionsKt__IterablesKt$Iterable$1.class */
public final class CollectionsKt__IterablesKt$Iterable$1<T> implements Iterable<T>, KMappedMarker {
    final /* synthetic */ Function0<Iterator<T>> $iterator;

    /* JADX WARN: Multi-variable type inference failed */
    public CollectionsKt__IterablesKt$Iterable$1(Function0<? extends Iterator<? extends T>> function0) {
        this.$iterator = function0;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.$iterator.invoke();
    }
}
