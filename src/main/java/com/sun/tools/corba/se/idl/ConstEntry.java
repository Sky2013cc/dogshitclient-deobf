package com.sun.tools.corba.se.idl;

import com.sun.tools.corba.se.idl.constExpr.Expression;
import java.io.PrintWriter;
import java.util.Hashtable;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ConstEntry.class */
public class ConstEntry extends SymtabEntry {
    static ConstGen constGen;
    private Expression _value;

    /* JADX INFO: Access modifiers changed from: protected */
    public ConstEntry() {
        this._value = null;
    }

    protected ConstEntry(ConstEntry constEntry) {
        super(constEntry);
        this._value = null;
        if (module().equals("")) {
            module(name());
        } else if (!name().equals("")) {
            module(module() + RuntimeConstants.SIG_PACKAGE + name());
        }
        this._value = constEntry._value;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ConstEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        this._value = null;
        if (module().equals("")) {
            module(name());
        } else if (!name().equals("")) {
            module(module() + RuntimeConstants.SIG_PACKAGE + name());
        }
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new ConstEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        constGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return constGen;
    }

    public Expression value() {
        return this._value;
    }

    public void value(Expression expression) {
        this._value = expression;
    }
}
