package com.sun.xml.internal.rngom.digested;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/xml/internal/rngom/digested/DAnnotation.class */
public class DAnnotation {
    static final DAnnotation EMPTY = new DAnnotation();
    final Map<QName, Attribute> attributes = new HashMap();
    final List<Element> contents = new ArrayList();

    /* loaded from: target.jar:com/sun/xml/internal/rngom/digested/DAnnotation$Attribute.class */
    public static class Attribute {
        private final String ns;
        private final String localName;
        private final String prefix;
        private String value;
        private Locator loc;

        public Attribute(String ns, String localName, String prefix) {
            this.ns = ns;
            this.localName = localName;
            this.prefix = prefix;
        }

        public Attribute(String ns, String localName, String prefix, String value, Locator loc) {
            this.ns = ns;
            this.localName = localName;
            this.prefix = prefix;
            this.value = value;
            this.loc = loc;
        }

        public String getNs() {
            return this.ns;
        }

        public String getLocalName() {
            return this.localName;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public String getValue() {
            return this.value;
        }

        public Locator getLoc() {
            return this.loc;
        }
    }

    public Attribute getAttribute(String nsUri, String localName) {
        return getAttribute(new QName(nsUri, localName));
    }

    public Attribute getAttribute(QName n) {
        return this.attributes.get(n);
    }

    public Map<QName, Attribute> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    public List<Element> getChildren() {
        return Collections.unmodifiableList(this.contents);
    }
}
