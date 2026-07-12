package com.sun.tools.internal.xjc.reader.internalizer;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.XMLStreamReaderToContentHandler;
import com.sun.tools.internal.xjc.ErrorReceiver;
import com.sun.tools.internal.xjc.Options;
import com.sun.tools.internal.xjc.util.ErrorReceiverFilter;
import com.sun.xml.internal.bind.marshaller.DataWriter;
import com.sun.xml.internal.bind.v2.util.XmlFactory;
import com.sun.xml.internal.xsom.parser.JAXPParser;
import com.sun.xml.internal.xsom.parser.XMLParser;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/internalizer/DOMForest.class */
public final class DOMForest {
    private final Map<String, Document> core;
    private final Set<String> rootDocuments;
    public final LocatorTable locatorTable;
    public final Set<Element> outerMostBindings;
    private EntityResolver entityResolver;
    private ErrorReceiver errorReceiver;
    protected final InternalizationLogic logic;
    private final SAXParserFactory parserFactory;
    private final DocumentBuilder documentBuilder;
    private final Options options;

    /* loaded from: target.jar:com/sun/tools/internal/xjc/reader/internalizer/DOMForest$Handler.class */
    public interface Handler extends ContentHandler {
        Document getDocument();
    }

    public DOMForest(SAXParserFactory parserFactory, DocumentBuilder documentBuilder, InternalizationLogic logic) {
        this.core = new HashMap();
        this.rootDocuments = new HashSet();
        this.locatorTable = new LocatorTable();
        this.outerMostBindings = new HashSet();
        this.entityResolver = null;
        this.errorReceiver = null;
        this.parserFactory = parserFactory;
        this.documentBuilder = documentBuilder;
        this.logic = logic;
        this.options = null;
    }

    public DOMForest(InternalizationLogic logic, Options opt) {
        this.core = new HashMap();
        this.rootDocuments = new HashSet();
        this.locatorTable = new LocatorTable();
        this.outerMostBindings = new HashSet();
        this.entityResolver = null;
        this.errorReceiver = null;
        if (opt == null) {
            throw new AssertionError("Options object null");
        }
        this.options = opt;
        try {
            DocumentBuilderFactory dbf = XmlFactory.createDocumentBuilderFactory(opt.disableXmlSecurity);
            this.documentBuilder = dbf.newDocumentBuilder();
            this.parserFactory = XmlFactory.createParserFactory(opt.disableXmlSecurity);
            this.logic = logic;
        } catch (ParserConfigurationException e) {
            throw new AssertionError(e);
        }
    }

