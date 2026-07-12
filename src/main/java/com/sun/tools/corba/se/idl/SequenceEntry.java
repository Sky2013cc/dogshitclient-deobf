package com.sun.tools.corba.se.idl;

import com.sun.tools.corba.se.idl.constExpr.Expression;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/SequenceEntry.class */
public class SequenceEntry extends SymtabEntry {
    static SequenceGen sequenceGen;
    private Expression _maxSize;
    private Vector _contained;

    /* JADX INFO: Access modifiers changed from: protected */
    public SequenceEntry() {
        this._maxSize = null;
        this._contained = new Vector();
        repositoryID(Util.emptyID);
    }

    protected SequenceEntry(SequenceEntry sequenceEntry) {
        super(sequenceEntry);
        this._maxSize = null;
        this._contained = new Vector();
        this._maxSize = sequenceEntry._maxSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SequenceEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        this._maxSize = null;
        this._contained = new Vector();
        if (!(symtabEntry instanceof SequenceEntry)) {
            if (module().equals("")) {
                module(name());
            } else if (!name().equals("")) {
                module(module() + RuntimeConstants.SIG_PACKAGE + name());
            }
        }
        repositoryID(Util.emptyID);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new SequenceEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public boolean isReferencable() {
        return type().isReferencable();
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void isReferencable(boolean z) {
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        sequenceGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return sequenceGen;
    }

    public void maxSize(Expression expression) {
        this._maxSize = expression;
    }

    public Expression maxSize() {
        return this._maxSize;
    }

    public void addContained(SymtabEntry symtabEntry) {
        this._contained.addElement(symtabEntry);
    }

    public Vector contained() {
        return this._contained;
    }
}
