package com.sun.tools.corba.se.idl;

import com.sun.tools.corba.se.idl.constExpr.Expression;
import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/StringEntry.class */
public class StringEntry extends SymtabEntry {
    static StringGen stringGen;
    private Expression _maxSize;

    /* JADX INFO: Access modifiers changed from: protected */
    public StringEntry() {
        this._maxSize = null;
        String str = (String) Parser.overrideNames.get("string");
        if (str == null) {
            name("string");
        } else {
            name(str);
        }
        repositoryID(Util.emptyID);
    }

    protected StringEntry(StringEntry stringEntry) {
        super(stringEntry);
        this._maxSize = null;
        this._maxSize = stringEntry._maxSize;
    }

    protected StringEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        this._maxSize = null;
        module("");
        String str = (String) Parser.overrideNames.get("string");
        if (str == null) {
            name("string");
        } else {
            name(str);
        }
        repositoryID(Util.emptyID);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new StringEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        stringGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return stringGen;
    }

    public void maxSize(Expression expression) {
        this._maxSize = expression;
    }

    public Expression maxSize() {
        return this._maxSize;
    }
}
