package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ModuleEntry.class */
public class ModuleEntry extends SymtabEntry {
    private Vector _contained;
    static ModuleGen moduleGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public ModuleEntry() {
        this._contained = new Vector();
    }

    protected ModuleEntry(ModuleEntry moduleEntry) {
        super(moduleEntry);
        this._contained = new Vector();
        this._contained = (Vector) moduleEntry._contained.clone();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ModuleEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        this._contained = new Vector();
        if (module().equals("")) {
            module(name());
        } else if (!name().equals("")) {
            module(module() + RuntimeConstants.SIG_PACKAGE + name());
        }
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new ModuleEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        moduleGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return moduleGen;
    }

    public void addContained(SymtabEntry symtabEntry) {
        this._contained.addElement(symtabEntry);
    }

    public Vector contained() {
        return this._contained;
    }
}
