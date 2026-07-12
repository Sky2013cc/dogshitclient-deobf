package com.sun.tools.internal.xjc.reader.dtd.bindinfo;

import org.w3c.dom.Element;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/dtd/bindinfo/DOMLocator.class */
class DOMLocator {
    private static final String locationNamespace = "http://www.sun.com/xmlns/jaxb/dom-location";
    private static final String systemId = "systemid";
    private static final String column = "column";
    private static final String line = "line";

    DOMLocator() {
    }

    public static void setLocationInfo(Element e, Locator loc) {
        e.setAttributeNS(locationNamespace, "loc:systemid", loc.getSystemId());
        e.setAttributeNS(locationNamespace, "loc:column", Integer.toString(loc.getLineNumber()));
        e.setAttributeNS(locationNamespace, "loc:line", Integer.toString(loc.getColumnNumber()));
    }

    public static Locator getLocationInfo(final Element e) {
        if (DOMUtil.getAttribute(e, locationNamespace, systemId) == null) {
            return null;
        }
        return new Locator() { // from class: com.sun.tools.internal.xjc.reader.dtd.bindinfo.DOMLocator.1
            @Override // org.xml.sax.Locator
            public int getLineNumber() {
                return Integer.parseInt(DOMUtil.getAttribute(e, DOMLocator.locationNamespace, DOMLocator.line));
            }

            @Override // org.xml.sax.Locator
            public int getColumnNumber() {
                return Integer.parseInt(DOMUtil.getAttribute(e, DOMLocator.locationNamespace, DOMLocator.column));
            }

            @Override // org.xml.sax.Locator
            public String getSystemId() {
                return DOMUtil.getAttribute(e, DOMLocator.locationNamespace, DOMLocator.systemId);
            }

            @Override // org.xml.sax.Locator
            public String getPublicId() {
                return null;
            }
        };
    }
}
