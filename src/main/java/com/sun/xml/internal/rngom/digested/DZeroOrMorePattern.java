package com.sun.xml.internal.rngom.digested;

/* loaded from: target.jar:com/sun/xml/internal/rngom/digested/DZeroOrMorePattern.class */
public class DZeroOrMorePattern extends DUnaryPattern {
    @Override // com.sun.xml.internal.rngom.digested.DPattern
    public boolean isNullable() {
        return true;
    }

    @Override // com.sun.xml.internal.rngom.digested.DPattern
    public Object accept(DPatternVisitor visitor) {
        return visitor.onZeroOrMore(this);
    }
}
