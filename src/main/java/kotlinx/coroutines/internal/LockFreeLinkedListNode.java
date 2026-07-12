package kotlinx.coroutines.internal;

import com.sun.tools.internal.ws.wsdl.document.jaxws.JAXWSBindingsConstants;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: LockFreeLinkedList.kt */
@InternalCoroutinesApi
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��@\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n��\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n��\b\u0017\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\bH\u0002J\u0019\u0010\u0016\u001a\u00060��j\u0002`\u00112\n\u0010\u0017\u001a\u00060��j\u0002`\u0011H\u0082\u0010J\u0012\u0010\u0018\u001a\u00020\u000b2\n\u0010\u0019\u001a\u00060��j\u0002`\u0011J\u001a\u0010\u001a\u001a\u00020\u000b2\n\u0010\u0019\u001a\u00060��j\u0002`\u00112\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001cJ \u0010 \u001a\u00020\u000b2\n\u0010\u0019\u001a\u00060��j\u0002`\u00112\n\u0010\r\u001a\u00060��j\u0002`\u0011H\u0001J\b\u0010!\u001a\u00020\u000bH\u0016J\u0010\u0010\"\u001a\n\u0018\u00010��j\u0004\u0018\u0001`\u0011H\u0001J\u0014\u0010#\u001a\u00020\u001e2\n\u0010\r\u001a\u00060��j\u0002`\u0011H\u0002J\u0011\u0010$\u001a\n\u0018\u00010��j\u0004\u0018\u0001`\u0011H\u0082\u0010J%\u0010%\u001a\u00020\u001e2\n\u0010&\u001a\u00060��j\u0002`\u00112\n\u0010\r\u001a\u00060��j\u0002`\u0011H��¢\u0006\u0002\b'J\b\u0010(\u001a\u00020)H\u0016R\u000f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005X\u0082\u0004R\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020��0\u0005X\u0082\u0004R\u0011\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005X\u0082\u0004R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0011\u0010\r\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0010\u001a\u00060��j\u0002`\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0014\u001a\u00060��j\u0002`\u00118F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013¨\u0006*"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "", Constants.CTOR, "()V", "_next", "Lkotlinx/atomicfu/AtomicRef;", "_prev", "_removedRef", "Lkotlinx/coroutines/internal/Removed;", "removed", "isRemoved", "", "()Z", "next", "getNext", "()Ljava/lang/Object;", "nextNode", "Lkotlinx/coroutines/internal/Node;", "getNextNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "prevNode", "getPrevNode", "findPrevNonRemoved", "current", "addOneIfEmpty", JAXWSBindingsConstants.NODE_ATTR, "addLast", "permissionsBitmask", "", "close", "", "forbiddenElementsBit", "addNext", "remove", "removeOrNext", "finishAdd", "correctPrev", "validateNode", "prev", "validateNode$kotlinx_coroutines_core", "toString", "", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nLockFreeLinkedList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LockFreeLinkedList.kt\nkotlinx/coroutines/internal/LockFreeLinkedListNode\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,288:1\n1#2:289\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/internal/LockFreeLinkedListNode.class */
public class LockFreeLinkedListNode {
    private volatile /* synthetic */ Object _next$volatile = this;
    private volatile /* synthetic */ Object _prev$volatile = this;
    private volatile /* synthetic */ Object _removedRef$volatile;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _next$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_next$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _prev$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_prev$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _removedRef$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_removedRef$volatile");

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

    private final /* synthetic */ Object get_removedRef$volatile() {
        return this._removedRef$volatile;
    }

    private final /* synthetic */ void set_removedRef$volatile(Object value) {
        this._removedRef$volatile = value;
    }

    private final /* synthetic */ void loop$atomicfu$ATOMIC_FIELD_UPDATER$Any(AtomicReferenceFieldUpdater handler$atomicfu, Object obj$atomicfu, Function1<Object, Unit> function1) {
        while (true) {
            function1.invoke(handler$atomicfu.get(obj$atomicfu));
        }
    }

    private final Removed removed() {
        Removed removed = (Removed) _removedRef$volatile$FU.get(this);
        if (removed != null) {
            return removed;
        }
        Removed it = new Removed(this);
        _removedRef$volatile$FU.set(this, it);
        return it;
    }

    public boolean isRemoved() {
        return getNext() instanceof Removed;
    }

    @NotNull
    public final Object getNext() {
        return _next$volatile$FU.get(this);
    }

    @NotNull
    public final LockFreeLinkedListNode getNextNode() {
        Object it = getNext();
        Removed removed = it instanceof Removed ? (Removed) it : null;
        if (removed != null) {
            LockFreeLinkedListNode lockFreeLinkedListNode = removed.ref;
            if (lockFreeLinkedListNode != null) {
                return lockFreeLinkedListNode;
            }
        }
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
        return (LockFreeLinkedListNode) it;
    }

    @NotNull
    public final LockFreeLinkedListNode getPrevNode() {
        LockFreeLinkedListNode correctPrev = correctPrev();
        return correctPrev == null ? findPrevNonRemoved((LockFreeLinkedListNode) _prev$volatile$FU.get(this)) : correctPrev;
    }

