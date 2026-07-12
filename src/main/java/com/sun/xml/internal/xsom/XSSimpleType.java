package com.sun.xml.internal.xsom;

import com.sun.xml.internal.xsom.visitor.XSSimpleTypeFunction;
import com.sun.xml.internal.xsom.visitor.XSSimpleTypeVisitor;
import java.util.List;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSSimpleType.class */
public interface XSSimpleType extends XSType, XSContentType {
    XSSimpleType getSimpleBaseType();

    XSVariety getVariety();

    XSSimpleType getPrimitiveType();

    boolean isPrimitive();

    XSListSimpleType getBaseListType();

    XSUnionSimpleType getBaseUnionType();

    boolean isFinal(XSVariety xSVariety);

    @Override // com.sun.xml.internal.xsom.XSType
    XSSimpleType getRedefinedBy();

    XSFacet getFacet(String str);

    List<XSFacet> getFacets(String str);

    void visit(XSSimpleTypeVisitor xSSimpleTypeVisitor);

    <T> T apply(XSSimpleTypeFunction<T> xSSimpleTypeFunction);

    boolean isRestriction();

    boolean isList();

    boolean isUnion();

    XSRestrictionSimpleType asRestriction();

    XSListSimpleType asList();

    XSUnionSimpleType asUnion();
}
