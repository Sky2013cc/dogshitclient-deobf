package com.sun.tools.internal.ws.util.xml;

import com.sun.tools.internal.ws.util.WSDLParseException;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;

/* loaded from: target.jar:com/sun/tools/internal/ws/util/xml/XmlUtil.class */
public class XmlUtil extends com.sun.xml.internal.ws.util.xml.XmlUtil {
    public static boolean matchesTagNS(Element e, String tag, String nsURI) {
        try {
            if (e.getLocalName().equals(tag)) {
                if (e.getNamespaceURI().equals(nsURI)) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e2) {
            throw new WSDLParseException("null.namespace.found", e.getLocalName());
        }
    }

    public static boolean matchesTagNS(Element e, QName name) {
        try {
            if (e.getLocalName().equals(name.getLocalPart())) {
                if (e.getNamespaceURI().equals(name.getNamespaceURI())) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e2) {
            throw new WSDLParseException("null.namespace.found", e.getLocalName());
        }
    }
}
