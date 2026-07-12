package com.sun.tools.internal.xjc.reader.internalizer;

import com.sun.istack.internal.SAXParseException2;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/internalizer/AbstractReferenceFinderImpl.class */
public abstract class AbstractReferenceFinderImpl extends XMLFilterImpl {
    protected final DOMForest parent;
    private Locator locator;

    protected abstract String findExternalResource(String str, String str2, Attributes attributes);

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
            String lsi = this.locator.getSystemId();
            URI relRefURI = new URI(relativeRef);
            if (relRefURI.isAbsolute()) {
                ref = relativeRef;
            } else if (lsi.startsWith("jar:")) {
                int bangIdx = lsi.indexOf(33);
                if (bangIdx > 0) {
                    ref = lsi.substring(0, bangIdx + 1) + new URI(lsi.substring(bangIdx + 1)).resolve(new URI(relativeRef)).toString();
                } else {
                    ref = relativeRef;
                }
            } else {
                ref = new URI(lsi).resolve(new URI(relativeRef)).toString();
            }
            if (this.parent != null) {
                this.parent.parse(ref, false);
            }
        } catch (IOException e) {
            SAXParseException2 sAXParseException2 = new SAXParseException2(Messages.format("AbstractReferenceFinderImpl.UnableToParse", relativeRef, e.getMessage()), this.locator, e);
            fatalError(sAXParseException2);
            throw sAXParseException2;
        } catch (URISyntaxException e2) {
            String msg = e2.getMessage();
            if (new File(relativeRef).exists()) {
                msg = Messages.format("ERR_FILENAME_IS_NOT_URI", new Object[0]) + ' ' + msg;
            }
            SAXParseException2 sAXParseException22 = new SAXParseException2(Messages.format("AbstractReferenceFinderImpl.UnableToParse", relativeRef, msg), this.locator, e2);
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
