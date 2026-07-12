package com.sun.xml.internal.rngom.parse.xml;

import java.util.Hashtable;
import org.relaxng.datatype.ValidationContext;
import org.xml.sax.DTDHandler;
import org.xml.sax.SAXException;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/xml/DtdContext.class */
public abstract class DtdContext implements DTDHandler, ValidationContext {
    private final Hashtable notationTable;
    private final Hashtable unparsedEntityTable;

    public DtdContext() {
        this.notationTable = new Hashtable();
        this.unparsedEntityTable = new Hashtable();
    }

    public DtdContext(DtdContext dc) {
        this.notationTable = dc.notationTable;
        this.unparsedEntityTable = dc.unparsedEntityTable;
    }

    @Override // org.xml.sax.DTDHandler
    public void notationDecl(String name, String publicId, String systemId) throws SAXException {
        this.notationTable.put(name, name);
    }

    @Override // org.xml.sax.DTDHandler
    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
        this.unparsedEntityTable.put(name, name);
    }

    @Override // org.relaxng.datatype.ValidationContext
    public boolean isNotation(String notationName) {
        return this.notationTable.get(notationName) != null;
    }

    @Override // org.relaxng.datatype.ValidationContext
    public boolean isUnparsedEntity(String entityName) {
        return this.unparsedEntityTable.get(entityName) != null;
    }

    public void clearDtdContext() {
        this.notationTable.clear();
        this.unparsedEntityTable.clear();
    }
}
