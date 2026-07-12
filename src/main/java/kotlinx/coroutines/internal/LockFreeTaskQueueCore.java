package kotlinx.coroutines.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.DebugKt;
import org.apache.fontbox.ttf.HeaderTable;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: LockFreeTaskQueue.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b��\u0018�� .*\b\b��\u0010\u0001*\u00020\u00022\u00020\u0002:\u0002-.B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0016\u001a\u00020\u0006J\u0013\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00028��¢\u0006\u0002\u0010\u0019J1\u0010\u001a\u001a\u0016\u0012\u0004\u0012\u00028��\u0018\u00010��j\n\u0012\u0004\u0012\u00028��\u0018\u0001`\f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00028��H\u0002¢\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u0004\u0018\u00010\u0002J,\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00028��\u0018\u00010��j\n\u0012\u0004\u0012\u00028��\u0018\u0001`\f2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u0004H\u0002J\f\u0010!\u001a\b\u0012\u0004\u0012\u00028��0��J\b\u0010\"\u001a\u00020#H\u0002J \u0010$\u001a\u0012\u0012\u0004\u0012\u00028��0��j\b\u0012\u0004\u0012\u00028��`\f2\u0006\u0010%\u001a\u00020#H\u0002J \u0010&\u001a\u0012\u0012\u0004\u0012\u00028��0��j\b\u0012\u0004\u0012\u00028��`\f2\u0006\u0010%\u001a\u00020#H\u0002J&\u0010'\u001a\b\u0012\u0004\u0012\u0002H)0(\"\u0004\b\u0001\u0010)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u0002H)0+J\u0006\u0010,\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R#\u0010\n\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00028��\u0018\u00010��j\n\u0012\u0004\u0012\u00028��\u0018\u0001`\f0\u000bX\u0082\u0004R\t\u0010\r\u001a\u00020\u000eX\u0082\u0004R\u0011\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0010X\u0082\u0004R\u0011\u0010\u0011\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006/"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "E", "", "capacity", "", "singleConsumer", "", Constants.CTOR, "(IZ)V", "mask", "_next", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/internal/Core;", "_state", "Lkotlinx/atomicfu/AtomicLong;", "array", "Lkotlinx/atomicfu/AtomicArray;", "isEmpty", "()Z", "size", "getSize", "()I", "close", "addLast", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_ELEMENT, "(Ljava/lang/Object;)I", "fillPlaceholder", "index", "(ILjava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "removeFirstOrNull", "removeSlowPath", "oldHead", "newHead", "next", "markFrozen", "", "allocateOrGetNextCopy", "state", "allocateNextCopy", "map", "", "R", "transform", "Lkotlin/Function1;", "isClosed", "Placeholder", "Companion", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nLockFreeTaskQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LockFreeTaskQueue.kt\nkotlinx/coroutines/internal/LockFreeTaskQueueCore\n+ 2 LockFreeTaskQueue.kt\nkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,304:1\n295#2,3:305\n295#2,3:308\n295#2,3:311\n295#2,3:314\n295#2,3:317\n295#2,3:321\n295#2,3:324\n1#3:320\n*S KotlinDebug\n*F\n+ 1 LockFreeTaskQueue.kt\nkotlinx/coroutines/internal/LockFreeTaskQueueCore\n*L\n87#1:305,3\n88#1:308,3\n103#1:311,3\n163#1:314,3\n196#1:317,3\n227#1:321,3\n243#1:324,3\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/internal/LockFreeTaskQueueCore.class */
public final class LockFreeTaskQueueCore<E> {
    private final int capacity;
    private final boolean singleConsumer;
    private final int mask;
    private volatile /* synthetic */ Object _next$volatile;
    private volatile /* synthetic */ long _state$volatile;
    private final /* synthetic */ AtomicReferenceArray array;
    public static final int INITIAL_CAPACITY = 8;
    public static final int CAPACITY_BITS = 30;
    public static final int MAX_CAPACITY_MASK = 1073741823;
    public static final int HEAD_SHIFT = 0;
    public static final long HEAD_MASK = 1073741823;
    public static final int TAIL_SHIFT = 30;
    public static final long TAIL_MASK = 1152921503533105152L;
    public static final int FROZEN_SHIFT = 60;
    public static final long FROZEN_MASK = 1152921504606846976L;
    public static final int CLOSED_SHIFT = 61;
    public static final long CLOSED_MASK = 2305843009213693952L;
    public static final int MIN_ADD_SPIN_CAPACITY = 1024;
    public static final int ADD_SUCCESS = 0;
    public static final int ADD_FROZEN = 1;
    public static final int ADD_CLOSED = 2;

    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final /* synthetic */ AtomicReferenceFieldUpdater _next$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, Object.class, "_next$volatile");
    private static final /* synthetic */ AtomicLongFieldUpdater _state$volatile$FU = AtomicLongFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, "_state$volatile");

    @JvmField
    @NotNull
    public static final Symbol REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");

    private final /* synthetic */ Object get_next$volatile() {
        return this._next$volatile;
    }

    private final /* synthetic */ void set_next$volatile(Object value) {
        this._next$volatile = value;
    }

    private final /* synthetic */ long get_state$volatile() {
        return this._state$volatile;
    }

    private final /* synthetic */ void set_state$volatile(long value) {
        this._state$volatile = value;
    }

    private final /* synthetic */ AtomicReferenceArray getArray() {
        return this.array;
    }

    private final /* synthetic */ void update$atomicfu$ATOMIC_FIELD_UPDATER$Long(AtomicLongFieldUpdater handler$atomicfu, Object obj$atomicfu, Function1<? super Long, Long> function1) {
        long j;
        do {
            j = handler$atomicfu.get(obj$atomicfu);
        } while (!handler$atomicfu.compareAndSet(obj$atomicfu, j, function1.invoke(Long.valueOf(j)).longValue()));
    }

    private final /* synthetic */ void loop$atomicfu$ATOMIC_FIELD_UPDATER$Long(AtomicLongFieldUpdater handler$atomicfu, Object obj$atomicfu, Function1<? super Long, Unit> function1) {
        while (true) {
            function1.invoke(Long.valueOf(handler$atomicfu.get(obj$atomicfu)));
        }
    }

    private final /* synthetic */ long updateAndGet$atomicfu$ATOMIC_FIELD_UPDATER$Long(AtomicLongFieldUpdater handler$atomicfu, Object obj$atomicfu, Function1<? super Long, Long> function1) {
        long j;
        Long invoke;
        do {
            j = handler$atomicfu.get(obj$atomicfu);
            invoke = function1.invoke(Long.valueOf(j));
        } while (!handler$atomicfu.compareAndSet(obj$atomicfu, j, invoke.longValue()));
        return invoke.longValue();
    }

    private final /* synthetic */ void loop$atomicfu$ATOMIC_FIELD_UPDATER$Any(AtomicReferenceFieldUpdater handler$atomicfu, Object obj$atomicfu, Function1<Object, Unit> function1) {
        while (true) {
            function1.invoke(handler$atomicfu.get(obj$atomicfu));
        }
    }

    public LockFreeTaskQueueCore(int capacity, boolean singleConsumer) {
        this.capacity = capacity;
        this.singleConsumer = singleConsumer;
        this.mask = this.capacity - 1;
        this.array = new AtomicReferenceArray(this.capacity);
        if (!(this.mask <= 1073741823)) {
            throw new IllegalStateException("Check failed.");
        }
        if (!((this.capacity & this.mask) == 0)) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final boolean isEmpty() {
        Companion companion = Companion;
        long $this$withState$iv = _state$volatile$FU.get(this);
        int head$iv = (int) (($this$withState$iv & HEAD_MASK) >> 0);
        int tail$iv = (int) (($this$withState$iv & TAIL_MASK) >> 30);
        return head$iv == tail$iv;
    }

    public final int getSize() {
        Companion companion = Companion;
        long $this$withState$iv = _state$volatile$FU.get(this);
        int head$iv = (int) (($this$withState$iv & HEAD_MASK) >> 0);
        int tail$iv = (int) (($this$withState$iv & TAIL_MASK) >> 30);
        return (tail$iv - head$iv) & MAX_CAPACITY_MASK;
    }

    public final boolean close() {
        long state;
        AtomicLongFieldUpdater handler$atomicfu$iv = _state$volatile$FU;
        do {
            state = handler$atomicfu$iv.get(this);
            if ((state & CLOSED_MASK) != 0) {
                return true;
            }
            if ((state & FROZEN_MASK) != 0) {
                return false;
            }
        } while (!handler$atomicfu$iv.compareAndSet(this, state, state | CLOSED_MASK));
        return true;
    }

    public final int addLast(@NotNull E e) {
        AtomicLongFieldUpdater handler$atomicfu$iv = _state$volatile$FU;
        while (true) {
            long state = handler$atomicfu$iv.get(this);
            if ((state & 3458764513820540928L) != 0) {
                return Companion.addFailReason(state);
            }
            Companion companion = Companion;
            int head$iv = (int) ((state & HEAD_MASK) >> 0);
            int tail$iv = (int) ((state & TAIL_MASK) >> 30);
            int mask = this.mask;
            if (((tail$iv + 2) & mask) == (head$iv & mask)) {
                return 1;
            }
            if (!this.singleConsumer && getArray().get(tail$iv & mask) != null) {
                if (this.capacity < 1024 || ((tail$iv - head$iv) & MAX_CAPACITY_MASK) > (this.capacity >> 1)) {
                    return 1;
                }
            } else {
                int newTail = (tail$iv + 1) & MAX_CAPACITY_MASK;
                if (_state$volatile$FU.compareAndSet(this, state, Companion.updateTail(state, newTail))) {
                    getArray().set(tail$iv & mask, e);
                    LockFreeTaskQueueCore lockFreeTaskQueueCore = this;
                    do {
                        LockFreeTaskQueueCore cur = lockFreeTaskQueueCore;
                        if ((_state$volatile$FU.get(cur) & FROZEN_MASK) != 0) {
                            lockFreeTaskQueueCore = cur.next().fillPlaceholder(tail$iv, e);
                        } else {
                            return 0;
                        }
                    } while (lockFreeTaskQueueCore != null);
                    return 0;
                }
            }
        }
    }

    private final LockFreeTaskQueueCore<E> fillPlaceholder(int index, E e) {
        Object old = getArray().get(index & this.mask);
        if ((old instanceof Placeholder) && ((Placeholder) old).index == index) {
            getArray().set(index & this.mask, e);
            return this;
        }
        return null;
    }

    @Nullable
    public final Object removeFirstOrNull() {
        AtomicLongFieldUpdater handler$atomicfu$iv = _state$volatile$FU;
        while (true) {
            long state = handler$atomicfu$iv.get(this);
            if ((state & FROZEN_MASK) != 0) {
                return REMOVE_FROZEN;
            }
            Companion companion = Companion;
            int head$iv = (int) ((state & HEAD_MASK) >> 0);
            int tail$iv = (int) ((state & TAIL_MASK) >> 30);
            if ((tail$iv & this.mask) == (head$iv & this.mask)) {
                return null;
            }
            Object element = getArray().get(head$iv & this.mask);
            if (element == null) {
                if (this.singleConsumer) {
                    return null;
                }
            } else {
                if (element instanceof Placeholder) {
                    return null;
                }
                int newHead = (head$iv + 1) & MAX_CAPACITY_MASK;
                if (!_state$volatile$FU.compareAndSet(this, state, Companion.updateHead(state, newHead))) {
                    if (this.singleConsumer) {
                        LockFreeTaskQueueCore lockFreeTaskQueueCore = this;
                        do {
                            LockFreeTaskQueueCore cur = lockFreeTaskQueueCore;
                            lockFreeTaskQueueCore = cur.removeSlowPath(head$iv, newHead);
                        } while (lockFreeTaskQueueCore != null);
                        return element;
                    }
                } else {
                    getArray().set(head$iv & this.mask, null);
                    return element;
                }
            }
        }
    }

    private final LockFreeTaskQueueCore<E> removeSlowPath(int oldHead, int newHead) {
        long state;
        int head$iv;
        AtomicLongFieldUpdater handler$atomicfu$iv = _state$volatile$FU;
        do {
            state = handler$atomicfu$iv.get(this);
            Companion companion = Companion;
            head$iv = (int) ((state & HEAD_MASK) >> 0);
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(head$iv == oldHead)) {
                    throw new AssertionError();
                }
            }
            if ((state & FROZEN_MASK) != 0) {
                return next();
            }
        } while (!_state$volatile$FU.compareAndSet(this, state, Companion.updateHead(state, newHead)));
        getArray().set(head$iv & this.mask, null);
        return null;
    }

    @NotNull
    public final LockFreeTaskQueueCore<E> next() {
        return allocateOrGetNextCopy(markFrozen());
    }

    private final long markFrozen() {
        long state;
        long j;
        AtomicLongFieldUpdater handler$atomicfu$iv = _state$volatile$FU;
        do {
            state = handler$atomicfu$iv.get(this);
            if ((state & FROZEN_MASK) != 0) {
                return state;
            }
            j = state | FROZEN_MASK;
        } while (!handler$atomicfu$iv.compareAndSet(this, state, j));
        return j;
    }

    private final LockFreeTaskQueueCore<E> allocateOrGetNextCopy(long state) {
        AtomicReferenceFieldUpdater handler$atomicfu$iv = _next$volatile$FU;
        while (true) {
            LockFreeTaskQueueCore next = (LockFreeTaskQueueCore) handler$atomicfu$iv.get(this);
            if (next != null) {
                return next;
            }
            _next$volatile$FU.compareAndSet(this, null, allocateNextCopy(state));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v21, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v25, types: [kotlinx.coroutines.internal.LockFreeTaskQueueCore$Placeholder] */
    private final LockFreeTaskQueueCore<E> allocateNextCopy(long state) {
        LockFreeTaskQueueCore next = new LockFreeTaskQueueCore(this.capacity * 2, this.singleConsumer);
        Companion companion = Companion;
        int head$iv = (int) ((state & HEAD_MASK) >> 0);
        int tail$iv = (int) ((state & TAIL_MASK) >> 30);
        for (int index = head$iv; (index & this.mask) != (tail$iv & this.mask); index++) {
            E e = getArray().get(index & this.mask);
            if (e == 0) {
                e = new Placeholder(index);
            }
            next.getArray().set(index & next.mask, e);
        }
        _state$volatile$FU.set(next, Companion.wo(state, FROZEN_MASK));
        return next;
    }

    @NotNull
    public final <R> List<R> map(@NotNull Function1<? super E, ? extends R> function1) {
        ArrayList res = new ArrayList(this.capacity);
        Companion companion = Companion;
        long $this$withState$iv = _state$volatile$FU.get(this);
        int head$iv = (int) (($this$withState$iv & HEAD_MASK) >> 0);
        int tail$iv = (int) (($this$withState$iv & TAIL_MASK) >> 30);
        for (int index = head$iv; (index & this.mask) != (tail$iv & this.mask); index++) {
            Object element = getArray().get(index & this.mask);
            if (element != null && !(element instanceof Placeholder)) {
                res.add(function1.invoke(element));
            }
        }
        return res;
    }

    public final boolean isClosed() {
        return (_state$volatile$FU.get(this) & CLOSED_MASK) != 0;
    }

    /* compiled from: LockFreeTaskQueue.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0003\b��\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;", "", "index", "", Constants.CTOR, "(I)V", "kotlinx-coroutines-core"})
    /* loaded from: target.jar:kotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder.class */
    public static final class Placeholder {

        @JvmField
        public final int index;

        public Placeholder(int index) {
            this.index = index;
        }
    }

    /* compiled from: LockFreeTaskQueue.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0080\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0017\u001a\u00020\n*\u00020\n2\u0006\u0010\u0018\u001a\u00020\nH\u0086\u0004J\u0012\u0010\u0019\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u0005J\u0012\u0010\u001b\u001a\u00020\n*\u00020\n2\u0006\u0010\u001c\u001a\u00020\u0005JP\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0001\u0010\u001e*\u00020\n26\u0010\u001f\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b($\u0012\u0004\u0012\u0002H\u001e0 H\u0086\b¢\u0006\u0002\u0010%J\n\u0010&\u001a\u00020\u0005*\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n��R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\f\u001a\u00020\nX\u0086T¢\u0006\u0002\n��R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u000e\u001a\u00020\nX\u0086T¢\u0006\u0002\n��R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0010\u001a\u00020\nX\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u0010\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004¢\u0006\u0002\n��R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��¨\u0006'"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;", "", Constants.CTOR, "()V", "INITIAL_CAPACITY", "", "CAPACITY_BITS", "MAX_CAPACITY_MASK", "HEAD_SHIFT", "HEAD_MASK", "", "TAIL_SHIFT", "TAIL_MASK", "FROZEN_SHIFT", "FROZEN_MASK", "CLOSED_SHIFT", "CLOSED_MASK", "MIN_ADD_SPIN_CAPACITY", "REMOVE_FROZEN", "Lkotlinx/coroutines/internal/Symbol;", "ADD_SUCCESS", "ADD_FROZEN", "ADD_CLOSED", "wo", "other", "updateHead", "newHead", "updateTail", "newTail", "withState", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_BLOCK, "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", HeaderTable.TAG, "tail", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "addFailReason", "kotlinx-coroutines-core"})
    /* loaded from: target.jar:kotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        public final long wo(long $this$wo, long other) {
            return $this$wo & (other ^ (-1));
        }

        public final long updateHead(long $this$updateHead, int newHead) {
            return wo($this$updateHead, LockFreeTaskQueueCore.HEAD_MASK) | (newHead << 0);
        }

        public final long updateTail(long $this$updateTail, int newTail) {
            return wo($this$updateTail, LockFreeTaskQueueCore.TAIL_MASK) | (newTail << 30);
        }

        public final <T> T withState(long $this$withState, @NotNull Function2<? super Integer, ? super Integer, ? extends T> function2) {
            int head = (int) (($this$withState & LockFreeTaskQueueCore.HEAD_MASK) >> 0);
            int tail = (int) (($this$withState & LockFreeTaskQueueCore.TAIL_MASK) >> 30);
            return function2.invoke(Integer.valueOf(head), Integer.valueOf(tail));
        }

        public final int addFailReason(long $this$addFailReason) {
            return ($this$addFailReason & LockFreeTaskQueueCore.CLOSED_MASK) != 0 ? 2 : 1;
        }
    }
}
