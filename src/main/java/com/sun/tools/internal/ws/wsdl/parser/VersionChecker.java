package com.sun.tools.internal.ws.wsdl.parser;

import com.sun.tools.internal.ws.resources.WsdlMessages;
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

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/parser/VersionChecker.class */
public class VersionChecker extends XMLFilterImpl {
    private String version = null;
    private boolean seenRoot = false;
    private boolean seenBindings = false;
    private Locator locator;
    private Locator rootTagStart;
    private static final Set<String> VERSIONS = new HashSet(Arrays.asList(JAXWSBindingsConstants.JAXB_BINDING_VERSION, "2.1"));

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
            this.version = atts.getValue(JAXWSBindingsConstants.NS_JAXWS_BINDINGS, "version");
            if (namespaceURI.equals(JAXWSBindingsConstants.NS_JAXWS_BINDINGS)) {
                String version2 = atts.getValue("", "version");
                if (this.version != null && version2 != null) {
                    SAXParseException e = new SAXParseException(WsdlMessages.INTERNALIZER_TWO_VERSION_ATTRIBUTES(), this.locator);
                    getErrorHandler().error(e);
                }
                if (this.version == null) {
                    this.version = version2 != null ? version2 : JAXWSBindingsConstants.JAXB_BINDING_VERSION;
                }
            }
        }
        if (JAXWSBindingsConstants.NS_JAXWS_BINDINGS.equals(namespaceURI)) {
            this.seenBindings = true;
            if (this.version == null) {
                this.version = JAXWSBindingsConstants.JAXB_BINDING_VERSION;
            }
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        super.endDocument();
        if (this.seenBindings && this.version == null) {
            SAXParseException e = new SAXParseException(WsdlMessages.INTERNALIZER_VERSION_NOT_PRESENT(), this.rootTagStart);
            getErrorHandler().error(e);
        }
        if (this.version != null && !VERSIONS.contains(this.version)) {
            SAXParseException e2 = new SAXParseException(WsdlMessages.INTERNALIZER_INCORRECT_VERSION(), this.rootTagStart);
            getErrorHandler().error(e2);
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
        this.locator = locator;
    }
}
