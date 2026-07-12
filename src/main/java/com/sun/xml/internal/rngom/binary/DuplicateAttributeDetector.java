package com.sun.xml.internal.rngom.binary;

import com.sun.xml.internal.rngom.nc.NameClass;
import java.util.ArrayList;
import java.util.List;

/* loaded from: target.jar:com/sun/xml/internal/rngom/binary/DuplicateAttributeDetector.class */
class DuplicateAttributeDetector {
    private List nameClasses = new ArrayList();
    private Alternative alternatives = null;

    /* loaded from: target.jar:com/sun/xml/internal/rngom/binary/DuplicateAttributeDetector$Alternative.class */
    private static class Alternative {
        private int startIndex;
        private int endIndex;
        private Alternative parent;

        private Alternative(int startIndex, Alternative parent) {
            this.startIndex = startIndex;
            this.endIndex = startIndex;
            this.parent = parent;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean addAttribute(NameClass nc) {
        int lim = this.nameClasses.size();
        Alternative alternative = this.alternatives;
        while (true) {
            Alternative a = alternative;
            if (a != null) {
                for (int i = a.endIndex; i < lim; i++) {
                    if (nc.hasOverlapWith((NameClass) this.nameClasses.get(i))) {
                        return false;
                    }
                }
                lim = a.startIndex;
                alternative = a.parent;
            } else {
                for (int i2 = 0; i2 < lim; i2++) {
                    if (nc.hasOverlapWith((NameClass) this.nameClasses.get(i2))) {
                        return false;
                    }
                }
                this.nameClasses.add(nc);
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startChoice() {
        this.alternatives = new Alternative(this.nameClasses.size(), this.alternatives);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void alternative() {
        this.alternatives.endIndex = this.nameClasses.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void endChoice() {
        this.alternatives = this.alternatives.parent;
    }
}
