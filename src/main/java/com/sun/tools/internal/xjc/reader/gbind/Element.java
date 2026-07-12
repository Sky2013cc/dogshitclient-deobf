package com.sun.tools.internal.xjc.reader.gbind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/gbind/Element.class */
public abstract class Element extends Expression implements ElementSet {
    final Set<Element> foreEdges = new LinkedHashSet();
    final Set<Element> backEdges = new LinkedHashSet();
    Element prevPostOrder;
    private ConnectedComponent cc;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !Element.class.desiredAssertionStatus();
    }

    @Override // com.sun.tools.internal.xjc.reader.gbind.Expression
    ElementSet lastSet() {
        return this;
    }

    @Override // com.sun.tools.internal.xjc.reader.gbind.Expression
    boolean isNullable() {
        return false;
    }

    boolean isSource() {
        return false;
    }

    boolean isSink() {
        return false;
    }

    @Override // com.sun.tools.internal.xjc.reader.gbind.Expression
    void buildDAG(ElementSet incoming) {
        incoming.addNext(this);
    }

    @Override // com.sun.tools.internal.xjc.reader.gbind.ElementSet
    public void addNext(Element element) {
        this.foreEdges.add(element);
        element.backEdges.add(this);
    }

    @Override // com.sun.tools.internal.xjc.reader.gbind.ElementSet
    public boolean contains(ElementSet rhs) {
        return this == rhs || rhs == ElementSet.EMPTY_SET;
    }

    @Override // java.lang.Iterable
    public Iterator<Element> iterator() {
        return Collections.singleton(this).iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element assignDfsPostOrder(Element prev) {
        if (this.prevPostOrder != null) {
            return prev;
        }
        this.prevPostOrder = this;
        for (Element next : this.foreEdges) {
            prev = next.assignDfsPostOrder(prev);
        }
        this.prevPostOrder = prev;
        return this;
    }

    public void buildStronglyConnectedComponents(List<ConnectedComponent> ccs) {
        List<Element> visitedElements = new ArrayList<>();
        Element element = this;
        while (true) {
            Element cur = element;
            if (cur != cur.prevPostOrder && !visitedElements.contains(cur)) {
                visitedElements.add(cur);
                if (!cur.belongsToSCC()) {
                    ConnectedComponent cc = new ConnectedComponent();
                    ccs.add(cc);
                    cur.formConnectedComponent(cc);
                }
                element = cur.prevPostOrder;
            } else {
                return;
            }
        }
    }

    private boolean belongsToSCC() {
        return this.cc != null || isSource() || isSink();
    }

    private void formConnectedComponent(ConnectedComponent group) {
        if (belongsToSCC()) {
            return;
        }
        this.cc = group;
        group.add(this);
        for (Element prev : this.backEdges) {
            prev.formConnectedComponent(group);
        }
    }

    public boolean hasSelfLoop() {
        if ($assertionsDisabled || this.foreEdges.contains(this) == this.backEdges.contains(this)) {
            return this.foreEdges.contains(this);
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean checkCutSet(ConnectedComponent cc, Set<Element> visited) {
        if (!$assertionsDisabled && !belongsToSCC()) {
            throw new AssertionError();
        }
        if (isSink()) {
            return false;
        }
        if (!visited.add(this) || this.cc == cc) {
            return true;
        }
        for (Element next : this.foreEdges) {
            if (!next.checkCutSet(cc, visited)) {
                return false;
            }
        }
        return true;
    }
}
