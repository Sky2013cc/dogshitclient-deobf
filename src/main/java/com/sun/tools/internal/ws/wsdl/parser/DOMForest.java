package com.sun.tools.internal.ws.wsdl.parser;

import com.sun.istack.internal.NotNull;
import com.sun.tools.internal.ws.util.xml.XmlUtil;
import com.sun.tools.internal.ws.wscompile.ErrorReceiver;
import com.sun.tools.internal.ws.wscompile.WsimportOptions;
import com.sun.tools.internal.xjc.reader.internalizer.LocatorTable;
import com.sun.xml.internal.bind.marshaller.DataWriter;
import com.sun.xml.internal.bind.marshaller.SAX2DOMEx;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/parser/DOMForest.class */
public class DOMForest {
    protected final ErrorReceiver errorReceiver;
    private final DocumentBuilder documentBuilder;
    private final SAXParserFactory parserFactory;
    protected final EntityResolver entityResolver;
    protected final InternalizationLogic logic;
    protected final WsimportOptions options;
    protected final Set<String> rootDocuments = new HashSet();
    protected final Set<String> externalReferences = new HashSet();
    protected final Map<String, Document> core = new HashMap();
    protected final List<Element> inlinedSchemaElements = new ArrayList();
    public final LocatorTable locatorTable = new LocatorTable();
    public final Set<Element> outerMostBindings = new HashSet();
    protected Map<String, String> resolvedCache = new HashMap();

    /* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/parser/DOMForest$Handler.class */
    public interface Handler extends ContentHandler {
        Document getDocument();
    }

    public DOMForest(InternalizationLogic logic, @NotNull EntityResolver entityResolver, WsimportOptions options, ErrorReceiver errReceiver) {
        this.options = options;
        this.entityResolver = entityResolver;
        this.errorReceiver = errReceiver;
        this.logic = logic;
        boolean disableXmlSecurity = options == null ? false : options.disableXmlSecurity;
        DocumentBuilderFactory dbf = XmlUtil.newDocumentBuilderFactory(disableXmlSecurity);
        this.parserFactory = XmlUtil.newSAXParserFactory(disableXmlSecurity);
        try {
            this.documentBuilder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new AssertionError(e);
        }
    }

    public List<Element> getInlinedSchemaElement() {
        return this.inlinedSchemaElements;
    }

    @NotNull
    public Document parse(InputSource source, boolean root) throws SAXException, IOException {
        if (source.getSystemId() == null) {
            throw new IllegalArgumentException();
        }
        return parse(source.getSystemId(), source, root);
    }

    public Document parse(String systemId, boolean root) throws SAXException, IOException {
        String systemId2 = normalizeSystemId(systemId);
        InputSource is = this.entityResolver.resolveEntity(null, systemId2);
        if (is == null) {
            is = new InputSource(systemId2);
        } else {
            this.resolvedCache.put(systemId2, is.getSystemId());
            systemId2 = is.getSystemId();
        }
        if (this.core.containsKey(systemId2)) {
            return this.core.get(systemId2);
        }
        if (!root) {
            addExternalReferences(systemId2);
        }
        return parse(systemId2, is, root);
    }

    public Map<String, String> getReferencedEntityMap() {
        return this.resolvedCache;
    }

    @NotNull
    private Document parse(String systemId, InputSource inputSource, boolean root) throws SAXException, IOException {
        Document dom = this.documentBuilder.newDocument();
        String systemId2 = normalizeSystemId(systemId);
        this.core.put(systemId2, dom);
        dom.setDocumentURI(systemId2);
        if (root) {
            this.rootDocuments.add(systemId2);
        }
        try {
            XMLReader reader = createReader(dom);
            if (inputSource.getByteStream() == null) {
                inputSource = this.entityResolver.resolveEntity(null, systemId2);
            }
            reader.parse(inputSource);
            Element doc = dom.getDocumentElement();
            if (doc == null) {
                return null;
            }
            NodeList schemas = doc.getElementsByTagNameNS("http://www.w3.org/2001/XMLSchema", "schema");
            for (int i = 0; i < schemas.getLength(); i++) {
                this.inlinedSchemaElements.add((Element) schemas.item(i));
            }
            this.resolvedCache.put(systemId2, dom.getDocumentURI());
            return dom;
        } catch (ParserConfigurationException e) {
            this.errorReceiver.error(e);
            throw new SAXException(e.getMessage());
        }
    }

    public void addExternalReferences(String ref) {
        if (!this.externalReferences.contains(ref)) {
            this.externalReferences.add(ref);
        }
    }

    public Set<String> getExternalReferences() {
        return this.externalReferences;
    }

    private XMLReader createReader(Document dom) throws SAXException, ParserConfigurationException {
        XMLReader xMLReader = this.parserFactory.newSAXParser().getXMLReader();
        SAX2DOMEx dOMBuilder = new DOMBuilder(dom, this.locatorTable, this.outerMostBindings);
        try {
            xMLReader.setProperty("http://xml.org/sax/properties/lexical-handler", dOMBuilder);
        } catch (SAXException e) {
            this.errorReceiver.debug(e.getMessage());
        }
        ContentHandler handler = new WhitespaceStripper(dOMBuilder, this.errorReceiver, this.entityResolver);
        ContentHandler handler2 = new VersionChecker(handler, this.errorReceiver, this.entityResolver);
        XMLFilterImpl f = this.logic.createExternalReferenceFinder(this);
        f.setContentHandler(handler2);
        if (this.errorReceiver != null) {
            f.setErrorHandler(this.errorReceiver);
        }
        f.setEntityResolver(this.entityResolver);
        xMLReader.setContentHandler(f);
        if (this.errorReceiver != null) {
            xMLReader.setErrorHandler(this.errorReceiver);
        }
        xMLReader.setEntityResolver(this.entityResolver);
        return xMLReader;
    }

    private String normalizeSystemId(String systemId) {
        try {
            systemId = new URI(systemId).normalize().toString();
        } catch (URISyntaxException e) {
        }
        return systemId;
    }

    boolean isExtensionMode() {
        return this.options.isExtensionMode();
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

    public String[] listSystemIDs() {
        return (String[]) this.core.keySet().toArray(new String[this.core.keySet().size()]);
    }

    public String getSystemId(Document dom) {
        for (Map.Entry<String, Document> e : this.core.entrySet()) {
            if (e.getValue() == dom) {
                return e.getKey();
            }
        }
        return null;
    }

    public String getFirstRootDocument() {
        if (this.rootDocuments.isEmpty()) {
            return null;
        }
        return this.rootDocuments.iterator().next();
    }

    public Set<String> getRootDocuments() {
        return this.rootDocuments;
    }

    public void dump(OutputStream out) throws IOException {
        try {
            boolean secureProcessingEnabled = this.options == null || !this.options.disableXmlSecurity;
            TransformerFactory tf = XmlUtil.newTransformerFactory(secureProcessingEnabled);
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
