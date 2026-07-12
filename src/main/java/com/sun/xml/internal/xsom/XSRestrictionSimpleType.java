package com.sun.xml.internal.xsom;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSRestrictionSimpleType.class */
public interface XSRestrictionSimpleType extends XSSimpleType {
    Iterator<XSFacet> iterateDeclaredFacets();

    Collection<? extends XSFacet> getDeclaredFacets();

    XSFacet getDeclaredFacet(String str);

    List<XSFacet> getDeclaredFacets(String str);
}
