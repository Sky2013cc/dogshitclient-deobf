package com.sun.tools.internal.xjc.reader;

import com.sun.tools.internal.xjc.Options;
import com.sun.tools.internal.xjc.Plugin;
import com.sun.tools.internal.xjc.util.SubtreeCutter;
import com.sun.xml.internal.bind.v2.util.EditDistance;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.NamespaceSupport;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/AbstractExtensionBindingChecker.class */
public abstract class AbstractExtensionBindingChecker extends SubtreeCutter {
    protected final NamespaceSupport nsSupport = new NamespaceSupport();
    protected final Set<String> enabledExtensions = new HashSet();
    private final Set<String> recognizableExtensions = new HashSet();
    private Locator locator;
    protected final String schemaLanguage;
    protected final boolean allowExtensions;
    private final Options options;

    public AbstractExtensionBindingChecker(String schemaLanguage, Options options, ErrorHandler handler) {
        this.schemaLanguage = schemaLanguage;
        this.allowExtensions = options.compatibilityMode != 1;
        this.options = options;
        setErrorHandler(handler);
        for (Plugin plugin : options.getAllPlugins()) {
            this.recognizableExtensions.addAll(plugin.getCustomizationURIs());
        }
        this.recognizableExtensions.add("http://java.sun.com/xml/ns/jaxb/xjc");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void checkAndEnable(String uri) throws SAXException {
        if (!isRecognizableExtension(uri)) {
            String nearest = EditDistance.findNearest(uri, this.recognizableExtensions);
            error(Messages.ERR_UNSUPPORTED_EXTENSION.format(uri, nearest));
        } else if (!isSupportedExtension(uri)) {
            Plugin owner = null;
            Iterator<Plugin> it = this.options.getAllPlugins().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Plugin p = it.next();
                if (p.getCustomizationURIs().contains(uri)) {
                    owner = p;
                    break;
                }
            }
            if (owner != null) {
                error(Messages.ERR_PLUGIN_NOT_ENABLED.format(owner.getOptionName(), uri));
            } else {
                error(Messages.ERR_UNSUPPORTED_EXTENSION.format(uri));
            }
        }
        this.enabledExtensions.add(uri);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void verifyTagName(String namespaceURI, String localName, String qName) throws SAXException {
        if (this.options.pluginURIs.contains(namespaceURI)) {
            boolean correct = false;
            Iterator<Plugin> it = this.options.activePlugins.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Plugin p = it.next();
                if (p.isCustomizationTagName(namespaceURI, localName)) {
                    correct = true;
                    break;
                }
            }
            if (!correct) {
                error(Messages.ERR_ILLEGAL_CUSTOMIZATION_TAGNAME.format(qName));
                startCutting();
            }
        }
    }

    protected final boolean isSupportedExtension(String namespaceUri) {
        return namespaceUri.equals("http://java.sun.com/xml/ns/jaxb/xjc") || this.options.pluginURIs.contains(namespaceUri);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isRecognizableExtension(String namespaceUri) {
        return this.recognizableExtensions.contains(namespaceUri);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
        this.locator = locator;
    }

    @Override // com.sun.tools.internal.xjc.util.SubtreeCutter, org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        super.startDocument();
        this.nsSupport.reset();
        this.enabledExtensions.clear();
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        if ("http://www.w3.org/XML/1998/namespace".equals(uri)) {
            return;
        }
        super.startPrefixMapping(prefix, uri);
        this.nsSupport.pushContext();
        this.nsSupport.declarePrefix(prefix, uri);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endPrefixMapping(String prefix) throws SAXException {
        if ("xml".equals(prefix)) {
            return;
        }
        super.endPrefixMapping(prefix);
        this.nsSupport.popContext();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final SAXParseException error(String msg) throws SAXException {
        SAXParseException spe = new SAXParseException(msg, this.locator);
        getErrorHandler().error(spe);
        return spe;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void warning(String msg) throws SAXException {
        SAXParseException spe = new SAXParseException(msg, this.locator);
        getErrorHandler().warning(spe);
    }
}