    private final LockFreeLinkedListNode findPrevNonRemoved(LockFreeLinkedListNode current) {
        LockFreeLinkedListNode lockFreeLinkedListNode = this;
        while (current.isRemoved()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode;
            current = (LockFreeLinkedListNode) _prev$volatile$FU.get(current);
        }
        return current;
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x0018 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean addOneIfEmpty(@NotNull LockFreeLinkedListNode node) {
        _prev$volatile$FU.set(node, this);
        _next$volatile$FU.set(node, this);
        while (next == this) {
            if (_next$volatile$FU.compareAndSet(this, this, node)) {
                node.finishAdd(this);
                return true;
            }
        }
        return false;
    }

    public final boolean addLast(@NotNull LockFreeLinkedListNode node, int permissionsBitmask) {
        LockFreeLinkedListNode currentPrev;
        do {
            currentPrev = getPrevNode();
            if (currentPrev instanceof ListClosed) {
                return (((ListClosed) currentPrev).forbiddenElementsBitmask & permissionsBitmask) == 0 && currentPrev.addLast(node, permissionsBitmask);
            }
        } while (!currentPrev.addNext(node, this));
        return true;
    }

    public final void close(int forbiddenElementsBit) {
        addLast(new ListClosed(forbiddenElementsBit), forbiddenElementsBit);
    }

    @PublishedApi
    public final boolean addNext(@NotNull LockFreeLinkedListNode node, @NotNull LockFreeLinkedListNode next) {
        _prev$volatile$FU.set(node, this);
        _next$volatile$FU.set(node, next);
        if (!_next$volatile$FU.compareAndSet(this, next, node)) {
            return false;
        }
        node.finishAdd(next);
        return true;
    }

    /* renamed from: remove */
    public boolean mo2925remove() {
        return removeOrNext() == null;
    }

    @PublishedApi
    @Nullable
    public final LockFreeLinkedListNode removeOrNext() {
        Object next;
        Removed removed;
        do {
            next = getNext();
            if (next instanceof Removed) {
                return ((Removed) next).ref;
            }
            if (next == this) {
                return (LockFreeLinkedListNode) next;
            }
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
            removed = ((LockFreeLinkedListNode) next).removed();
        } while (!_next$volatile$FU.compareAndSet(this, next, removed));
        ((LockFreeLinkedListNode) next).correctPrev();
        return null;
    }

    private final void finishAdd(LockFreeLinkedListNode next) {
        LockFreeLinkedListNode nextPrev;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = _prev$volatile$FU;
        do {
            nextPrev = (LockFreeLinkedListNode) handler$atomicfu$iv.get(next);
            if (getNext() != next) {
                return;
            }
        } while (!_prev$volatile$FU.compareAndSet(next, nextPrev, this));
        if (isRemoved()) {
            next.correctPrev();
        }
    }

    private final LockFreeLinkedListNode correctPrev() {
        LockFreeLinkedListNode lockFreeLinkedListNode = this;
        while (true) {
            LockFreeLinkedListNode lockFreeLinkedListNode2 = lockFreeLinkedListNode;
            LockFreeLinkedListNode oldPrev = (LockFreeLinkedListNode) _prev$volatile$FU.get(lockFreeLinkedListNode2);
            LockFreeLinkedListNode prev = oldPrev;
            LockFreeLinkedListNode last = null;
            while (true) {
                Object prevNext = _next$volatile$FU.get(prev);
                if (prevNext == lockFreeLinkedListNode2) {
                    if (oldPrev == prev) {
                        return prev;
                    }
                    if (!_prev$volatile$FU.compareAndSet(lockFreeLinkedListNode2, oldPrev, prev)) {
                        lockFreeLinkedListNode = lockFreeLinkedListNode2;
                    } else {
                        return prev;
                    }
                } else {
                    if (lockFreeLinkedListNode2.isRemoved()) {
                        return null;
                    }
                    if (prevNext instanceof Removed) {
                        if (last != null) {
                            if (!_next$volatile$FU.compareAndSet(last, prev, ((Removed) prevNext).ref)) {
                                lockFreeLinkedListNode = lockFreeLinkedListNode2;
                                break;
                            }
                            prev = last;
                            last = null;
                        } else {
                            prev = (LockFreeLinkedListNode) _prev$volatile$FU.get(prev);
                        }
                    } else {
                        last = prev;
                        Intrinsics.checkNotNull(prevNext, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
                        prev = (LockFreeLinkedListNode) prevNext;
                    }
                }
            }
        }
    }

    public final void validateNode$kotlinx_coroutines_core(@NotNull LockFreeLinkedListNode prev, @NotNull LockFreeLinkedListNode next) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(prev == _prev$volatile$FU.get(this))) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(next == _next$volatile$FU.get(this))) {
                throw new AssertionError();
            }
        }
    }

    @NotNull
    public String toString() {
        return new PropertyReference0Impl(this) { // from class: kotlinx.coroutines.internal.LockFreeLinkedListNode$toString$1
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return DebugStringsKt.getClassSimpleName(this.receiver);
            }
        } + '@' + DebugStringsKt.getHexAddress(this);
    }
}
