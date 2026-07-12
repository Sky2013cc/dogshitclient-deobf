package com.sun.tools.internal.xjc.reader.dtd.bindinfo;

import com.sun.tools.internal.xjc.Options;
import com.sun.tools.internal.xjc.reader.AbstractExtensionBindingChecker;
import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/dtd/bindinfo/DTDExtensionBindingChecker.class */
final class DTDExtensionBindingChecker extends AbstractExtensionBindingChecker {
    public DTDExtensionBindingChecker(String schemaLanguage, Options options, ErrorHandler handler) {
        super(schemaLanguage, options, handler);
    }

    private boolean needsToBePruned(String uri) {
        if (uri.equals(this.schemaLanguage) || uri.equals("http://java.sun.com/xml/ns/jaxb") || uri.equals("http://java.sun.com/xml/ns/jaxb/xjc")) {
            return false;
        }
        return this.enabledExtensions.contains(uri);
    }

    @Override // com.sun.tools.internal.xjc.util.SubtreeCutter, org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (!isCutting() && !uri.equals("")) {
            checkAndEnable(uri);
            verifyTagName(uri, localName, qName);
            if (needsToBePruned(uri)) {
                startCutting();
            }
        }
        super.startElement(uri, localName, qName, atts);
    }
}
