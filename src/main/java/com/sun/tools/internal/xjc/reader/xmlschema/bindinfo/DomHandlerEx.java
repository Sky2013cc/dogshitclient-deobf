package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import com.sun.xml.internal.bind.marshaller.SAX2DOMEx;
import com.sun.xml.internal.bind.v2.util.XmlFactory;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Locator;
import org.xml.sax.helpers.LocatorImpl;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/bindinfo/DomHandlerEx.class */
final class DomHandlerEx implements DomHandler<DomAndLocation, ResultImpl> {
    DomHandlerEx() {
    }

    /* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/bindinfo/DomHandlerEx$DomAndLocation.class */
    public static final class DomAndLocation {
        public final Element element;
        public final Locator loc;

        public DomAndLocation(Element element, Locator loc) {
            this.element = element;
            this.loc = loc;
        }
    }

    /* renamed from: createUnmarshaller, reason: merged with bridge method [inline-methods] */
    public ResultImpl m382createUnmarshaller(ValidationEventHandler errorHandler) {
        return new ResultImpl();
    }

    public DomAndLocation getElement(ResultImpl r) {
        return new DomAndLocation(((Document) r.s2d.getDOM()).getDocumentElement(), r.location);
    }

    public Source marshal(DomAndLocation domAndLocation, ValidationEventHandler errorHandler) {
        return new DOMSource(domAndLocation.element);
    }

    /* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/bindinfo/DomHandlerEx$ResultImpl.class */
    public static final class ResultImpl extends SAXResult {
        final SAX2DOMEx s2d;
        Locator location = null;

        ResultImpl() {
            try {
                DocumentBuilderFactory factory = XmlFactory.createDocumentBuilderFactory(false);
                this.s2d = new SAX2DOMEx(factory);
                XMLFilterImpl f = new XMLFilterImpl() { // from class: com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.DomHandlerEx.ResultImpl.1
                    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
                    public void setDocumentLocator(Locator locator) {
                        super.setDocumentLocator(locator);
                        ResultImpl.this.location = new LocatorImpl(locator);
                    }
                };
                f.setContentHandler(this.s2d);
                setHandler(f);
            } catch (ParserConfigurationException e) {
                throw new AssertionError(e);
            }
        }
    }
}
