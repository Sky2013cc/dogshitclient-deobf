package com.sun.xml.internal.xsom;

import java.util.List;
import java.util.Set;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSElementDecl.class */
public interface XSElementDecl extends XSDeclaration, XSTerm {
    XSType getType();

    boolean isNillable();

    XSElementDecl getSubstAffiliation();

    List<XSIdentityConstraint> getIdentityConstraints();

    boolean isSubstitutionExcluded(int i);

    boolean isSubstitutionDisallowed(int i);

    boolean isAbstract();

    XSElementDecl[] listSubstitutables();

    Set<? extends XSElementDecl> getSubstitutables();

    boolean canBeSubstitutedBy(XSElementDecl xSElementDecl);

    XmlString getDefaultValue();

    XmlString getFixedValue();

    Boolean getForm();
}
