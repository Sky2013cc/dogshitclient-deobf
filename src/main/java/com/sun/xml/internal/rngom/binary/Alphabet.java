package com.sun.xml.internal.rngom.binary;

import com.sun.xml.internal.rngom.nc.ChoiceNameClass;
import com.sun.xml.internal.rngom.nc.NameClass;

/* loaded from: target.jar:com/sun/xml/internal/rngom/binary/Alphabet.class */
class Alphabet {
    private NameClass nameClass;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEmpty() {
        return this.nameClass == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addElement(NameClass nc) {
        if (this.nameClass == null) {
            this.nameClass = nc;
        } else if (nc != null) {
            this.nameClass = new ChoiceNameClass(this.nameClass, nc);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAlphabet(Alphabet a) {
        addElement(a.nameClass);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkOverlap(Alphabet a) throws RestrictionViolationException {
        if (this.nameClass != null && a.nameClass != null && this.nameClass.hasOverlapWith(a.nameClass)) {
            throw new RestrictionViolationException("interleave_element_overlap");
        }
    }
}
