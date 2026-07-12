package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: LockFreeLinkedList.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0001\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\b\u0012\u00060\u0001j\u0002`\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0086\bJ\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\r¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", Constants.CTOR, "()V", "forEach", "", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_BLOCK, "Lkotlin/Function1;", "Lkotlinx/coroutines/internal/Node;", "remove", "", "isRemoved", "", "()Z", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/LockFreeLinkedListHead.class */
public class LockFreeLinkedListHead extends LockFreeLinkedListNode {
    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    /* renamed from: remove, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ boolean mo2925remove() {
        return ((Boolean) remove()).booleanValue();
    }

    public final void forEach(@NotNull Function1<? super LockFreeLinkedListNode, Unit> function1) {
        Object next = getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
        LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
        while (true) {
            LockFreeLinkedListNode cur = lockFreeLinkedListNode;
            if (!Intrinsics.areEqual(cur, this)) {
                function1.invoke(cur);
                lockFreeLinkedListNode = cur.getNextNode();
            } else {
                return;
            }
        }
    }

    @NotNull
    public final Void remove() {
        throw new IllegalStateException("head cannot be removed".toString());
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public boolean isRemoved() {
        return false;
    }
}
