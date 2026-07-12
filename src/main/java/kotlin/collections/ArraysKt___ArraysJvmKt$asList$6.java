package kotlin.collections;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.util.RandomAccess;
import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

/* compiled from: _ArraysJvm.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��'\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\u0011\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0002H\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$6", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "isEmpty", "", "contains", Constants.ATTR_ELEMENT, PropertyDescriptor.GET, "index", "(I)Ljava/lang/Double;", "indexOf", "lastIndexOf", "kotlin-stdlib"})
@SourceDebugExtension({"SMAP\n_ArraysJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _ArraysJvm.kt\nkotlin/collections/ArraysKt___ArraysJvmKt$asList$6\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,3105:1\n12627#2,2:3106\n1754#2,6:3108\n1862#2,6:3114\n*S KotlinDebug\n*F\n+ 1 _ArraysJvm.kt\nkotlin/collections/ArraysKt___ArraysJvmKt$asList$6\n*L\n213#1:3106,2\n215#1:3108,6\n216#1:3114,6\n*E\n"})
/* loaded from: target.jar:kotlin/collections/ArraysKt___ArraysJvmKt$asList$6.class */
public final class ArraysKt___ArraysJvmKt$asList$6 extends AbstractList<Double> implements RandomAccess {
    final /* synthetic */ double[] $this_asList;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArraysKt___ArraysJvmKt$asList$6(double[] $receiver) {
        this.$this_asList = $receiver;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object element) {
        if (element instanceof Double) {
            return contains(((Number) element).doubleValue());
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object element) {
        if (element instanceof Double) {
            return indexOf(((Number) element).doubleValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object element) {
        if (element instanceof Double) {
            return lastIndexOf(((Number) element).doubleValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.$this_asList.length;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.$this_asList.length == 0;
    }

    public boolean contains(double element) {
        double[] $this$any$iv = this.$this_asList;
        for (double element$iv : $this$any$iv) {
            if (Double.doubleToLongBits(element$iv) == Double.doubleToLongBits(element)) {
                return true;
            }
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public Double get(int index) {
        return Double.valueOf(this.$this_asList[index]);
    }

    public int indexOf(double element) {
        double[] $this$indexOfFirst$iv = this.$this_asList;
        int length = $this$indexOfFirst$iv.length;
        for (int index$iv = 0; index$iv < length; index$iv++) {
            double it = $this$indexOfFirst$iv[index$iv];
            if (Double.doubleToLongBits(it) == Double.doubleToLongBits(element)) {
                return index$iv;
            }
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:?, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0011, code lost:
    
        if (0 <= r10) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r0 = r10;
        r10 = r10 - 1;
        r0 = r0[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x002e, code lost:
    
        if (java.lang.Double.doubleToLongBits(r0) != java.lang.Double.doubleToLongBits(r6)) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0031, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0036, code lost:
    
        if (r0 == false) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0041, code lost:
    
        if (0 <= r10) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int lastIndexOf(double element) {
        double[] $this$indexOfLast$iv = this.$this_asList;
        int length = $this$indexOfLast$iv.length - 1;
    }
}
