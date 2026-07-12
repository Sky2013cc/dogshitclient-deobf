package com.sun.tools.internal.xjc.reader.dtd.bindinfo;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/dtd/bindinfo/DOMUtil.class */
public final class DOMUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final String getAttribute(Element e, String attName) {
        if (e.getAttributeNode(attName) == null) {
            return null;
        }
        return e.getAttribute(attName);
    }

    public static String getAttribute(Element e, String nsUri, String local) {
        if (e.getAttributeNodeNS(nsUri, local) == null) {
            return null;
        }
        return e.getAttributeNS(nsUri, local);
    }

    public static Element getElement(Element e, String nsUri, String localName) {
        NodeList l = e.getChildNodes();
        for (int i = 0; i < l.getLength(); i++) {
            Node n = l.item(i);
            if (n.getNodeType() == 1) {
                Element r = (Element) n;
                if (equals(r.getLocalName(), localName) && equals(fixNull(r.getNamespaceURI()), nsUri)) {
                    return r;
                }
            }
        }
        return null;
    }

    private static boolean equals(String a, String b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }

    private static String fixNull(String s) {
        return s == null ? "" : s;
    }

    public static Element getElement(Element e, String localName) {
        return getElement(e, "", localName);
    }

    public static List<Element> getChildElements(Element e) {
        List<Element> r = new ArrayList<>();
        NodeList l = e.getChildNodes();
        for (int i = 0; i < l.getLength(); i++) {
            Node n = l.item(i);
            if (n.getNodeType() == 1) {
                r.add((Element) n);
            }
        }
        return r;
    }

    public static List<Element> getChildElements(Element e, String localName) {
        List<Element> r = new ArrayList<>();
        NodeList l = e.getChildNodes();
        for (int i = 0; i < l.getLength(); i++) {
            Node n = l.item(i);
            if (n.getNodeType() == 1) {
                Element c = (Element) n;
                if (c.getLocalName().equals(localName)) {
                    r.add(c);
                }
            }
        }
        return r;
    }
}
