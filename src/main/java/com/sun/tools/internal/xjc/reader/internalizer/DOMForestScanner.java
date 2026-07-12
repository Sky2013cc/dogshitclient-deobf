package com.sun.tools.internal.xjc.reader.internalizer;

import com.sun.xml.internal.bind.unmarshaller.DOMScanner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/internalizer/DOMForestScanner.class */
public class DOMForestScanner {
    private final DOMForest forest;

    public DOMForestScanner(DOMForest _forest) {
        this.forest = _forest;
    }

    public void scan(Element e, ContentHandler contentHandler) throws SAXException {
        DOMScanner scanner = new DOMScanner();
        LocationResolver resolver = new LocationResolver(scanner);
        resolver.setContentHandler(contentHandler);
        scanner.setContentHandler(resolver);
        scanner.scan(e);
    }

    public void scan(Document d, ContentHandler contentHandler) throws SAXException {
        scan(d.getDocumentElement(), contentHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/internal/xjc/reader/internalizer/DOMForestScanner$LocationResolver.class */
    public class LocationResolver extends XMLFilterImpl implements Locator {
        private final DOMScanner parent;
        private boolean inStart = false;

        LocationResolver(DOMScanner _parent) {
            this.parent = _parent;
        }

        @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
        public void setDocumentLocator(Locator locator) {
            super.setDocumentLocator(this);
        }

        @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
        public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
            this.inStart = false;
            super.endElement(namespaceURI, localName, qName);
        }

        @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
        public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
            this.inStart = true;
            super.startElement(namespaceURI, localName, qName, atts);
        }

        private Locator findLocator() {
            Node n = this.parent.getCurrentLocation();
            if (n instanceof Element) {
                Element e = (Element) n;
                return this.inStart ? DOMForestScanner.this.forest.locatorTable.getStartLocation(e) : DOMForestScanner.this.forest.locatorTable.getEndLocation(e);
            }
            return null;
        }

        @Override // org.xml.sax.Locator
        public int getColumnNumber() {
            Locator l = findLocator();
            if (l != null) {
                return l.getColumnNumber();
            }
            return -1;
        }

        @Override // org.xml.sax.Locator
        public int getLineNumber() {
            Locator l = findLocator();
            if (l != null) {
                return l.getLineNumber();
            }
            return -1;
        }

        @Override // org.xml.sax.Locator
        public String getPublicId() {
            Locator l = findLocator();
            if (l != null) {
                return l.getPublicId();
            }
            return null;
        }

        @Override // org.xml.sax.Locator
        public String getSystemId() {
            Locator l = findLocator();
            if (l != null) {
                return l.getSystemId();
            }
            return null;
        }
    }
}
