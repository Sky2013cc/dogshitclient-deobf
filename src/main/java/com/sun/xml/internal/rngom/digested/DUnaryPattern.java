package com.sun.xml.internal.rngom.digested;

/* loaded from: target.jar:com/sun/xml/internal/rngom/digested/DUnaryPattern.class */
public abstract class DUnaryPattern extends DPattern {
    private DPattern child;

    public DPattern getChild() {
        return this.child;
    }

    public void setChild(DPattern child) {
        this.child = child;
    }
}
