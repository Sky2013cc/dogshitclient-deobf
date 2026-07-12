package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/PrimitiveEntry.class */
public class PrimitiveEntry extends SymtabEntry {
    static PrimitiveGen primitiveGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public PrimitiveEntry() {
        repositoryID(Util.emptyID);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PrimitiveEntry(String str) {
        name(str);
        module("");
        repositoryID(Util.emptyID);
    }

    protected PrimitiveEntry(PrimitiveEntry primitiveEntry) {
        super(primitiveEntry);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new PrimitiveEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        primitiveGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return primitiveGen;
    }
}
