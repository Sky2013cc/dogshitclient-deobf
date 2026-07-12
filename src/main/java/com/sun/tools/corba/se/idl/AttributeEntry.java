package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/AttributeEntry.class */
public class AttributeEntry extends MethodEntry {
    static AttributeGen attributeGen;
    public boolean _readOnly;

    /* JADX INFO: Access modifiers changed from: protected */
    public AttributeEntry() {
        this._readOnly = false;
    }

    protected AttributeEntry(AttributeEntry attributeEntry) {
        super(attributeEntry);
        this._readOnly = false;
        this._readOnly = attributeEntry._readOnly;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AttributeEntry(InterfaceEntry interfaceEntry, IDLID idlid) {
        super(interfaceEntry, idlid);
        this._readOnly = false;
    }

    @Override // com.sun.tools.corba.se.idl.MethodEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new AttributeEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.MethodEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        attributeGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.MethodEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return attributeGen;
    }

    public boolean readOnly() {
        return this._readOnly;
    }

    public void readOnly(boolean z) {
        this._readOnly = z;
    }
}
