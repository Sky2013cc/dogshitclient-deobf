package com.sun.tools.example.debug.tty;

import com.sun.jdi.ThreadGroupReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/* loaded from: target.jar:com/sun/tools/example/debug/tty/ThreadGroupIterator.class */
class ThreadGroupIterator implements Iterator<ThreadGroupReference> {
    private final Stack<Iterator<ThreadGroupReference>> stack;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThreadGroupIterator(List<ThreadGroupReference> list) {
        this.stack = new Stack<>();
        push(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThreadGroupIterator(ThreadGroupReference threadGroupReference) {
        this.stack = new Stack<>();
        ArrayList arrayList = new ArrayList();
        arrayList.add(threadGroupReference);
        push(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThreadGroupIterator() {
        this(Env.vm().topLevelThreadGroups());
    }

    private Iterator<ThreadGroupReference> top() {
        return this.stack.peek();
    }

    private void push(List<ThreadGroupReference> list) {
        this.stack.push(list.iterator());
        while (!this.stack.isEmpty() && !top().hasNext()) {
            this.stack.pop();
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return !this.stack.isEmpty();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public ThreadGroupReference next() {
        return nextThreadGroup();
    }

    public ThreadGroupReference nextThreadGroup() {
        ThreadGroupReference next = top().next();
        push(next.threadGroups());
        return next;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ThreadGroupReference find(String str) {
        ThreadGroupIterator threadGroupIterator = new ThreadGroupIterator();
        while (threadGroupIterator.hasNext()) {
            ThreadGroupReference nextThreadGroup = threadGroupIterator.nextThreadGroup();
            if (nextThreadGroup.name().equals(str)) {
                return nextThreadGroup;
            }
        }
        return null;
    }
}
