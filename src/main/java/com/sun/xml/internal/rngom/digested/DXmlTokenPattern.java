package com.sun.xml.internal.rngom.digested;

import com.sun.xml.internal.rngom.nc.NameClass;

/* loaded from: target.jar:com/sun/xml/internal/rngom/digested/DXmlTokenPattern.class */
public abstract class DXmlTokenPattern extends DUnaryPattern {
    private final NameClass name;

    public DXmlTokenPattern(NameClass name) {
        this.name = name;
    }

    public NameClass getName() {
        return this.name;
    }

    @Override // com.sun.xml.internal.rngom.digested.DPattern
    public final boolean isNullable() {
        return false;
    }
}
