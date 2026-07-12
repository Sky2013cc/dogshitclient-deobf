package com.sun.tools.corba.se.idl;

import com.sun.tools.corba.se.idl.constExpr.Expression;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/UnionEntry.class */
public class UnionEntry extends SymtabEntry {
    private Vector _branches;
    private TypedefEntry _defaultBranch;
    private Vector _contained;
    static UnionGen unionGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public UnionEntry() {
        this._branches = new Vector();
        this._defaultBranch = null;
        this._contained = new Vector();
    }

    protected UnionEntry(UnionEntry unionEntry) {
        super(unionEntry);
        this._branches = new Vector();
        this._defaultBranch = null;
        this._contained = new Vector();
        if (!name().equals("")) {
            module(module() + name());
            name("");
        }
        this._branches = (Vector) unionEntry._branches.clone();
        this._defaultBranch = unionEntry._defaultBranch;
        this._contained = unionEntry._contained;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UnionEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        this._branches = new Vector();
        this._defaultBranch = null;
        this._contained = new Vector();
        if (module().equals("")) {
            module(name());
        } else if (!name().equals("")) {
            module(module() + RuntimeConstants.SIG_PACKAGE + name());
        }
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new UnionEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        unionGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return unionGen;
    }

    public void addBranch(UnionBranch unionBranch) {
        this._branches.addElement(unionBranch);
    }

    public Vector branches() {
        return this._branches;
    }

    public void defaultBranch(TypedefEntry typedefEntry) {
        this._defaultBranch = typedefEntry;
    }

    public TypedefEntry defaultBranch() {
        return this._defaultBranch;
    }

    public void addContained(SymtabEntry symtabEntry) {
        this._contained.addElement(symtabEntry);
    }

    public Vector contained() {
        return this._contained;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean has(Expression expression) {
        Enumeration elements = this._branches.elements();
        while (elements.hasMoreElements()) {
            Enumeration elements2 = ((UnionBranch) elements.nextElement()).labels.elements();
            while (elements2.hasMoreElements()) {
                Expression expression2 = (Expression) elements2.nextElement();
                if (expression2.equals(expression) || expression2.value().equals(expression.value())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean has(TypedefEntry typedefEntry) {
        Enumeration elements = this._branches.elements();
        while (elements.hasMoreElements()) {
            UnionBranch unionBranch = (UnionBranch) elements.nextElement();
            if (!unionBranch.typedef.equals(typedefEntry) && unionBranch.typedef.name().equals(typedefEntry.name())) {
                return true;
            }
        }
        return false;
    }
}
