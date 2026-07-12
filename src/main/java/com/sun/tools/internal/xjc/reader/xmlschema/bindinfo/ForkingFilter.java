package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/bindinfo/ForkingFilter.class */
public class ForkingFilter extends XMLFilterImpl {
    private ContentHandler side;
    private int depth;
    private final ArrayList<String> namespaces = new ArrayList<>();
    private Locator loc;

    public ForkingFilter() {
    }

    public ForkingFilter(ContentHandler next) {
        setContentHandler(next);
    }

    public ContentHandler getSideHandler() {
        return this.side;
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
        this.loc = locator;
    }

    public Locator getDocumentLocator() {
        return this.loc;
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        reset();
        super.startDocument();
    }

    private void reset() {
        this.namespaces.clear();
        this.side = null;
        this.depth = 0;
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        this.loc = null;
        reset();
        super.endDocument();
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        if ("http://www.w3.org/XML/1998/namespace".equals(uri)) {
            return;
        }
        if (this.side != null) {
            this.side.startPrefixMapping(prefix, uri);
        }
        this.namespaces.add(prefix);
        this.namespaces.add(uri);
        super.startPrefixMapping(prefix, uri);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endPrefixMapping(String prefix) throws SAXException {
        if ("xml".equals(prefix)) {
            return;
        }
        if (this.side != null) {
            this.side.endPrefixMapping(prefix);
        }
        super.endPrefixMapping(prefix);
        this.namespaces.remove(this.namespaces.size() - 1);
        this.namespaces.remove(this.namespaces.size() - 1);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (this.side != null) {
            this.side.startElement(uri, localName, qName, atts);
            this.depth++;
        }
        super.startElement(uri, localName, qName, atts);
    }

    public void startForking(String uri, String localName, String qName, Attributes atts, ContentHandler side) throws SAXException {
        if (this.side != null) {
            throw new IllegalStateException();
        }
        this.side = side;
        this.depth = 1;
        side.setDocumentLocator(this.loc);
        side.startDocument();
        for (int i = 0; i < this.namespaces.size(); i += 2) {
            side.startPrefixMapping(this.namespaces.get(i), this.namespaces.get(i + 1));
        }
        side.startElement(uri, localName, qName, atts);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (this.side != null) {
            this.side.endElement(uri, localName, qName);
            this.depth--;
            if (this.depth == 0) {
                for (int i = this.namespaces.size() - 2; i >= 0; i -= 2) {
                    this.side.endPrefixMapping(this.namespaces.get(i));
                }
                this.side.endDocument();
                this.side = null;
            }
        }
        super.endElement(uri, localName, qName);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.side != null) {
            this.side.characters(ch, start, length);
        }
        super.characters(ch, start, length);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        if (this.side != null) {
            this.side.ignorableWhitespace(ch, start, length);
        }
        super.ignorableWhitespace(ch, start, length);
    }
}
