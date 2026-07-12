package com.sun.xml.internal.xsom.impl.parser;

import com.sun.xml.internal.xsom.parser.XMLParser;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderAdapter;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/parser/SAXParserFactoryAdaptor.class */
public class SAXParserFactoryAdaptor extends SAXParserFactory {
    private final XMLParser parser;

    public SAXParserFactoryAdaptor(XMLParser _parser) {
        this.parser = _parser;
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public SAXParser newSAXParser() throws ParserConfigurationException, SAXException {
        return new SAXParserImpl();
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public void setFeature(String name, boolean value) {
        throw new UnsupportedOperationException("XSOM parser does not support JAXP features.");
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public boolean getFeature(String name) {
        return false;
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/parser/SAXParserFactoryAdaptor$SAXParserImpl.class */
    private class SAXParserImpl extends SAXParser {
        private final XMLReaderImpl reader;

        private SAXParserImpl() {
            this.reader = new XMLReaderImpl();
        }

        @Override // javax.xml.parsers.SAXParser
        public Parser getParser() throws SAXException {
            return new XMLReaderAdapter(this.reader);
        }

        @Override // javax.xml.parsers.SAXParser
        public XMLReader getXMLReader() throws SAXException {
            return this.reader;
        }

        @Override // javax.xml.parsers.SAXParser
        public boolean isNamespaceAware() {
            return true;
        }

        @Override // javax.xml.parsers.SAXParser
        public boolean isValidating() {
            return false;
        }

        @Override // javax.xml.parsers.SAXParser
        public void setProperty(String name, Object value) {
        }

        @Override // javax.xml.parsers.SAXParser
        public Object getProperty(String name) {
            return null;
        }
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/parser/SAXParserFactoryAdaptor$XMLReaderImpl.class */
    private class XMLReaderImpl extends XMLFilterImpl {
        private XMLReaderImpl() {
        }

        @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.XMLReader
        public void parse(InputSource input) throws IOException, SAXException {
            SAXParserFactoryAdaptor.this.parser.parse(input, this, this, this);
        }

        @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.XMLReader
        public void parse(String systemId) throws IOException, SAXException {
            SAXParserFactoryAdaptor.this.parser.parse(new InputSource(systemId), this, this, this);
        }
    }
}
