package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/MethodEntry.class */
public class MethodEntry extends SymtabEntry {
    private Vector _exceptionNames;
    private Vector _exceptions;
    private Vector _contexts;
    private Vector _parameters;
    private boolean _oneway;
    private boolean _valueMethod;
    static MethodGen methodGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public MethodEntry() {
        this._exceptionNames = new Vector();
        this._exceptions = new Vector();
        this._contexts = new Vector();
        this._parameters = new Vector();
        this._oneway = false;
        this._valueMethod = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MethodEntry(MethodEntry methodEntry) {
        super(methodEntry);
        this._exceptionNames = new Vector();
        this._exceptions = new Vector();
        this._contexts = new Vector();
        this._parameters = new Vector();
        this._oneway = false;
        this._valueMethod = false;
        this._exceptionNames = (Vector) methodEntry._exceptionNames.clone();
        this._exceptions = (Vector) methodEntry._exceptions.clone();
        this._contexts = (Vector) methodEntry._contexts.clone();
        this._parameters = (Vector) methodEntry._parameters.clone();
        this._oneway = methodEntry._oneway;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MethodEntry(InterfaceEntry interfaceEntry, IDLID idlid) {
        super(interfaceEntry, idlid);
        this._exceptionNames = new Vector();
        this._exceptions = new Vector();
        this._contexts = new Vector();
        this._parameters = new Vector();
        this._oneway = false;
        this._valueMethod = false;
        if (module().equals("")) {
            module(name());
        } else if (!name().equals("")) {
            module(module() + RuntimeConstants.SIG_PACKAGE + name());
        }
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new MethodEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        methodGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return methodGen;
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void type(SymtabEntry symtabEntry) {
        super.type(symtabEntry);
        if (symtabEntry == null) {
            typeName(Constants.IDL_VOID);
        }
    }

    public void addException(ExceptionEntry exceptionEntry) {
        this._exceptions.addElement(exceptionEntry);
    }

    public Vector exceptions() {
        return this._exceptions;
    }

    public void addExceptionName(String str) {
        this._exceptionNames.addElement(str);
    }

    public Vector exceptionNames() {
        return this._exceptionNames;
    }

    public void addContext(String str) {
        this._contexts.addElement(str);
    }

    public Vector contexts() {
        return this._contexts;
    }

    public void addParameter(ParameterEntry parameterEntry) {
        this._parameters.addElement(parameterEntry);
    }

    public Vector parameters() {
        return this._parameters;
    }

    public void oneway(boolean z) {
        this._oneway = z;
    }

    public boolean oneway() {
        return this._oneway;
    }

    public void valueMethod(boolean z) {
        this._valueMethod = z;
    }

    public boolean valueMethod() {
        return this._valueMethod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void exceptionsAddElement(ExceptionEntry exceptionEntry) {
        addException(exceptionEntry);
        addExceptionName(exceptionEntry.fullName());
    }
}
