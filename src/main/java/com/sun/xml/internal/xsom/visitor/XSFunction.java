package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSAnnotation;
import com.sun.xml.internal.xsom.XSAttGroupDecl;
import com.sun.xml.internal.xsom.XSAttributeDecl;
import com.sun.xml.internal.xsom.XSAttributeUse;
import com.sun.xml.internal.xsom.XSComplexType;
import com.sun.xml.internal.xsom.XSFacet;
import com.sun.xml.internal.xsom.XSIdentityConstraint;
import com.sun.xml.internal.xsom.XSNotation;
import com.sun.xml.internal.xsom.XSSchema;
import com.sun.xml.internal.xsom.XSXPath;

/* loaded from: target.jar:com/sun/xml/internal/xsom/visitor/XSFunction.class */
public interface XSFunction<T> extends XSContentTypeFunction<T>, XSTermFunction<T> {
    T annotation(XSAnnotation xSAnnotation);

    T attGroupDecl(XSAttGroupDecl xSAttGroupDecl);

    T attributeDecl(XSAttributeDecl xSAttributeDecl);

    T attributeUse(XSAttributeUse xSAttributeUse);

    T complexType(XSComplexType xSComplexType);

    T schema(XSSchema xSSchema);

    T facet(XSFacet xSFacet);

    T notation(XSNotation xSNotation);

    T identityConstraint(XSIdentityConstraint xSIdentityConstraint);

    T xpath(XSXPath xSXPath);
}
