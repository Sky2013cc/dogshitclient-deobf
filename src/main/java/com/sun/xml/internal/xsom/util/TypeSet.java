package com.sun.xml.internal.xsom.util;

import com.sun.xml.internal.xsom.XSType;

/* loaded from: target.jar:com/sun/xml/internal/xsom/util/TypeSet.class */
public abstract class TypeSet {
    public abstract boolean contains(XSType xSType);

    public static TypeSet intersection(TypeSet a, final TypeSet b) {
        return new TypeSet() { // from class: com.sun.xml.internal.xsom.util.TypeSet.1
            @Override // com.sun.xml.internal.xsom.util.TypeSet
            public boolean contains(XSType type) {
                return TypeSet.this.contains(type) && b.contains(type);
            }
        };
    }

    public static TypeSet union(TypeSet a, final TypeSet b) {
        return new TypeSet() { // from class: com.sun.xml.internal.xsom.util.TypeSet.2
            @Override // com.sun.xml.internal.xsom.util.TypeSet
            public boolean contains(XSType type) {
                return TypeSet.this.contains(type) || b.contains(type);
            }
        };
    }
}
