package com.sun.tools.internal.xjc.util;

import com.sun.xml.internal.xsom.XmlString;
import java.util.Collections;
import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;

/* loaded from: target.jar:com/sun/tools/internal/xjc/util/NamespaceContextAdapter.class */
public final class NamespaceContextAdapter implements NamespaceContext {
    private XmlString xstr;

    public NamespaceContextAdapter(XmlString xstr) {
        this.xstr = xstr;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getNamespaceURI(String prefix) {
        return this.xstr.resolvePrefix(prefix);
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getPrefix(String namespaceURI) {
        return null;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public Iterator getPrefixes(String namespaceURI) {
        return Collections.EMPTY_LIST.iterator();
    }
}
