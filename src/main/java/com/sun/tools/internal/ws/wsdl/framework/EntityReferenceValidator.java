package com.sun.tools.internal.ws.wsdl.framework;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/framework/EntityReferenceValidator.class */
public interface EntityReferenceValidator {
    boolean isValid(Kind kind, QName qName);
}
