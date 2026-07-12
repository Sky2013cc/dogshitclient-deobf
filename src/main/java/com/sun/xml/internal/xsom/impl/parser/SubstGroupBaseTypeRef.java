package com.sun.xml.internal.xsom.impl.parser;

import com.sun.xml.internal.xsom.XSType;
import com.sun.xml.internal.xsom.impl.Ref;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/parser/SubstGroupBaseTypeRef.class */
public class SubstGroupBaseTypeRef implements Ref.Type {
    private final Ref.Element e;

    public SubstGroupBaseTypeRef(Ref.Element _e) {
        this.e = _e;
    }

    @Override // com.sun.xml.internal.xsom.impl.Ref.Type
    public XSType getType() {
        return this.e.get().getType();
    }
}
