package com.sun.tools.internal.xjc.reader.gbind;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/gbind/ConnectedComponent.class */
public final class ConnectedComponent implements Iterable<Element> {
    private final List<Element> elements = new ArrayList();
    boolean isRequired;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !ConnectedComponent.class.desiredAssertionStatus();
    }

    public final boolean isCollection() {
        if (!$assertionsDisabled && this.elements.isEmpty()) {
            throw new AssertionError();
        }
        if (this.elements.size() > 1) {
            return true;
        }
        Element n = this.elements.get(0);
        return n.hasSelfLoop();
    }

    public final boolean isRequired() {
        return this.isRequired;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void add(Element e) {
        if (!$assertionsDisabled && this.elements.contains(e)) {
            throw new AssertionError();
        }
        this.elements.add(e);
    }

    @Override // java.lang.Iterable
    public Iterator<Element> iterator() {
        return this.elements.iterator();
    }

    public String toString() {
        String s = this.elements.toString();
        if (isRequired()) {
            s = s + '!';
        }
        if (isCollection()) {
            s = s + '*';
        }
        return s;
    }
}
