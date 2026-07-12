package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/EnumEntry.class */
public class EnumEntry extends SymtabEntry {
    static EnumGen enumGen;
    private Vector _elements;

    /* JADX INFO: Access modifiers changed from: protected */
    public EnumEntry() {
        this._elements = new Vector();
    }

    protected EnumEntry(EnumEntry enumEntry) {
        super(enumEntry);
        this._elements = new Vector();
        this._elements = (Vector) enumEntry._elements.clone();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EnumEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        this._elements = new Vector();
        if (module().equals("")) {
            module(name());
        } else if (!name().equals("")) {
            module(module() + RuntimeConstants.SIG_PACKAGE + name());
        }
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new EnumEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        enumGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return enumGen;
    }

    public void addElement(String str) {
        this._elements.addElement(str);
    }

    public Vector elements() {
        return this._elements;
    }
}
