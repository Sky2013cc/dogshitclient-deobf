package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/IncludeEntry.class */
public class IncludeEntry extends SymtabEntry {
    static IncludeGen includeGen;
    private Vector includeList;
    private String _absFilename;

    /* JADX INFO: Access modifiers changed from: protected */
    public IncludeEntry() {
        this.includeList = new Vector();
        this._absFilename = null;
        repositoryID(Util.emptyID);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IncludeEntry(SymtabEntry symtabEntry) {
        super(symtabEntry, new IDLID());
        this.includeList = new Vector();
        this._absFilename = null;
        module(symtabEntry.name());
        name("");
    }

    protected IncludeEntry(IncludeEntry includeEntry) {
        super(includeEntry);
        this.includeList = new Vector();
        this._absFilename = null;
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new IncludeEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        includeGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return includeGen;
    }

    public void absFilename(String str) {
        this._absFilename = str;
    }

    public String absFilename() {
        return this._absFilename;
    }

    public void addInclude(IncludeEntry includeEntry) {
        this.includeList.addElement(includeEntry);
    }

    public Vector includes() {
        return this.includeList;
    }
}
