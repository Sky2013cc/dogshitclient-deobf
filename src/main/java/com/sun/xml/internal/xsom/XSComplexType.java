package com.sun.xml.internal.xsom;

import java.util.List;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSComplexType.class */
public interface XSComplexType extends XSType, XSAttContainer {
    boolean isAbstract();

    boolean isFinal(int i);

    boolean isSubstitutionProhibited(int i);

    XSElementDecl getScope();

    XSContentType getContentType();

    XSContentType getExplicitContent();

    boolean isMixed();

    @Override // com.sun.xml.internal.xsom.XSType
    XSComplexType getRedefinedBy();

    List<XSComplexType> getSubtypes();

    List<XSElementDecl> getElementDecls();
}
