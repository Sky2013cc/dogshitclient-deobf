package com.sun.tools.internal.xjc.reader.xmlschema.parser;

import java.io.InputStream;
import java.io.Reader;
import org.w3c.dom.ls.LSInput;
import org.xml.sax.InputSource;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/parser/LSInputSAXWrapper.class */
public class LSInputSAXWrapper implements LSInput {
    private InputSource core;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !LSInputSAXWrapper.class.desiredAssertionStatus();
    }

    public LSInputSAXWrapper(InputSource inputSource) {
        if (!$assertionsDisabled && inputSource == null) {
            throw new AssertionError();
        }
        this.core = inputSource;
    }

    @Override // org.w3c.dom.ls.LSInput
    public Reader getCharacterStream() {
        return this.core.getCharacterStream();
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setCharacterStream(Reader characterStream) {
        this.core.setCharacterStream(characterStream);
    }

    @Override // org.w3c.dom.ls.LSInput
    public InputStream getByteStream() {
        return this.core.getByteStream();
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setByteStream(InputStream byteStream) {
        this.core.setByteStream(byteStream);
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getStringData() {
        return null;
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setStringData(String stringData) {
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getSystemId() {
        return this.core.getSystemId();
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setSystemId(String systemId) {
        this.core.setSystemId(systemId);
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getPublicId() {
        return this.core.getPublicId();
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setPublicId(String publicId) {
        this.core.setPublicId(publicId);
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getBaseURI() {
        return null;
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setBaseURI(String baseURI) {
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getEncoding() {
        return this.core.getEncoding();
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setEncoding(String encoding) {
        this.core.setEncoding(encoding);
    }

    @Override // org.w3c.dom.ls.LSInput
    public boolean getCertifiedText() {
        return true;
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setCertifiedText(boolean certifiedText) {
    }
}
