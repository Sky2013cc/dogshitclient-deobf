package com.sun.xml.internal.rngom.digested;

/* loaded from: target.jar:com/sun/xml/internal/rngom/digested/DOptionalPattern.class */
public class DOptionalPattern extends DUnaryPattern {
    @Override // com.sun.xml.internal.rngom.digested.DPattern
    public boolean isNullable() {
        return true;
    }

    @Override // com.sun.xml.internal.rngom.digested.DPattern
    public Object accept(DPatternVisitor visitor) {
        return visitor.onOptional(this);
    }
}
