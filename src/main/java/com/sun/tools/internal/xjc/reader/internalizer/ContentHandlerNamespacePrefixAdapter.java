package com.sun.tools.internal.xjc.reader.internalizer;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import com.sun.xml.internal.dtdparser.DTDParser;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/internalizer/ContentHandlerNamespacePrefixAdapter.class */
final class ContentHandlerNamespacePrefixAdapter extends XMLFilterImpl {
    private boolean namespacePrefixes;
    private String[] nsBinding;
    private int len;
    private final AttributesImpl atts;
    private static final String PREFIX_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
    private static final String NAMESPACE_FEATURE = "http://xml.org/sax/features/namespaces";

    public ContentHandlerNamespacePrefixAdapter() {
        this.namespacePrefixes = false;
        this.nsBinding = new String[8];
        this.atts = new AttributesImpl();
    }

    public ContentHandlerNamespacePrefixAdapter(XMLReader parent) {
        this.namespacePrefixes = false;
        this.nsBinding = new String[8];
        this.atts = new AttributesImpl();
        setParent(parent);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.XMLReader
    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name.equals(PREFIX_FEATURE)) {
            return this.namespacePrefixes;
        }
        return super.getFeature(name);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.XMLReader
    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name.equals(PREFIX_FEATURE)) {
            this.namespacePrefixes = value;
        } else {
            if (name.equals(NAMESPACE_FEATURE) && value) {
                return;
            }
            super.setFeature(name, value);
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        if ("http://www.w3.org/XML/1998/namespace".equals(uri)) {
            return;
        }
        if (this.len == this.nsBinding.length) {
            String[] buf = new String[this.nsBinding.length * 2];
            System.arraycopy(this.nsBinding, 0, buf, 0, this.nsBinding.length);
            this.nsBinding = buf;
        }
        String[] strArr = this.nsBinding;
        int i = this.len;
        this.len = i + 1;
        strArr[i] = prefix;
        String[] strArr2 = this.nsBinding;
        int i2 = this.len;
        this.len = i2 + 1;
        strArr2[i2] = uri;
        super.startPrefixMapping(prefix, uri);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (this.namespacePrefixes) {
            this.atts.setAttributes(atts);
            for (int i = 0; i < this.len; i += 2) {
                String prefix = this.nsBinding[i];
                if (prefix.length() == 0) {
                    this.atts.addAttribute("http://www.w3.org/XML/1998/namespace", Constants.XMLNS, Constants.XMLNS, DTDParser.TYPE_CDATA, this.nsBinding[i + 1]);
                } else {
                    this.atts.addAttribute("http://www.w3.org/XML/1998/namespace", prefix, "xmlns:" + prefix, DTDParser.TYPE_CDATA, this.nsBinding[i + 1]);
                }
            }
            atts = this.atts;
        }
        this.len = 0;
        super.startElement(uri, localName, qName, atts);
    }
}
