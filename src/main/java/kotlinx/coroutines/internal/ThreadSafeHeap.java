package kotlinx.coroutines.internal;

import com.sun.tools.internal.ws.wsdl.document.jaxws.JAXWSBindingsConstants;
import java.lang.Comparable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ThreadSafeHeap.kt */
@InternalCoroutinesApi
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0014\b\u0017\u0018��*\u0012\b��\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00060\u0004j\u0002`\u0005B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J0\u0010\u0017\u001a\u0004\u0018\u00018��2!\u0010\u0018\u001a\u001d\u0012\u0013\u0012\u00118��¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00150\u0019¢\u0006\u0002\u0010\u001cJ\r\u0010\u001d\u001a\u0004\u0018\u00018��¢\u0006\u0002\u0010\u001eJ\r\u0010\u001f\u001a\u0004\u0018\u00018��¢\u0006\u0002\u0010\u001eJ$\u0010 \u001a\u0004\u0018\u00018��2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\u00150\u0019H\u0086\b¢\u0006\u0002\u0010\u001cJ\u0013\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00028��¢\u0006\u0002\u0010$J,\u0010%\u001a\u00020\u00152\u0006\u0010#\u001a\u00028��2\u0014\u0010&\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00018��\u0012\u0004\u0012\u00020\u00150\u0019H\u0086\b¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020\u00152\u0006\u0010#\u001a\u00028��¢\u0006\u0002\u0010)J\u000f\u0010*\u001a\u0004\u0018\u00018��H\u0001¢\u0006\u0002\u0010\u001eJ\u0015\u0010+\u001a\u00028��2\u0006\u0010,\u001a\u00020\u000eH\u0001¢\u0006\u0002\u0010-J\u0015\u0010.\u001a\u00020\"2\u0006\u0010#\u001a\u00028��H\u0001¢\u0006\u0002\u0010$J\u0011\u0010/\u001a\u00020\"2\u0006\u00100\u001a\u00020\u000eH\u0082\u0010J\u0011\u00101\u001a\u00020\"2\u0006\u00100\u001a\u00020\u000eH\u0082\u0010J\u0015\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018��0\tH\u0002¢\u0006\u0002\u00103J\u0018\u00104\u001a\u00020\"2\u0006\u00100\u001a\u00020\u000e2\u0006\u00105\u001a\u00020\u000eH\u0002R\u001a\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018��\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\nR\t\u0010\u000b\u001a\u00020\fX\u0082\u0004R$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@BX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016¨\u00066"}, d2 = {"Lkotlinx/coroutines/internal/ThreadSafeHeap;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", Constants.CTOR, "()V", "a", "", "[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "_size", "Lkotlinx/atomicfu/AtomicInt;", "value", "", "size", "getSize", "()I", "setSize", "(I)V", "isEmpty", "", "()Z", "find", "predicate", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "peek", "()Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "removeFirstOrNull", "removeFirstIf", "addLast", "", JAXWSBindingsConstants.NODE_ATTR, "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)V", "addLastIf", "cond", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;Lkotlin/jvm/functions/Function1;)Z", "remove", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)Z", "firstImpl", "removeAtImpl", "index", "(I)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "addImpl", "siftUpFrom", OperatorName.SET_FLATNESS, "siftDownFrom", "realloc", "()[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "swap", OperatorName.SET_LINE_JOINSTYLE, "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nThreadSafeHeap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ThreadSafeHeap.kt\nkotlinx/coroutines/internal/ThreadSafeHeap\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,159:1\n28#2:160\n28#2:162\n28#2:164\n28#2:166\n28#2:168\n28#2:170\n28#2:172\n16#3:161\n16#3:163\n16#3:165\n16#3:167\n16#3:169\n16#3:171\n16#3:173\n1#4:174\n*S KotlinDebug\n*F\n+ 1 ThreadSafeHeap.kt\nkotlinx/coroutines/internal/ThreadSafeHeap\n*L\n33#1:160\n41#1:162\n43#1:164\n51#1:166\n60#1:168\n63#1:170\n72#1:172\n33#1:161\n41#1:163\n43#1:165\n51#1:167\n60#1:169\n63#1:171\n72#1:173\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/internal/ThreadSafeHeap.class */
public class ThreadSafeHeap<T extends ThreadSafeHeapNode & Comparable<? super T>> {

    @Nullable
    private T[] a;
    private volatile /* synthetic */ int _size$volatile;
    private static final /* synthetic */ AtomicIntegerFieldUpdater _size$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(ThreadSafeHeap.class, "_size$volatile");

    private final /* synthetic */ int get_size$volatile() {
        return this._size$volatile;
    }

    private final /* synthetic */ void set_size$volatile(int value) {
        this._size$volatile = value;
    }

    public final int getSize() {
        return _size$volatile$FU.get(this);
    }

    private final void setSize(int value) {
        _size$volatile$FU.set(this, value);
    }

    public final boolean isEmpty() {
        return getSize() == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.Object] */
    @Nullable
    public final T find(@NotNull Function1<? super T, Boolean> function1) {
        T t;
        T t2;
        synchronized (this) {
            int i = 0;
            int size = getSize();
            while (true) {
                if (i < size) {
                    T[] tArr = this.a;
                    T t3 = tArr != null ? tArr[i] : null;
                    Intrinsics.checkNotNull(t3);
                    ?? r11 = (Object) t3;
                    if (function1.invoke(r11).booleanValue()) {
                        t = r11;
                        break;
                    }
                    i++;
                } else {
                    t = null;
                    break;
                }
            }
            t2 = t;
        }
        return t2;
    }

    @Nullable
    public final T peek() {
        T firstImpl;
        synchronized (this) {
            firstImpl = firstImpl();
        }
        return firstImpl;
    }

    @Nullable
    public final T removeFirstOrNull() {
        T t;
        T t2;
        synchronized (this) {
            if (getSize() > 0) {
                t = removeAtImpl(0);
            } else {
                t = null;
            }
            t2 = t;
        }
        return t2;
    }

    @Nullable
    public final T removeFirstIf(@NotNull Function1<? super T, Boolean> function1) {
        T t;
        synchronized (this) {
            try {
                ThreadSafeHeapNode first = firstImpl();
                if (first == null) {
                    InlineMarker.finallyStart(2);
                    InlineMarker.finallyEnd(2);
                    return null;
                }
                if (function1.invoke(first).booleanValue()) {
                    t = removeAtImpl(0);
                } else {
                    t = null;
                }
                T t2 = t;
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                return t2;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
    }

    public final void addLast(@NotNull T t) {
        synchronized (this) {
            addImpl(t);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean addLastIf(@NotNull T t, @NotNull Function1<? super T, Boolean> function1) {
        boolean z;
        boolean z2;
        synchronized (this) {
            try {
                if (function1.invoke(firstImpl()).booleanValue()) {
                    addImpl(t);
                    z = true;
                } else {
                    z = false;
                }
                z2 = z;
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        return z2;
    }

    public final boolean remove(@NotNull T t) {
        boolean z;
        boolean z2;
        synchronized (this) {
            if (t.getHeap() == null) {
                z = false;
            } else {
                int index = t.getIndex();
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(index >= 0)) {
                        throw new AssertionError();
                    }
                }
                removeAtImpl(index);
                z = true;
            }
            z2 = z;
        }
        return z2;
    }

    @PublishedApi
    @Nullable
    public final T firstImpl() {
        T[] tArr = this.a;
        if (tArr != null) {
            return tArr[0];
        }
        return null;
    }

    @PublishedApi
    @NotNull
    public final T removeAtImpl(int index) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getSize() > 0)) {
                throw new AssertionError();
            }
        }
        T[] tArr = this.a;
        Intrinsics.checkNotNull(tArr);
        setSize(getSize() - 1);
        if (index < getSize()) {
            swap(index, getSize());
            int j = (index - 1) / 2;
            if (index > 0) {
                T t = tArr[index];
                Intrinsics.checkNotNull(t);
                T t2 = tArr[j];
                Intrinsics.checkNotNull(t2);
                if (((Comparable) t).compareTo(t2) < 0) {
                    swap(index, j);
                    siftUpFrom(j);
                }
            }
            siftDownFrom(index);
        }
        T t3 = tArr[getSize()];
        Intrinsics.checkNotNull(t3);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(t3.getHeap() == this)) {
                throw new AssertionError();
            }
        }
        t3.setHeap(null);
        t3.setIndex(-1);
        tArr[getSize()] = null;
        return t3;
    }

    @PublishedApi
    public final void addImpl(@NotNull T t) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(t.getHeap() == null)) {
                throw new AssertionError();
            }
        }
        t.setHeap(this);
        ThreadSafeHeapNode[] a = realloc();
        int i = getSize();
        setSize(i + 1);
        a[i] = t;
        t.setIndex(i);
        siftUpFrom(i);
    }

    private final void siftUpFrom(int i) {
        ThreadSafeHeap<T> threadSafeHeap = this;
        while (i > 0) {
            T[] tArr = threadSafeHeap.a;
            Intrinsics.checkNotNull(tArr);
            int j = (i - 1) / 2;
            T t = tArr[j];
            Intrinsics.checkNotNull(t);
            T t2 = tArr[i];
            Intrinsics.checkNotNull(t2);
            if (((Comparable) t).compareTo(t2) <= 0) {
                return;
            }
            threadSafeHeap.swap(i, j);
            threadSafeHeap = threadSafeHeap;
            i = j;
        }
    }

    private final void siftDownFrom(int i) {
        ThreadSafeHeap<T> threadSafeHeap = this;
        while (true) {
            int j = (2 * i) + 1;
            if (j >= threadSafeHeap.getSize()) {
                return;
            }
            T[] tArr = threadSafeHeap.a;
            Intrinsics.checkNotNull(tArr);
            if (j + 1 < threadSafeHeap.getSize()) {
                T t = tArr[j + 1];
                Intrinsics.checkNotNull(t);
                T t2 = tArr[j];
                Intrinsics.checkNotNull(t2);
                if (((Comparable) t).compareTo(t2) < 0) {
                    j++;
                }
            }
            T t3 = tArr[i];
            Intrinsics.checkNotNull(t3);
            T t4 = tArr[j];
            Intrinsics.checkNotNull(t4);
            if (((Comparable) t3).compareTo(t4) <= 0) {
                return;
            }
            threadSafeHeap.swap(i, j);
            threadSafeHeap = threadSafeHeap;
            i = j;
        }
    }

    private final T[] realloc() {
        T[] tArr = this.a;
        if (tArr == null) {
            T[] tArr2 = (T[]) new ThreadSafeHeapNode[4];
            this.a = tArr2;
            return tArr2;
        }
        if (getSize() < tArr.length) {
            return tArr;
        }
        Object[] copyOf = Arrays.copyOf(tArr, getSize() * 2);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
        this.a = (T[]) ((ThreadSafeHeapNode[]) copyOf);
        return (T[]) ((ThreadSafeHeapNode[]) copyOf);
    }

    private final void swap(int i, int j) {
        ThreadSafeHeapNode[] a = this.a;
        Intrinsics.checkNotNull(a);
        ThreadSafeHeapNode ni = a[j];
        Intrinsics.checkNotNull(ni);
        ThreadSafeHeapNode nj = a[i];
        Intrinsics.checkNotNull(nj);
        a[i] = ni;
        a[j] = nj;
        ni.setIndex(i);
        nj.setIndex(j);
    }
}
