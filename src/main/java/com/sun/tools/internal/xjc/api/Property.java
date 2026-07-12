package com.sun.tools.internal.xjc.api;

import com.sun.codemodel.internal.JType;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/xjc/api/Property.class */
public interface Property {
    String name();

    JType type();

    QName elementName();

    QName rawName();
}
