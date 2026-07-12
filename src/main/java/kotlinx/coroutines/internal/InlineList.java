package kotlinx.coroutines.internal;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.DebugKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: InlineList.kt */
@JvmInline
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\b\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0081@\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010\u0007\u001a\u00028��H\u0086\u0002¢\u0006\u0004\b\b\u0010\tJ$\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\u000b0\rH\u0086\b¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0002X\u0082\u0004¢\u0006\u0002\n��\u0088\u0001\u0003\u0092\u0001\u0004\u0018\u00010\u0002¨\u0006\u0017"}, d2 = {"Lkotlinx/coroutines/internal/InlineList;", "E", "", "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "plus", Constants.ATTR_ELEMENT, "plus-FjFbRPM", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "forEachReversed", "", "action", "Lkotlin/Function1;", "forEachReversed-impl", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "equals", "", "other", "hashCode", "", "toString", "", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nInlineList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InlineList.kt\nkotlinx/coroutines/internal/InlineList\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,45:1\n1#2:46\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/internal/InlineList.class */
public final class InlineList<E> {

    @Nullable
    private final Object holder;

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m2916toStringimpl(Object arg0) {
        return "InlineList(holder=" + arg0 + ')';
    }

    public String toString() {
        return m2916toStringimpl(this.holder);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m2917hashCodeimpl(Object arg0) {
        if (arg0 == null) {
            return 0;
        }
        return arg0.hashCode();
    }

    public int hashCode() {
        return m2917hashCodeimpl(this.holder);
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m2918equalsimpl(Object arg0, Object other) {
        return (other instanceof InlineList) && Intrinsics.areEqual(arg0, ((InlineList) other).m2922unboximpl());
    }

    public boolean equals(Object other) {
        return m2918equalsimpl(this.holder, other);
    }

    @NotNull
    /* renamed from: constructor-impl, reason: not valid java name */
    public static <E> Object m2919constructorimpl(@Nullable Object holder) {
        return holder;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ InlineList m2921boximpl(Object v) {
        return new InlineList(v);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m2922unboximpl() {
        return this.holder;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2923equalsimpl0(Object p1, Object p2) {
        return Intrinsics.areEqual(p1, p2);
    }

    private /* synthetic */ InlineList(Object holder) {
        this.holder = holder;
    }

    /* renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ Object m2920constructorimpl$default(Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            obj = null;
        }
        return m2919constructorimpl(obj);
    }

    @NotNull
    /* renamed from: plus-FjFbRPM, reason: not valid java name */
    public static final Object m2914plusFjFbRPM(Object arg0, E e) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(!(e instanceof List))) {
                throw new AssertionError();
            }
        }
        if (arg0 == null) {
            return m2919constructorimpl(e);
        }
        if (arg0 instanceof ArrayList) {
            Intrinsics.checkNotNull(arg0, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>");
            ((ArrayList) arg0).add(e);
            return m2919constructorimpl(arg0);
        }
        ArrayList list = new ArrayList(4);
        list.add(arg0);
        list.add(e);
        return m2919constructorimpl(list);
    }

    /* renamed from: forEachReversed-impl, reason: not valid java name */
    public static final void m2915forEachReversedimpl(Object obj, @NotNull Function1<? super E, Unit> function1) {
        if (obj == null) {
            return;
        }
        if (obj instanceof ArrayList) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>");
            ArrayList arrayList = (ArrayList) obj;
            for (int size = arrayList.size() - 1; -1 < size; size--) {
                function1.invoke((Object) arrayList.get(size));
            }
            return;
        }
        function1.invoke(obj);
    }
}
