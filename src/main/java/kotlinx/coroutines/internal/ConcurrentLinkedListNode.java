package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ConcurrentLinkedList.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\b\b \u0018��*\u000e\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010��2\u00020\u0002B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018��¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\f\u001a\u0004\u0018\u00018��2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0086\b¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00028��¢\u0006\u0002\u0010\u0017J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u0015J\u0006\u0010\u001f\u001a\u00020\u001cR\u0011\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0007X\u0082\u0004R\u0011\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018��0\u0007X\u0082\u0004R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0011\u001a\u0004\u0018\u00018��8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0003\u001a\u0004\u0018\u00018��8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0013R\u0012\u0010\u001e\u001a\u00020\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0019R\u0016\u0010 \u001a\u0004\u0018\u00018��8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0013R\u0014\u0010\"\u001a\u00028��8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0013¨\u0006$"}, d2 = {"Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "N", "", "prev", Constants.CTOR, "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)V", "_next", "Lkotlinx/atomicfu/AtomicRef;", "_prev", "nextOrClosed", "getNextOrClosed", "()Ljava/lang/Object;", "nextOrIfClosed", "onClosedAction", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "next", "getNext", "()Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "trySetNext", "", "value", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Z", "isTail", "()Z", "getPrev", "cleanPrev", "", "markAsClosed", "isRemoved", "remove", "aliveSegmentLeft", "getAliveSegmentLeft", "aliveSegmentRight", "getAliveSegmentRight", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nConcurrentLinkedList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListNode\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,265:1\n103#1,7:266\n1#2:273\n*S KotlinDebug\n*F\n+ 1 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListNode\n*L\n111#1:266,7\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/internal/ConcurrentLinkedListNode.class */
public abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {
    private volatile /* synthetic */ Object _next$volatile;
    private volatile /* synthetic */ Object _prev$volatile;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _next$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _prev$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev$volatile");

    private final /* synthetic */ Object get_next$volatile() {
        return this._next$volatile;
    }

    private final /* synthetic */ void set_next$volatile(Object value) {
        this._next$volatile = value;
    }

    private final /* synthetic */ Object get_prev$volatile() {
        return this._prev$volatile;
    }

    private final /* synthetic */ void set_prev$volatile(Object value) {
        this._prev$volatile = value;
    }

    public abstract boolean isRemoved();

    private final /* synthetic */ void update$atomicfu$ATOMIC_FIELD_UPDATER$Any(AtomicReferenceFieldUpdater handler$atomicfu, Object obj$atomicfu, Function1<Object, ? extends Object> function1) {
        Object obj;
        do {
            obj = handler$atomicfu.get(obj$atomicfu);
        } while (!handler$atomicfu.compareAndSet(obj$atomicfu, obj, function1.invoke(obj)));
    }

    public ConcurrentLinkedListNode(@Nullable N n) {
        this._prev$volatile = n;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getNextOrClosed() {
        return _next$volatile$FU.get(this);
    }

    @Nullable
    public final N nextOrIfClosed(@NotNull Function0 onClosedAction) {
        Object it = getNextOrClosed();
        if (it == ConcurrentLinkedListKt.access$getCLOSED$p()) {
            onClosedAction.invoke();
            throw new KotlinNothingValueException();
        }
        return (N) it;
    }

    @Nullable
    public final N getNext() {
        Object it$iv = getNextOrClosed();
        if (it$iv == ConcurrentLinkedListKt.access$getCLOSED$p()) {
            return null;
        }
        return (N) it$iv;
    }

    public final boolean trySetNext(@NotNull N n) {
        return _next$volatile$FU.compareAndSet(this, null, n);
    }

    public final boolean isTail() {
        return getNext() == null;
    }

    @Nullable
    public final N getPrev() {
        return (N) _prev$volatile$FU.get(this);
    }

    public final void cleanPrev() {
        _prev$volatile$FU.set(this, null);
    }

    public final boolean markAsClosed() {
        return _next$volatile$FU.compareAndSet(this, null, ConcurrentLinkedListKt.access$getCLOSED$p());
    }

    public final void remove() {
        Object obj;
        ConcurrentLinkedListNode it;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(isRemoved() || isTail())) {
                throw new AssertionError();
            }
        }
        if (isTail()) {
            return;
        }
        while (true) {
            N aliveSegmentLeft = getAliveSegmentLeft();
            N aliveSegmentRight = getAliveSegmentRight();
            AtomicReferenceFieldUpdater handler$atomicfu$iv = _prev$volatile$FU;
            do {
                obj = handler$atomicfu$iv.get(aliveSegmentRight);
                it = (ConcurrentLinkedListNode) obj;
            } while (!handler$atomicfu$iv.compareAndSet(aliveSegmentRight, obj, it == null ? null : aliveSegmentLeft));
            if (aliveSegmentLeft != null) {
                _next$volatile$FU.set(aliveSegmentLeft, aliveSegmentRight);
            }
            if (!aliveSegmentRight.isRemoved() || aliveSegmentRight.isTail()) {
                if (aliveSegmentLeft == null || !aliveSegmentLeft.isRemoved()) {
                    return;
                }
            }
        }
    }

    private final N getAliveSegmentLeft() {
        N n;
        N prev = getPrev();
        while (true) {
            n = prev;
            if (n == null || !n.isRemoved()) {
                break;
            }
            prev = (N) _prev$volatile$FU.get(n);
        }
        return n;
    }

    private final N getAliveSegmentRight() {
        N n;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(!isTail())) {
                throw new AssertionError();
            }
        }
        N next = getNext();
        Intrinsics.checkNotNull(next);
        do {
            n = next;
            if (n.isRemoved()) {
                next = (N) n.getNext();
            } else {
                return n;
            }
        } while (next != null);
        return n;
    }
}
