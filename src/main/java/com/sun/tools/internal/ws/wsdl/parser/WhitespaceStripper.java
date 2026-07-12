package com.sun.tools.internal.ws.wsdl.parser;

import com.sun.xml.internal.bind.WhiteSpaceProcessor;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/parser/WhitespaceStripper.class */
class WhitespaceStripper extends XMLFilterImpl {
    private int state;
    private char[] buf;
    private int bufLen;
    private static final int AFTER_START_ELEMENT = 1;
    private static final int AFTER_END_ELEMENT = 2;

    public WhitespaceStripper(XMLReader reader) {
        this.state = 0;
        this.buf = new char[1024];
        this.bufLen = 0;
        setParent(reader);
    }

    public WhitespaceStripper(ContentHandler handler, ErrorHandler eh, EntityResolver er) {
        this.state = 0;
        this.buf = new char[1024];
        this.bufLen = 0;
        setContentHandler(handler);
        if (eh != null) {
            setErrorHandler(eh);
        }
        if (er != null) {
            setEntityResolver(er);
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (this.state) {
            case 1:
                if (this.bufLen + length > this.buf.length) {
                    char[] newBuf = new char[Math.max(this.bufLen + length, this.buf.length * 2)];
                    System.arraycopy(this.buf, 0, newBuf, 0, this.bufLen);
                    this.buf = newBuf;
                }
                System.arraycopy(ch, start, this.buf, this.bufLen, length);
                this.bufLen += length;
                return;
            case 2:
                int len = start + length;
                for (int i = start; i < len; i++) {
                    if (!WhiteSpaceProcessor.isWhiteSpace(ch[i])) {
                        super.characters(ch, start, length);
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        processPendingText();
        super.startElement(uri, localName, qName, atts);
        this.state = 1;
        this.bufLen = 0;
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endElement(String uri, String localName, String qName) throws SAXException {
        processPendingText();
        super.endElement(uri, localName, qName);
        this.state = 2;
    }

    private void processPendingText() throws SAXException {
        if (this.state == 1) {
            for (int i = this.bufLen - 1; i >= 0; i--) {
                if (!WhiteSpaceProcessor.isWhiteSpace(this.buf[i])) {
                    super.characters(this.buf, 0, this.bufLen);
                    return;
                }
            }
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    }
}
