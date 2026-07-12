package com.sun.xml.internal.xsom;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSUnionSimpleType.class */
public interface XSUnionSimpleType extends XSSimpleType, Iterable<XSSimpleType> {
    XSSimpleType getMember(int i);

    int getMemberSize();
}
