package com.sun.xml.internal.xsom;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSDeclaration.class */
public interface XSDeclaration extends XSComponent {
    String getTargetNamespace();

    String getName();

    boolean isAnonymous();

    boolean isGlobal();

    boolean isLocal();
}
