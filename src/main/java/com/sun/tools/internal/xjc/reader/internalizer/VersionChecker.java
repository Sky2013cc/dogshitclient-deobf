package com.sun.tools.internal.xjc.reader.internalizer;

import com.sun.tools.internal.ws.wsdl.document.jaxws.JAXWSBindingsConstants;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.LocatorImpl;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/internalizer/VersionChecker.class */
public class VersionChecker extends XMLFilterImpl {
    private String version = null;
    private boolean seenRoot = false;
    private boolean seenBindings = false;
    private Locator locator;
    private Locator rootTagStart;
    private static final Set<String> VERSIONS = new HashSet(Arrays.asList("1.0", JAXWSBindingsConstants.JAXB_BINDING_VERSION, "2.1"));

    public VersionChecker(XMLReader parent) {
        setParent(parent);
    }

    public VersionChecker(ContentHandler handler, ErrorHandler eh, EntityResolver er) {
        setContentHandler(handler);
        if (eh != null) {
            setErrorHandler(eh);
        }
        if (er != null) {
            setEntityResolver(er);
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        super.startElement(namespaceURI, localName, qName, atts);
        if (!this.seenRoot) {
            this.seenRoot = true;
            this.rootTagStart = new LocatorImpl(this.locator);
            this.version = atts.getValue("http://java.sun.com/xml/ns/jaxb", "version");
            if (namespaceURI.equals("http://java.sun.com/xml/ns/jaxb")) {
                String version2 = atts.getValue("", "version");
                if (this.version != null && version2 != null) {
                    SAXParseException e = new SAXParseException(Messages.format("Internalizer.TwoVersionAttributes", new Object[0]), this.locator);
                    getErrorHandler().error(e);
                }
                if (this.version == null) {
                    this.version = version2;
                }
            }
        }
        if ("http://java.sun.com/xml/ns/jaxb".equals(namespaceURI)) {
            this.seenBindings = true;
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        super.endDocument();
        if (this.seenBindings && this.version == null) {
            SAXParseException e = new SAXParseException(Messages.format("Internalizer.VersionNotPresent", new Object[0]), this.rootTagStart);
            getErrorHandler().error(e);
        }
        if (this.version != null && !VERSIONS.contains(this.version)) {
            SAXParseException e2 = new SAXParseException(Messages.format("Internalizer.IncorrectVersion", new Object[0]), this.rootTagStart);
            getErrorHandler().error(e2);
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
        this.locator = locator;
    }
}
