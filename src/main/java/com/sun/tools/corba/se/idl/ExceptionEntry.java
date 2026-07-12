package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ExceptionEntry.class */
public class ExceptionEntry extends StructEntry {
    static ExceptionGen exceptionGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public ExceptionEntry() {
    }

    protected ExceptionEntry(ExceptionEntry exceptionEntry) {
        super(exceptionEntry);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ExceptionEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
    }

    @Override // com.sun.tools.corba.se.idl.StructEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new ExceptionEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.StructEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        exceptionGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.StructEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return exceptionGen;
    }
}
