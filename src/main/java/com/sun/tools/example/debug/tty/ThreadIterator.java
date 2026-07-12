package com.sun.tools.example.debug.tty;

import com.sun.jdi.ThreadGroupReference;
import com.sun.jdi.ThreadReference;
import java.util.Iterator;
import java.util.List;

/* loaded from: target.jar:com/sun/tools/example/debug/tty/ThreadIterator.class */
class ThreadIterator implements Iterator<ThreadReference> {
    Iterator<ThreadReference> it;
    ThreadGroupIterator tgi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThreadIterator(ThreadGroupReference threadGroupReference) {
        this.it = null;
        this.tgi = new ThreadGroupIterator(threadGroupReference);
    }

    ThreadIterator(List<ThreadGroupReference> list) {
        this.it = null;
        this.tgi = new ThreadGroupIterator(list);
    }

    ThreadIterator() {
        this.it = null;
        this.tgi = new ThreadGroupIterator();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (true) {
            if (this.it == null || !this.it.hasNext()) {
                if (!this.tgi.hasNext()) {
                    return false;
                }
                this.it = this.tgi.nextThreadGroup().threads().iterator();
            } else {
                return true;
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public ThreadReference next() {
        return this.it.next();
    }

    public ThreadReference nextThread() {
        return next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
