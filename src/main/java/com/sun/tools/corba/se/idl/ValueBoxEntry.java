package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ValueBoxEntry.class */
public class ValueBoxEntry extends ValueEntry {
    static ValueBoxGen valueBoxGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public ValueBoxEntry() {
    }

    protected ValueBoxEntry(ValueBoxEntry valueBoxEntry) {
        super(valueBoxEntry);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ValueBoxEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
    }

    @Override // com.sun.tools.corba.se.idl.ValueEntry, com.sun.tools.corba.se.idl.InterfaceEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new ValueBoxEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.ValueEntry, com.sun.tools.corba.se.idl.InterfaceEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        valueBoxGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.ValueEntry, com.sun.tools.corba.se.idl.InterfaceEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return valueBoxGen;
    }
}
