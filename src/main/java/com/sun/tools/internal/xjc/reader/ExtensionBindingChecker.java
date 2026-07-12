package com.sun.tools.internal.xjc.reader;

import com.sun.tools.internal.xjc.Options;
import java.util.StringTokenizer;
import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/ExtensionBindingChecker.class */
public final class ExtensionBindingChecker extends AbstractExtensionBindingChecker {
    private int count;

    public ExtensionBindingChecker(String schemaLanguage, Options options, ErrorHandler handler) {
        super(schemaLanguage, options, handler);
        this.count = 0;
    }

    private boolean needsToBePruned(String uri) {
        if (uri.equals(this.schemaLanguage) || uri.equals("http://java.sun.com/xml/ns/jaxb") || this.enabledExtensions.contains(uri)) {
            return false;
        }
        return isRecognizableExtension(uri);
    }

    @Override // com.sun.tools.internal.xjc.reader.AbstractExtensionBindingChecker, com.sun.tools.internal.xjc.util.SubtreeCutter, org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        super.startDocument();
        this.count = 0;
    }

    @Override // com.sun.tools.internal.xjc.util.SubtreeCutter, org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (!isCutting()) {
            String v = atts.getValue("http://java.sun.com/xml/ns/jaxb", "extensionBindingPrefixes");
            if (v != null) {
                if (this.count != 0) {
                    error(Messages.ERR_UNEXPECTED_EXTENSION_BINDING_PREFIXES.format(new Object[0]));
                }
                if (!this.allowExtensions) {
                    error(Messages.ERR_VENDOR_EXTENSION_DISALLOWED_IN_STRICT_MODE.format(new Object[0]));
                }
                StringTokenizer tokens = new StringTokenizer(v);
                while (tokens.hasMoreTokens()) {
                    String prefix = tokens.nextToken();
                    String uri = this.nsSupport.getURI(prefix);
                    if (uri == null) {
                        error(Messages.ERR_UNDECLARED_PREFIX.format(prefix));
                    } else {
                        checkAndEnable(uri);
                    }
                }
            }
            if (needsToBePruned(namespaceURI)) {
                if (isRecognizableExtension(namespaceURI)) {
                    warning(Messages.ERR_SUPPORTED_EXTENSION_IGNORED.format(namespaceURI));
                }
                startCutting();
            } else {
                verifyTagName(namespaceURI, localName, qName);
            }
        }
        this.count++;
        super.startElement(namespaceURI, localName, qName, atts);
    }
}
