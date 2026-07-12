package com.sun.tools.internal.xjc.reader.gbind;

import java.util.Collections;
import java.util.Iterator;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/gbind/ElementSet.class */
interface ElementSet extends Iterable<Element> {
    public static final ElementSet EMPTY_SET = new ElementSet() { // from class: com.sun.tools.internal.xjc.reader.gbind.ElementSet.1
        @Override // com.sun.tools.internal.xjc.reader.gbind.ElementSet
        public void addNext(Element element) {
        }

        @Override // com.sun.tools.internal.xjc.reader.gbind.ElementSet
        public boolean contains(ElementSet element) {
            return this == element;
        }

        @Override // java.lang.Iterable
        public Iterator<Element> iterator() {
            return Collections.emptySet().iterator();
        }
    };

    void addNext(Element element);

    boolean contains(ElementSet elementSet);
}
