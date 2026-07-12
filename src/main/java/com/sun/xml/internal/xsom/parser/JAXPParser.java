package com.sun.xml.internal.xsom.parser;

import com.sun.xml.internal.xsom.impl.parser.Messages;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/xml/internal/xsom/parser/JAXPParser.class */
public class JAXPParser implements XMLParser {
    private static final String ACCESS_EXTERNAL_SCHEMA = "http://javax.xml.XMLConstants/property/accessExternalSchema";
    private static final Logger LOGGER = Logger.getLogger(JAXPParser.class.getName());
    private final SAXParserFactory factory;

    public JAXPParser(SAXParserFactory factory) {
        factory.setNamespaceAware(true);
        this.factory = factory;
    }

    public JAXPParser() {
        this(SAXParserFactory.newInstance());
    }

    @Override // com.sun.xml.internal.xsom.parser.XMLParser
    public void parse(InputSource source, ContentHandler handler, ErrorHandler errorHandler, EntityResolver entityResolver) throws SAXException, IOException {
        try {
            SAXParser saxParser = allowFileAccess(this.factory.newSAXParser(), false);
            XMLReader reader = new XMLReaderEx(saxParser.getXMLReader());
            reader.setContentHandler(handler);
            if (errorHandler != null) {
                reader.setErrorHandler(errorHandler);
            }
            if (entityResolver != null) {
                reader.setEntityResolver(entityResolver);
            }
            reader.parse(source);
        } catch (ParserConfigurationException e) {
            SAXParseException spe = new SAXParseException(e.getMessage(), null, e);
            errorHandler.fatalError(spe);
            throw spe;
        }
    }

    private static SAXParser allowFileAccess(SAXParser saxParser, boolean disableSecureProcessing) throws SAXException {
        if (disableSecureProcessing) {
            return saxParser;
        }
        try {
            saxParser.setProperty(ACCESS_EXTERNAL_SCHEMA, "file");
            LOGGER.log(Level.FINE, Messages.format(Messages.JAXP_SUPPORTED_PROPERTY, ACCESS_EXTERNAL_SCHEMA));
        } catch (SAXException ignored) {
            LOGGER.log(Level.CONFIG, Messages.format(Messages.JAXP_UNSUPPORTED_PROPERTY, ACCESS_EXTERNAL_SCHEMA), (Throwable) ignored);
        }
        return saxParser;
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/parser/JAXPParser$XMLReaderEx.class */
    private static class XMLReaderEx extends XMLFilterImpl {
        private Locator locator;

        XMLReaderEx(XMLReader parent) {
            setParent(parent);
        }

        @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.EntityResolver
        public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
            try {
                InputSource is = null;
                if (getEntityResolver() != null) {
                    is = getEntityResolver().resolveEntity(publicId, systemId);
                }
                if (is != null) {
                    return is;
                }
                InputSource is2 = new InputSource(new URL(systemId).openStream());
                is2.setSystemId(systemId);
                is2.setPublicId(publicId);
                return is2;
            } catch (IOException e) {
                SAXParseException spe = new SAXParseException(Messages.format(Messages.ERR_ENTITY_RESOLUTION_FAILURE, systemId, e.toString()), this.locator, e);
                if (getErrorHandler() != null) {
                    getErrorHandler().fatalError(spe);
                }
                throw spe;
            }
        }

        @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
        public void setDocumentLocator(Locator locator) {
            super.setDocumentLocator(locator);
            this.locator = locator;
        }
    }
}