    public Document get(String systemId) {
        Document doc = this.core.get(systemId);
        if (doc == null && systemId.startsWith("file:/") && !systemId.startsWith("file://")) {
            doc = this.core.get("file://" + systemId.substring(5));
        }
        if (doc == null && systemId.startsWith("file:")) {
            String systemPath = getPath(systemId);
            Iterator<String> it = this.core.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String key = it.next();
                if (key.startsWith("file:") && getPath(key).equalsIgnoreCase(systemPath)) {
                    doc = this.core.get(key);
                    break;
                }
            }
        }
        return doc;
    }

    private String getPath(String key) {
        String key2;
        String substring = key.substring(5);
        while (true) {
            key2 = substring;
            if (key2.length() <= 0 || key2.charAt(0) != '/') {
                break;
            }
            substring = key2.substring(1);
        }
        return key2;
    }

    public Set<String> getRootDocuments() {
        return Collections.unmodifiableSet(this.rootDocuments);
    }

    public Document getOneDocument() {
        for (Document dom : this.core.values()) {
            if (!dom.getDocumentElement().getNamespaceURI().equals("http://java.sun.com/xml/ns/jaxb")) {
                return dom;
            }
        }
        throw new AssertionError();
    }

    public boolean checkSchemaCorrectness(ErrorReceiver errorHandler) {
        try {
            boolean disableXmlSecurity = false;
            if (this.options != null) {
                disableXmlSecurity = this.options.disableXmlSecurity;
            }
            SchemaFactory sf = XmlFactory.createSchemaFactory("http://www.w3.org/2001/XMLSchema", disableXmlSecurity);
            ErrorReceiverFilter filter = new ErrorReceiverFilter(errorHandler);
            sf.setErrorHandler(filter);
            Set<String> roots = getRootDocuments();
            Source[] sources = new Source[roots.size()];
            int i = 0;
            for (String root : roots) {
                int i2 = i;
                i++;
                sources[i2] = new DOMSource(get(root), root);
            }
            sf.newSchema(sources);
            return !filter.hadError();
        } catch (SAXException e) {
            return false;
        }
    }

    public String getSystemId(Document dom) {
        for (Map.Entry<String, Document> e : this.core.entrySet()) {
            if (e.getValue() == dom) {
                return e.getKey();
            }
        }
        return null;
    }

    public Document parse(InputSource source, boolean root) throws SAXException {
        if (source.getSystemId() == null) {
            throw new IllegalArgumentException();
        }
        return parse(source.getSystemId(), source, root);
    }

    public Document parse(String systemId, boolean root) throws SAXException, IOException {
        String systemId2 = Options.normalizeSystemId(systemId);
        if (this.core.containsKey(systemId2)) {
            return this.core.get(systemId2);
        }
        InputSource is = null;
        if (this.entityResolver != null) {
            is = this.entityResolver.resolveEntity(null, systemId2);
        }
        if (is == null) {
            is = new InputSource(systemId2);
        }
        return parse(systemId2, is, root);
    }

    private ContentHandler getParserHandler(Document dom) {
        ContentHandler handler = new WhitespaceStripper(new DOMBuilder(dom, this.locatorTable, this.outerMostBindings), this.errorReceiver, this.entityResolver);
        ContentHandler handler2 = new VersionChecker(handler, this.errorReceiver, this.entityResolver);
        XMLFilterImpl f = this.logic.createExternalReferenceFinder(this);
        f.setContentHandler(handler2);
        if (this.errorReceiver != null) {
            f.setErrorHandler(this.errorReceiver);
        }
        if (this.entityResolver != null) {
            f.setEntityResolver(this.entityResolver);
        }
        return f;
    }

    /* loaded from: target.jar:com/sun/tools/internal/xjc/reader/internalizer/DOMForest$HandlerImpl.class */
    private static abstract class HandlerImpl extends XMLFilterImpl implements Handler {
        private HandlerImpl() {
        }
    }

    public Handler getParserHandler(String systemId, boolean root) {
        final Document dom = this.documentBuilder.newDocument();
        this.core.put(systemId, dom);
        if (root) {
            this.rootDocuments.add(systemId);
        }
        ContentHandler handler = getParserHandler(dom);
        HandlerImpl x = new HandlerImpl() { // from class: com.sun.tools.internal.xjc.reader.internalizer.DOMForest.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.sun.tools.internal.xjc.reader.internalizer.DOMForest.Handler
            public Document getDocument() {
                return dom;
            }
        };
        x.setContentHandler(handler);
        return x;
    }

    public Document parse(String systemId, InputSource inputSource, boolean root) throws SAXException {
        Document dom = this.documentBuilder.newDocument();
        String systemId2 = Options.normalizeSystemId(systemId);
        this.core.put(systemId2, dom);
        if (root) {
            this.rootDocuments.add(systemId2);
        }
        try {
            XMLReader reader = this.parserFactory.newSAXParser().getXMLReader();
            reader.setContentHandler(getParserHandler(dom));
            if (this.errorReceiver != null) {
                reader.setErrorHandler(this.errorReceiver);
            }
            if (this.entityResolver != null) {
                reader.setEntityResolver(this.entityResolver);
            }
            reader.parse(inputSource);
            return dom;
        } catch (IOException e) {
            this.errorReceiver.error(Messages.format("DOMFOREST_INPUTSOURCE_IOEXCEPTION", systemId2, e.toString()), e);
            this.core.remove(systemId2);
            this.rootDocuments.remove(systemId2);
            return null;
        } catch (ParserConfigurationException e2) {
            this.errorReceiver.error(e2.getMessage(), e2);
            this.core.remove(systemId2);
            this.rootDocuments.remove(systemId2);
            return null;
        }
    }

    public Document parse(String systemId, XMLStreamReader parser, boolean root) throws XMLStreamException {
        Document dom = this.documentBuilder.newDocument();
        String systemId2 = Options.normalizeSystemId(systemId);
        if (root) {
            this.rootDocuments.add(systemId2);
        }
        if (systemId2 == null) {
            throw new IllegalArgumentException("system id cannot be null");
        }
        this.core.put(systemId2, dom);
        new XMLStreamReaderToContentHandler(parser, getParserHandler(dom), false, false).bridge();
        return dom;
    }

    public SCDBasedBindingSet transform(boolean enableSCD) {
        return Internalizer.transform(this, enableSCD, this.options.disableXmlSecurity);
    }

    public void weakSchemaCorrectnessCheck(SchemaFactory sf) {
        List<SAXSource> sources = new ArrayList<>();
        for (String systemId : getRootDocuments()) {
            Document dom = get(systemId);
            if (!dom.getDocumentElement().getNamespaceURI().equals("http://java.sun.com/xml/ns/jaxb")) {
                SAXSource ss = createSAXSource(systemId);
                try {
                    ss.getXMLReader().setFeature("http://xml.org/sax/features/namespace-prefixes", true);
                    sources.add(ss);
                } catch (SAXException e) {
                    throw new AssertionError(e);
                }
            }
        }
        try {
            XmlFactory.allowExternalAccess(sf, "file,http", this.options.disableXmlSecurity).newSchema((Source[]) sources.toArray(new SAXSource[0]));
        } catch (RuntimeException re) {
            try {
                sf.getErrorHandler().warning(new SAXParseException(Messages.format("ERR_GENERAL_SCHEMA_CORRECTNESS_ERROR", re.getMessage()), null, null, -1, -1, re));
            } catch (SAXException e2) {
            }
        } catch (SAXException e3) {
        }
    }

    @NotNull
    public SAXSource createSAXSource(String systemId) {
        ContentHandlerNamespacePrefixAdapter reader = new ContentHandlerNamespacePrefixAdapter(new XMLFilterImpl() { // from class: com.sun.tools.internal.xjc.reader.internalizer.DOMForest.2
            @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.XMLReader
            public void parse(InputSource input) throws SAXException, IOException {
                DOMForest.this.createParser().parse(input, this, this, this);
            }

            @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.XMLReader
            public void parse(String systemId2) throws SAXException, IOException {
                parse(new InputSource(systemId2));
            }
        });
        return new SAXSource(reader, new InputSource(systemId));
    }

    public XMLParser createParser() {
        return new DOMForestParser(this, new JAXPParser(XmlFactory.createParserFactory(this.options.disableXmlSecurity)));
    }

    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    public void setEntityResolver(EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }

    public ErrorReceiver getErrorHandler() {
        return this.errorReceiver;
    }

    public void setErrorHandler(ErrorReceiver errorHandler) {
        this.errorReceiver = errorHandler;
    }

    public Document[] listDocuments() {
        return (Document[]) this.core.values().toArray(new Document[this.core.size()]);
    }

    public String[] listSystemIDs() {
        return (String[]) this.core.keySet().toArray(new String[this.core.keySet().size()]);
    }

    public void dump(OutputStream out) throws IOException {
        try {
            boolean disableXmlSecurity = false;
            if (this.options != null) {
                disableXmlSecurity = this.options.disableXmlSecurity;
            }
            TransformerFactory tf = XmlFactory.createTransformerFactory(disableXmlSecurity);
            Transformer it = tf.newTransformer();
            for (Map.Entry<String, Document> e : this.core.entrySet()) {
                out.write(("---<< " + e.getKey() + '\n').getBytes());
                DataWriter dw = new DataWriter(new OutputStreamWriter(out), (String) null);
                dw.setIndentStep("  ");
                it.transform(new DOMSource(e.getValue()), new SAXResult(dw));
                out.write("\n\n\n".getBytes());
            }
        } catch (TransformerException e2) {
            e2.printStackTrace();
        }
    }
}
