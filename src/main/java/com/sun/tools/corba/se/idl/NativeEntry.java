package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/NativeEntry.class */
public class NativeEntry extends SymtabEntry {
    static NativeGen nativeGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public NativeEntry() {
        repositoryID(Util.emptyID);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public NativeEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        if (module().equals("")) {
            module(name());
        } else if (!name().equals("")) {
            module(module() + RuntimeConstants.SIG_PACKAGE + name());
        }
    }

    protected NativeEntry(NativeEntry nativeEntry) {
        super(nativeEntry);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new NativeEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        nativeGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return nativeGen;
    }
}
