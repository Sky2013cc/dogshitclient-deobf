package com.sun.tools.internal.ws.wsdl.framework;

import javax.xml.namespace.QName;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/framework/Elemental.class */
public interface Elemental {
    QName getElementName();

    Locator getLocator();
}
