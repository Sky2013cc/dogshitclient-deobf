package com.sun.xml.internal.xsom.util;

import com.sun.xml.internal.xsom.XSType;

/* loaded from: target.jar:com/sun/xml/internal/xsom/util/TypeClosure.class */
public class TypeClosure extends TypeSet {
    private final TypeSet typeSet;

    public TypeClosure(TypeSet typeSet) {
        this.typeSet = typeSet;
    }

    @Override // com.sun.xml.internal.xsom.util.TypeSet
    public boolean contains(XSType type) {
        if (this.typeSet.contains(type)) {
            return true;
        }
        XSType baseType = type.getBaseType();
        if (baseType == null) {
            return false;
        }
        return contains(baseType);
    }
}
