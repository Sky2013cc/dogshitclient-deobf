package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/PragmaEntry.class */
public class PragmaEntry extends SymtabEntry {
    static PragmaGen pragmaGen;
    private String _data;

    /* JADX INFO: Access modifiers changed from: protected */
    public PragmaEntry() {
        this._data = null;
        repositoryID(Util.emptyID);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PragmaEntry(SymtabEntry symtabEntry) {
        super(symtabEntry, new IDLID());
        this._data = null;
        module(symtabEntry.name());
        name("");
    }

    protected PragmaEntry(PragmaEntry pragmaEntry) {
        super(pragmaEntry);
        this._data = null;
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new PragmaEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        pragmaGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return pragmaGen;
    }

    public String data() {
        return this._data;
    }

    public void data(String str) {
        this._data = str;
    }
}
