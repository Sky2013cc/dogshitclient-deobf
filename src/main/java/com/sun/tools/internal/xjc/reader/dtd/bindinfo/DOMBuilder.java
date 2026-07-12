package com.sun.tools.internal.xjc.reader.dtd.bindinfo;

import com.sun.xml.internal.bind.marshaller.SAX2DOMEx;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/dtd/bindinfo/DOMBuilder.class */
final class DOMBuilder extends SAX2DOMEx {
    private Locator locator;

    public DOMBuilder(DocumentBuilderFactory f) throws ParserConfigurationException {
        super(f);
    }

    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
        this.locator = locator;
    }

    public void startElement(String namespace, String localName, String qName, Attributes attrs) {
        super.startElement(namespace, localName, qName, attrs);
        DOMLocator.setLocationInfo(getCurrentElement(), this.locator);
    }
}
