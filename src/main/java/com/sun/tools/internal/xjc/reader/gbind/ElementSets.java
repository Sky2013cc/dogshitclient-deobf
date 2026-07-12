package com.sun.tools.internal.xjc.reader.gbind;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/gbind/ElementSets.class */
public final class ElementSets {
    public static ElementSet union(ElementSet lhs, ElementSet rhs) {
        if (lhs.contains(rhs)) {
            return lhs;
        }
        if (lhs == ElementSet.EMPTY_SET) {
            return rhs;
        }
        if (rhs == ElementSet.EMPTY_SET) {
            return lhs;
        }
        return new MultiValueSet(lhs, rhs);
    }

    /* loaded from: target.jar:com/sun/tools/internal/xjc/reader/gbind/ElementSets$MultiValueSet.class */
    private static final class MultiValueSet extends LinkedHashSet<Element> implements ElementSet {
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !ElementSets.class.desiredAssertionStatus();
        }

        public MultiValueSet(ElementSet lhs, ElementSet rhs) {
            addAll(lhs);
            addAll(rhs);
            if (!$assertionsDisabled && size() <= 1) {
                throw new AssertionError();
            }
        }

        private void addAll(ElementSet lhs) {
            if (lhs instanceof MultiValueSet) {
                super.addAll((Collection) lhs);
                return;
            }
            for (Element e : lhs) {
                add(e);
            }
        }

        @Override // com.sun.tools.internal.xjc.reader.gbind.ElementSet
        public boolean contains(ElementSet rhs) {
            return super.contains((Object) rhs) || rhs == ElementSet.EMPTY_SET;
        }

        @Override // com.sun.tools.internal.xjc.reader.gbind.ElementSet
        public void addNext(Element element) {
            Iterator it = iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                e.addNext(element);
            }
        }
    }
}
