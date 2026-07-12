package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ForwardValueEntry.class */
public class ForwardValueEntry extends ForwardEntry {
    static ForwardValueGen forwardValueGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public ForwardValueEntry() {
    }

    protected ForwardValueEntry(ForwardValueEntry forwardValueEntry) {
        super(forwardValueEntry);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ForwardValueEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
    }

    @Override // com.sun.tools.corba.se.idl.ForwardEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new ForwardValueEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.ForwardEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        forwardValueGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.ForwardEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return forwardValueGen;
    }
}
