package com.sun.tools.internal.xjc.reader.xmlschema.parser;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import javax.xml.namespace.QName;
import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/parser/CustomizationContextChecker.class */
public class CustomizationContextChecker extends XMLFilterImpl {
    private final Stack<QName> elementNames = new Stack<>();
    private final ErrorHandler errorHandler;
    private Locator locator;
    private static final Set<String> prohibitedSchemaElementNames = new HashSet();

    static {
        prohibitedSchemaElementNames.add(Constants.ATTRVALUE_RESTRICTION);
        prohibitedSchemaElementNames.add(Constants.ATTRVALUE_EXTENSION);
        prohibitedSchemaElementNames.add("simpleContent");
        prohibitedSchemaElementNames.add("complexContent");
        prohibitedSchemaElementNames.add(Constants.ATTRVALUE_LIST);
        prohibitedSchemaElementNames.add(Constants.ATTRVALUE_UNION);
    }

    public CustomizationContextChecker(ErrorHandler _errorHandler) {
        this.errorHandler = _errorHandler;
    }

    private QName top() {
        return this.elementNames.peek();
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        QName newElement = new QName(namespaceURI, localName);
        if (newElement.getNamespaceURI().equals("http://java.sun.com/xml/ns/jaxb") && top().getNamespaceURI().equals("http://www.w3.org/2001/XMLSchema") && this.elementNames.size() >= 3) {
            QName schemaElement = this.elementNames.get(this.elementNames.size() - 3);
            if (prohibitedSchemaElementNames.contains(schemaElement.getLocalPart())) {
                this.errorHandler.error(new SAXParseException(Messages.format("CustomizationContextChecker.UnacknolwedgedCustomization", localName), this.locator));
            }
        }
        this.elementNames.push(newElement);
        super.startElement(namespaceURI, localName, qName, atts);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        super.endElement(namespaceURI, localName, qName);
        this.elementNames.pop();
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
        this.locator = locator;
    }
}
