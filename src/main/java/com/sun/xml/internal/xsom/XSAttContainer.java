package com.sun.xml.internal.xsom;

import java.util.Collection;
import java.util.Iterator;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSAttContainer.class */
public interface XSAttContainer extends XSDeclaration {
    XSWildcard getAttributeWildcard();

    XSAttributeUse getAttributeUse(String str, String str2);

    Iterator<? extends XSAttributeUse> iterateAttributeUses();

    Collection<? extends XSAttributeUse> getAttributeUses();

    XSAttributeUse getDeclaredAttributeUse(String str, String str2);

    Iterator<? extends XSAttributeUse> iterateDeclaredAttributeUses();

    Collection<? extends XSAttributeUse> getDeclaredAttributeUses();

    Iterator<? extends XSAttGroupDecl> iterateAttGroups();

    Collection<? extends XSAttGroupDecl> getAttGroups();
}
