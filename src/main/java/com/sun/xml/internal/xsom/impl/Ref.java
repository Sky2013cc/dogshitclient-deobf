package com.sun.xml.internal.xsom.impl;

import com.sun.xml.internal.xsom.XSAttGroupDecl;
import com.sun.xml.internal.xsom.XSAttributeDecl;
import com.sun.xml.internal.xsom.XSComplexType;
import com.sun.xml.internal.xsom.XSContentType;
import com.sun.xml.internal.xsom.XSElementDecl;
import com.sun.xml.internal.xsom.XSIdentityConstraint;
import com.sun.xml.internal.xsom.XSSimpleType;
import com.sun.xml.internal.xsom.XSTerm;
import com.sun.xml.internal.xsom.XSType;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/Ref.class */
public abstract class Ref {

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/Ref$AttGroup.class */
    public interface AttGroup {
        XSAttGroupDecl get();
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/Ref$Attribute.class */
    public interface Attribute {
        XSAttributeDecl getAttribute();
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/Ref$ComplexType.class */
    public interface ComplexType extends Type {
        @Override // com.sun.xml.internal.xsom.impl.Ref.Type
        XSComplexType getType();
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/Ref$ContentType.class */
    public interface ContentType {
        XSContentType getContentType();
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/Ref$Element.class */
    public interface Element extends Term {
        XSElementDecl get();
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/Ref$IdentityConstraint.class */
    public interface IdentityConstraint {
        XSIdentityConstraint get();
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/Ref$SimpleType.class */
    public interface SimpleType extends Type {
        @Override // com.sun.xml.internal.xsom.impl.Ref.Type
        XSSimpleType getType();
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/Ref$Term.class */
    public interface Term {
        XSTerm getTerm();
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/Ref$Type.class */
    public interface Type {
        XSType getType();
    }
}
