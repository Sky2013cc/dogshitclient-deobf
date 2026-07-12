package com.sun.tools.internal.ws.wsdl.parser;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/parser/NamespaceContextImpl.class */
public class NamespaceContextImpl implements NamespaceContext {
    private final Element e;

    public NamespaceContextImpl(Element e) {
        this.e = e;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getNamespaceURI(String prefix) {
        int type;
        String namespace = null;
        if (prefix.equals("xml")) {
            namespace = "http://www.w3.org/XML/1998/namespace";
        } else {
            for (Node parent = this.e; null != parent && null == namespace && ((type = parent.getNodeType()) == 1 || type == 5); parent = parent.getParentNode()) {
                if (type == 1) {
                    if (parent.getNodeName().indexOf(prefix + ':') == 0) {
                        return parent.getNamespaceURI();
                    }
                    NamedNodeMap nnm = parent.getAttributes();
                    int i = 0;
                    while (true) {
                        if (i >= nnm.getLength()) {
                            break;
                        }
                        Node attr = nnm.item(i);
                        String aname = attr.getNodeName();
                        boolean isPrefix = aname.startsWith("xmlns:");
                        if (isPrefix || aname.equals(Constants.XMLNS)) {
                            int index = aname.indexOf(58);
                            String p = isPrefix ? aname.substring(index + 1) : "";
                            if (p.equals(prefix)) {
                                namespace = attr.getNodeValue();
                                break;
                            }
                        }
                        i++;
                    }
                }
            }
        }
        return namespace;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getPrefix(String namespaceURI) {
        throw new UnsupportedOperationException();
    }

    @Override // javax.xml.namespace.NamespaceContext
    public Iterator getPrefixes(String namespaceURI) {
        throw new UnsupportedOperationException();
    }
}
