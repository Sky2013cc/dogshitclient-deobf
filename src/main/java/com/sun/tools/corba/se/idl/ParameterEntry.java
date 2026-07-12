package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ParameterEntry.class */
public class ParameterEntry extends SymtabEntry {
    public static final int In = 0;
    public static final int Inout = 1;
    public static final int Out = 2;
    private int _passType;
    static ParameterGen parameterGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public ParameterEntry() {
        this._passType = 0;
    }

    protected ParameterEntry(ParameterEntry parameterEntry) {
        super(parameterEntry);
        this._passType = 0;
        this._passType = parameterEntry._passType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ParameterEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        this._passType = 0;
        if (module().equals("")) {
            module(name());
        } else if (!name().equals("")) {
            module(module() + RuntimeConstants.SIG_PACKAGE + name());
        }
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new ParameterEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        parameterGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return parameterGen;
    }

    public void passType(int i) {
        if (i >= 0 && i <= 2) {
            this._passType = i;
        }
    }

    public int passType() {
        return this._passType;
    }
}
