package kotlin.collections.builders;

import com.sun.xml.internal.xsom.XSFacet;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import sun.tools.java.RuntimeConstants;

/* compiled from: ListBuilder.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��8\n��\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000b\n��\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\u001a!\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H��¢\u0006\u0002\u0010\u0005\u001a=\u0010\u0006\u001a\u00020\u0007\"\u0004\b��\u0010\b*\n\u0012\u0006\b\u0001\u0012\u0002H\b0\u00012\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\b0\fH\u0002¢\u0006\u0002\u0010\r\u001a-\u0010\u000e\u001a\u00020\u0004\"\u0004\b��\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00012\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u000f\u001a9\u0010\u0010\u001a\u00020\u0011\"\u0004\b��\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00012\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0002¢\u0006\u0002\u0010\u0014\u001a+\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\b0\u0001\"\u0004\b��\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00012\u0006\u0010\u0016\u001a\u00020\u0004H��¢\u0006\u0002\u0010\u0017\u001a%\u0010\u0018\u001a\u00020\u0019\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0004H��¢\u0006\u0002\u0010\u001b\u001a-\u0010\u001c\u001a\u00020\u0019\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004H��¢\u0006\u0002\u0010\u001f¨\u0006 "}, d2 = {"arrayOfUninitializedElements", "", "E", "size", "", "(I)[Ljava/lang/Object;", "subarrayContentToString", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "offset", XSFacet.FACET_LENGTH, "thisCollection", "", "([Ljava/lang/Object;IILjava/util/Collection;)Ljava/lang/String;", "subarrayContentHashCode", "([Ljava/lang/Object;II)I", "subarrayContentEquals", "", "other", "", "([Ljava/lang/Object;IILjava/util/List;)Z", "copyOfUninitializedElements", "newSize", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "resetAt", "", "index", "([Ljava/lang/Object;I)V", "resetRange", "fromIndex", "toIndex", "([Ljava/lang/Object;II)V", "kotlin-stdlib"})
@SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilderKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,718:1\n1#2:719\n*E\n"})
/* loaded from: target.jar:kotlin/collections/builders/ListBuilderKt.class */
public final class ListBuilderKt {
    @NotNull
    public static final <E> E[] arrayOfUninitializedElements(int i) {
        if (!(i >= 0)) {
            throw new IllegalArgumentException("capacity must be non-negative.".toString());
        }
        return (E[]) new Object[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> String subarrayContentToString(T[] tArr, int offset, int length, Collection<? extends T> collection) {
        StringBuilder sb = new StringBuilder(2 + (length * 3));
        sb.append(RuntimeConstants.SIG_ARRAY);
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            T t = tArr[offset + i];
            if (t == collection) {
                sb.append("(this Collection)");
            } else {
                sb.append(t);
            }
        }
        sb.append("]");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> int subarrayContentHashCode(T[] tArr, int offset, int length) {
        int result = 1;
        for (int i = 0; i < length; i++) {
            T t = tArr[offset + i];
            result = (result * 31) + (t != null ? t.hashCode() : 0);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> boolean subarrayContentEquals(T[] tArr, int offset, int length, List<?> list) {
        if (length != list.size()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!Intrinsics.areEqual(tArr[offset + i], list.get(i))) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static final <T> T[] copyOfUninitializedElements(@NotNull T[] tArr, int i) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, i);
        Intrinsics.checkNotNullExpressionValue(tArr2, "copyOf(...)");
        return tArr2;
    }

    public static final <E> void resetAt(@NotNull E[] eArr, int index) {
        Intrinsics.checkNotNullParameter(eArr, "<this>");
        eArr[index] = null;
    }

    public static final <E> void resetRange(@NotNull E[] eArr, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(eArr, "<this>");
        for (int index = fromIndex; index < toIndex; index++) {
            resetAt(eArr, index);
        }
    }
}
