package com.sun.xml.internal.xsom.util;

import com.sun.xml.internal.xsom.XSType;
import java.util.Set;

/* loaded from: target.jar:com/sun/xml/internal/xsom/util/SimpleTypeSet.class */
public class SimpleTypeSet extends TypeSet {
    private final Set typeSet;

    public SimpleTypeSet(Set s) {
        this.typeSet = s;
    }

    @Override // com.sun.xml.internal.xsom.util.TypeSet
    public boolean contains(XSType type) {
        return this.typeSet.contains(type);
    }
}
