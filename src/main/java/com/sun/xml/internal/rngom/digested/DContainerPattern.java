package com.sun.xml.internal.rngom.digested;

import java.util.Iterator;

/* loaded from: target.jar:com/sun/xml/internal/rngom/digested/DContainerPattern.class */
public abstract class DContainerPattern extends DPattern implements Iterable<DPattern> {
    private DPattern head;
    private DPattern tail;

    public DPattern firstChild() {
        return this.head;
    }

    public DPattern lastChild() {
        return this.tail;
    }

    public int countChildren() {
        int i = 0;
        DPattern firstChild = firstChild();
        while (true) {
            DPattern p = firstChild;
            if (p != null) {
                i++;
                firstChild = p.next;
            } else {
                return i;
            }
        }
    }

    @Override // java.lang.Iterable
    public Iterator<DPattern> iterator() {
        return new Iterator<DPattern>() { // from class: com.sun.xml.internal.rngom.digested.DContainerPattern.1
            DPattern next;

            {
                this.next = DContainerPattern.this.head;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next != null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public DPattern next() {
                DPattern r = this.next;
                this.next = this.next.next;
                return r;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void add(DPattern child) {
        if (this.tail == null) {
            child.next = null;
            child.prev = null;
            this.tail = child;
            this.head = child;
            return;
        }
        child.prev = this.tail;
        this.tail.next = child;
        child.next = null;
        this.tail = child;
    }
}
