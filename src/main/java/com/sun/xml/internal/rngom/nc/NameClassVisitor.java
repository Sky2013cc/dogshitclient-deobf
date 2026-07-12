package com.sun.xml.internal.rngom.nc;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/xml/internal/rngom/nc/NameClassVisitor.class */
public interface NameClassVisitor<V> {
    V visitChoice(NameClass nameClass, NameClass nameClass2);

    V visitNsName(String str);

    V visitNsNameExcept(String str, NameClass nameClass);

    V visitAnyName();

    V visitAnyNameExcept(NameClass nameClass);

    V visitName(QName qName);

    V visitNull();
}
