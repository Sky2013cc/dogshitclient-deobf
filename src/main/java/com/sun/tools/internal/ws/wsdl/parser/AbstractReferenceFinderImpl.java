package com.sun.tools.internal.ws.wsdl.parser;

import com.sun.istack.internal.SAXParseException2;
import com.sun.tools.internal.ws.resources.WsdlMessages;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/parser/AbstractReferenceFinderImpl.class */
public abstract class AbstractReferenceFinderImpl extends XMLFilterImpl {
    protected final DOMForest parent;
    private Locator locator;
    static final /* synthetic */ boolean $assertionsDisabled;

    protected abstract String findExternalResource(String str, String str2, Attributes attributes);

    static {
        $assertionsDisabled = !AbstractReferenceFinderImpl.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractReferenceFinderImpl(DOMForest _parent) {
        this.parent = _parent;
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        String ref;
        super.startElement(namespaceURI, localName, qName, atts);
        String relativeRef = findExternalResource(namespaceURI, localName, atts);
        if (relativeRef == null) {
            return;
        }
        try {
            if (!$assertionsDisabled && this.locator == null) {
                throw new AssertionError();
            }
            String lsi = this.locator.getSystemId();
            if (lsi.startsWith("jar:")) {
                int bangIdx = lsi.indexOf(33);
                if (bangIdx > 0) {
                    ref = new URL(new URL(lsi), relativeRef).toString();
                } else {
                    ref = relativeRef;
                }
            } else {
                ref = new URI(lsi).resolve(new URI(relativeRef)).toString();
            }
            this.parent.parse(ref, false);
        } catch (IOException e) {
            SAXParseException2 sAXParseException2 = new SAXParseException2(WsdlMessages.ABSTRACT_REFERENCE_FINDER_IMPL_UNABLE_TO_PARSE(relativeRef, e.getMessage()), this.locator, e);
            fatalError(sAXParseException2);
            throw sAXParseException2;
        } catch (URISyntaxException e2) {
            SAXParseException2 sAXParseException22 = new SAXParseException2(WsdlMessages.ABSTRACT_REFERENCE_FINDER_IMPL_UNABLE_TO_PARSE(relativeRef, e2.getMessage()), this.locator, e2);
            fatalError(sAXParseException22);
            throw sAXParseException22;
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
        this.locator = locator;
    }
}
