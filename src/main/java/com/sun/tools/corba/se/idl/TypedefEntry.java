package com.sun.tools.corba.se.idl;

import com.sun.tools.corba.se.idl.constExpr.Expression;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/TypedefEntry.class */
public class TypedefEntry extends SymtabEntry {
    private Vector _arrayInfo;
    static TypedefGen typedefGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public TypedefEntry() {
        this._arrayInfo = new Vector();
    }

    protected TypedefEntry(TypedefEntry typedefEntry) {
        super(typedefEntry);
        this._arrayInfo = new Vector();
        this._arrayInfo = (Vector) typedefEntry._arrayInfo.clone();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TypedefEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        this._arrayInfo = new Vector();
        if (module().equals("")) {
            module(name());
        } else if (!name().equals("")) {
            module(module() + RuntimeConstants.SIG_PACKAGE + name());
        }
    }

    public Vector arrayInfo() {
        return this._arrayInfo;
    }

    public void addArrayInfo(Expression expression) {
        this._arrayInfo.addElement(expression);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new TypedefEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        typedefGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public boolean isReferencable() {
        return type().isReferencable();
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void isReferencable(boolean z) {
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return typedefGen;
    }
}
