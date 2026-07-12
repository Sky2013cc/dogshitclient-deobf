package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSListSimpleType;
import com.sun.xml.internal.xsom.XSRestrictionSimpleType;
import com.sun.xml.internal.xsom.XSUnionSimpleType;

/* loaded from: target.jar:com/sun/xml/internal/xsom/visitor/XSSimpleTypeVisitor.class */
public interface XSSimpleTypeVisitor {
    void listSimpleType(XSListSimpleType xSListSimpleType);

    void unionSimpleType(XSUnionSimpleType xSUnionSimpleType);

    void restrictionSimpleType(XSRestrictionSimpleType xSRestrictionSimpleType);
}
