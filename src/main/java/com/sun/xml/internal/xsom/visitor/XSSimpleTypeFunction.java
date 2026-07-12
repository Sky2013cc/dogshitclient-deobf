package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSListSimpleType;
import com.sun.xml.internal.xsom.XSRestrictionSimpleType;
import com.sun.xml.internal.xsom.XSUnionSimpleType;

/* loaded from: target.jar:com/sun/xml/internal/xsom/visitor/XSSimpleTypeFunction.class */
public interface XSSimpleTypeFunction<T> {
    T listSimpleType(XSListSimpleType xSListSimpleType);

    T unionSimpleType(XSUnionSimpleType xSUnionSimpleType);

    T restrictionSimpleType(XSRestrictionSimpleType xSRestrictionSimpleType);
}
