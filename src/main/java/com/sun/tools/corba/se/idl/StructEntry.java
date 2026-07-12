package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/StructEntry.class */
public class StructEntry extends SymtabEntry {
    private Vector _members;
    private Vector _contained;
    static StructGen structGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public StructEntry() {
        this._members = new Vector();
        this._contained = new Vector();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StructEntry(StructEntry structEntry) {
        super(structEntry);
        this._members = new Vector();
        this._contained = new Vector();
        if (!name().equals("")) {
            module(module() + name());
            name("");
        }
        this._members = (Vector) structEntry._members.clone();
        this._contained = (Vector) structEntry._contained.clone();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StructEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        this._members = new Vector();
        this._contained = new Vector();
        if (module().equals("")) {
            module(name());
        } else if (!name().equals("")) {
            module(module() + RuntimeConstants.SIG_PACKAGE + name());
        }
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new StructEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        structGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return structGen;
    }

    public void addMember(TypedefEntry typedefEntry) {
        this._members.addElement(typedefEntry);
    }

    public Vector members() {
        return this._members;
    }

    public void addContained(SymtabEntry symtabEntry) {
        this._contained.addElement(symtabEntry);
    }

    public Vector contained() {
        return this._contained;
    }
}
