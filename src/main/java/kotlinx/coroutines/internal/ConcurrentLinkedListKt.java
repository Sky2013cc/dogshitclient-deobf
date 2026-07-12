package kotlinx.coroutines.internal;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* compiled from: ConcurrentLinkedList.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��N\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\u001ag\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\u0002H\u00022\u0006\u0010\u0004\u001a\u00020\u000526\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u0002H\u00020\u0007H��¢\u0006\u0002\u0010\u000b\u001a+\u0010\f\u001a\u00020\r\"\u000e\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u000e2\u0006\u0010\u000f\u001a\u0002H\u0002H\u0080\b\u001as\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u0002H\u000228\b\b\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u0002H\u00020\u0007H\u0080\b\u001a!\u0010\u0012\u001a\u0002H\u0013\"\u000e\b��\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00130\u0014*\u0002H\u0013H��¢\u0006\u0002\u0010\u0015\u001a8\u0010\u0016\u001a\u00020\r*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192!\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\r0\u001bH\u0082\b\"\u000e\u0010\u001d\u001a\u00020\u0019X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n��¨\u0006 "}, d2 = {"findSegmentInternal", "Lkotlinx/coroutines/internal/SegmentOrClosed;", "S", "Lkotlinx/coroutines/internal/Segment;", Constants.ATTR_ID, "", "createNewSegment", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "prev", "(Lkotlinx/coroutines/internal/Segment;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "moveForward", "", "Lkotlinx/atomicfu/AtomicRef;", "to", "findSegmentAndMoveForward", "startFrom", "close", "N", "Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "addConditionally", "Lkotlinx/atomicfu/AtomicInt;", "delta", "", "condition", "Lkotlin/Function1;", "cur", "POINTERS_SHIFT", "CLOSED", "Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nConcurrentLinkedList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListKt\n+ 2 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListNode\n*L\n1#1,265:1\n42#1,8:280\n103#2,7:266\n103#2,7:273\n*S KotlinDebug\n*F\n+ 1 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListKt\n*L\n70#1:280,8\n23#1:266,7\n81#1:273,7\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/internal/ConcurrentLinkedListKt.class */
public final class ConcurrentLinkedListKt {
    private static final int POINTERS_SHIFT = 16;

    @NotNull
    private static final Symbol CLOSED = new Symbol("CLOSED");

    public static final /* synthetic */ Symbol access$getCLOSED$p() {
        return CLOSED;
    }

    @NotNull
    public static final <S extends Segment<S>> Object findSegmentInternal(@NotNull S s, long id, @NotNull Function2<? super Long, ? super S, ? extends S> function2) {
        Segment cur = s;
        while (true) {
            if (cur.id < id || cur.isRemoved()) {
                ConcurrentLinkedListNode this_$iv = cur;
                Object it$iv = this_$iv.getNextOrClosed();
                if (it$iv == CLOSED) {
                    return SegmentOrClosed.m2941constructorimpl(CLOSED);
                }
                Segment next = (Segment) ((ConcurrentLinkedListNode) it$iv);
                if (next != null) {
                    cur = next;
                } else {
                    Segment newTail = function2.invoke(Long.valueOf(cur.id + 1), cur);
                    if (cur.trySetNext(newTail)) {
                        if (cur.isRemoved()) {
                            cur.remove();
                        }
                        cur = newTail;
                    }
                }
            } else {
                return SegmentOrClosed.m2941constructorimpl(cur);
            }
        }
    }

    public static final /* synthetic */ <S extends Segment<S>> boolean moveForward$atomicfu$ATOMIC_FIELD_UPDATER$Any(AtomicReferenceFieldUpdater handler$atomicfu, Object obj$atomicfu, S s) {
        while (true) {
            Segment cur = (Segment) handler$atomicfu.get(obj$atomicfu);
            if (cur.id >= s.id) {
                return true;
            }
            if (!s.tryIncPointers$kotlinx_coroutines_core()) {
                return false;
            }
            if (handler$atomicfu.compareAndSet(obj$atomicfu, cur, s)) {
                if (cur.decPointers$kotlinx_coroutines_core()) {
                    cur.remove();
                    return true;
                }
                return true;
            }
            if (s.decPointers$kotlinx_coroutines_core()) {
                s.remove();
            }
        }
    }

    public static final /* synthetic */ <S extends Segment<S>> boolean moveForward$atomicfu$BOXED_ATOMIC$Any(AtomicReference handler$atomicfu, S s) {
        while (true) {
            Segment cur = (Segment) handler$atomicfu.get();
            if (cur.id >= s.id) {
                return true;
            }
            if (!s.tryIncPointers$kotlinx_coroutines_core()) {
                return false;
            }
            if (handler$atomicfu.compareAndSet(cur, s)) {
                if (cur.decPointers$kotlinx_coroutines_core()) {
                    cur.remove();
                    return true;
                }
                return true;
            }
            if (s.decPointers$kotlinx_coroutines_core()) {
                s.remove();
            }
        }
    }

    public static final /* synthetic */ <S extends Segment<S>> boolean moveForward$atomicfu$ATOMIC_ARRAY$Any(AtomicReferenceArray handler$atomicfu, int index$atomicfu, S s) {
        while (true) {
            Segment cur = (Segment) handler$atomicfu.get(index$atomicfu);
            if (cur.id >= s.id) {
                return true;
            }
            if (!s.tryIncPointers$kotlinx_coroutines_core()) {
                return false;
            }
            if (handler$atomicfu.compareAndSet(index$atomicfu, cur, s)) {
                if (cur.decPointers$kotlinx_coroutines_core()) {
                    cur.remove();
                    return true;
                }
                return true;
            }
            if (s.decPointers$kotlinx_coroutines_core()) {
                s.remove();
            }
        }
    }

    public static final /* synthetic */ <S extends Segment<S>> Object findSegmentAndMoveForward$atomicfu$ATOMIC_FIELD_UPDATER$Any(AtomicReferenceFieldUpdater handler$atomicfu, Object obj$atomicfu, long id, S s, Function2<? super Long, ? super S, ? extends S> function2) {
        Object s2;
        boolean z;
        do {
            s2 = findSegmentInternal(s, id, function2);
            if (SegmentOrClosed.m2936isClosedimpl(s2)) {
                break;
            }
            Segment to$iv = SegmentOrClosed.m2937getSegmentimpl(s2);
            while (true) {
                Segment cur$iv = (Segment) handler$atomicfu.get(obj$atomicfu);
                if (cur$iv.id >= to$iv.id) {
                    z = true;
                    break;
                }
                if (!to$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                }
                if (handler$atomicfu.compareAndSet(obj$atomicfu, cur$iv, to$iv)) {
                    if (cur$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv.remove();
                    }
                    z = true;
                } else if (to$iv.decPointers$kotlinx_coroutines_core()) {
                    to$iv.remove();
                }
            }
        } while (!z);
        return s2;
    }

    public static final /* synthetic */ <S extends Segment<S>> Object findSegmentAndMoveForward$atomicfu$BOXED_ATOMIC$Any(AtomicReference handler$atomicfu, long id, S s, Function2<? super Long, ? super S, ? extends S> function2) {
        Object s2;
        boolean z;
        do {
            s2 = findSegmentInternal(s, id, function2);
            if (SegmentOrClosed.m2936isClosedimpl(s2)) {
                break;
            }
            Segment to$iv = SegmentOrClosed.m2937getSegmentimpl(s2);
            while (true) {
                Segment cur$iv = (Segment) handler$atomicfu.get();
                if (cur$iv.id >= to$iv.id) {
                    z = true;
                    break;
                }
                if (!to$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                }
                if (handler$atomicfu.compareAndSet(cur$iv, to$iv)) {
                    if (cur$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv.remove();
                    }
                    z = true;
                } else if (to$iv.decPointers$kotlinx_coroutines_core()) {
                    to$iv.remove();
                }
            }
        } while (!z);
        return s2;
    }

    public static final /* synthetic */ <S extends Segment<S>> Object findSegmentAndMoveForward$atomicfu$ATOMIC_ARRAY$Any(AtomicReferenceArray handler$atomicfu, int index$atomicfu, long id, S s, Function2<? super Long, ? super S, ? extends S> function2) {
        Object s2;
        boolean z;
        do {
            s2 = findSegmentInternal(s, id, function2);
            if (SegmentOrClosed.m2936isClosedimpl(s2)) {
                break;
            }
            Segment to$iv = SegmentOrClosed.m2937getSegmentimpl(s2);
            while (true) {
                Segment cur$iv = (Segment) handler$atomicfu.get(index$atomicfu);
                if (cur$iv.id >= to$iv.id) {
                    z = true;
                    break;
                }
                if (!to$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                }
                if (handler$atomicfu.compareAndSet(index$atomicfu, cur$iv, to$iv)) {
                    if (cur$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv.remove();
                    }
                    z = true;
                } else if (to$iv.decPointers$kotlinx_coroutines_core()) {
                    to$iv.remove();
                }
            }
        } while (!z);
        return s2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode] */
    @NotNull
    public static final <N extends ConcurrentLinkedListNode<N>> N close(@NotNull N n) {
        N n2 = n;
        while (true) {
            ConcurrentLinkedListNode this_$iv = n2;
            Object it$iv = this_$iv.getNextOrClosed();
            if (it$iv == CLOSED) {
                return n2;
            }
            ?? r0 = (ConcurrentLinkedListNode) it$iv;
            if (r0 == 0) {
                if (n2.markAsClosed()) {
                    return n2;
                }
            } else {
                n2 = r0;
            }
        }
    }

    private static final /* synthetic */ boolean addConditionally$atomicfu$ATOMIC_FIELD_UPDATER$Int(AtomicIntegerFieldUpdater handler$atomicfu, Object obj$atomicfu, int delta, Function1<? super Integer, Boolean> function1) {
        int cur;
        do {
            cur = handler$atomicfu.get(obj$atomicfu);
            if (!function1.invoke(Integer.valueOf(cur)).booleanValue()) {
                return false;
            }
        } while (!handler$atomicfu.compareAndSet(obj$atomicfu, cur, cur + delta));
        return true;
    }

    private static final /* synthetic */ boolean addConditionally$atomicfu$BOXED_ATOMIC$Int(AtomicInteger handler$atomicfu, int delta, Function1<? super Integer, Boolean> function1) {
        int cur;
        do {
            cur = handler$atomicfu.get();
            if (!function1.invoke(Integer.valueOf(cur)).booleanValue()) {
                return false;
            }
        } while (!handler$atomicfu.compareAndSet(cur, cur + delta));
        return true;
    }

    private static final /* synthetic */ boolean addConditionally$atomicfu$ATOMIC_ARRAY$Int(AtomicIntegerArray handler$atomicfu, int index$atomicfu, int delta, Function1<? super Integer, Boolean> function1) {
        int cur;
        do {
            cur = handler$atomicfu.get(index$atomicfu);
            if (!function1.invoke(Integer.valueOf(cur)).booleanValue()) {
                return false;
            }
        } while (!handler$atomicfu.compareAndSet(index$atomicfu, cur, cur + delta));
        return true;
    }
}
