package com.sun.xml.internal.rngom.digested;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/* loaded from: target.jar:com/sun/xml/internal/rngom/digested/DOMPrinter.class */
class DOMPrinter {
    protected XMLStreamWriter out;

    public DOMPrinter(XMLStreamWriter out) {
        this.out = out;
    }

    public void print(Node node) throws XMLStreamException {
        switch (node.getNodeType()) {
            case 1:
                visitElement((Element) node);
                return;
            case 2:
            case 6:
            default:
                throw new XMLStreamException("Unexpected DOM Node Type " + ((int) node.getNodeType()));
            case 3:
                visitText((Text) node);
                return;
            case 4:
                visitCDATASection((CDATASection) node);
                return;
            case 5:
                visitReference((EntityReference) node);
                return;
            case 7:
                visitProcessingInstruction((ProcessingInstruction) node);
                return;
            case 8:
                visitComment((Comment) node);
                return;
            case 9:
                visitDocument((Document) node);
                return;
            case 10:
                return;
            case 11:
                visitDocumentFragment((DocumentFragment) node);
                return;
        }
    }

    protected void visitChildren(Node node) throws XMLStreamException {
        NodeList nodeList = node.getChildNodes();
        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                print(nodeList.item(i));
            }
        }
    }

    protected void visitDocument(Document document) throws XMLStreamException {
        this.out.writeStartDocument();
        print(document.getDocumentElement());
        this.out.writeEndDocument();
    }

    protected void visitDocumentFragment(DocumentFragment documentFragment) throws XMLStreamException {
        visitChildren(documentFragment);
    }

    protected void visitElement(Element node) throws XMLStreamException {
        this.out.writeStartElement(node.getPrefix(), node.getLocalName(), node.getNamespaceURI());
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            visitAttr((Attr) attrs.item(i));
        }
        visitChildren(node);
        this.out.writeEndElement();
    }

    protected void visitAttr(Attr node) throws XMLStreamException {
        String name = node.getLocalName();
        if (name.equals(Constants.XMLNS)) {
            this.out.writeDefaultNamespace(node.getNamespaceURI());
            return;
        }
        String prefix = node.getPrefix();
        if (prefix != null && prefix.equals(Constants.XMLNS)) {
            this.out.writeNamespace(prefix, node.getNamespaceURI());
        } else if (prefix != null) {
            this.out.writeAttribute(prefix, node.getNamespaceURI(), name, node.getNodeValue());
        } else {
            this.out.writeAttribute(node.getNamespaceURI(), name, node.getNodeValue());
        }
    }

    protected void visitComment(Comment comment) throws XMLStreamException {
        this.out.writeComment(comment.getData());
    }

    protected void visitText(Text node) throws XMLStreamException {
        this.out.writeCharacters(node.getNodeValue());
    }

    protected void visitCDATASection(CDATASection cdata) throws XMLStreamException {
        this.out.writeCData(cdata.getNodeValue());
    }

    protected void visitProcessingInstruction(ProcessingInstruction processingInstruction) throws XMLStreamException {
        this.out.writeProcessingInstruction(processingInstruction.getNodeName(), processingInstruction.getData());
    }

    protected void visitReference(EntityReference entityReference) throws XMLStreamException {
        visitChildren(entityReference);
    }
}
