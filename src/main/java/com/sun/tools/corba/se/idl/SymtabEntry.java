package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/SymtabEntry.class */
public class SymtabEntry {
    static Stack includeStack = new Stack();
    static boolean setEmit = true;
    static int maxKey = -1;
    private SymtabEntry _container;
    private String _module;
    private String _name;
    private String _typeName;
    private SymtabEntry _type;
    private IncludeEntry _sourceFile;
    private Object _info;
    private RepositoryID _repID;
    private boolean _emit;
    private Comment _comment;
    private Vector _dynamicVars;
    private boolean _isReferencable;

    public SymtabEntry() {
        this._container = null;
        this._module = "";
        this._name = "";
        this._typeName = "";
        this._type = null;
        this._sourceFile = null;
        this._info = null;
        this._repID = new IDLID("", "", "1.0");
        this._emit = setEmit;
        this._comment = null;
        this._isReferencable = true;
        initDynamicVars();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SymtabEntry(SymtabEntry symtabEntry, IDLID idlid) {
        this._container = null;
        this._module = "";
        this._name = "";
        this._typeName = "";
        this._type = null;
        this._sourceFile = null;
        this._info = null;
        this._repID = new IDLID("", "", "1.0");
        this._emit = setEmit;
        this._comment = null;
        this._isReferencable = true;
        this._module = symtabEntry._module;
        this._name = symtabEntry._name;
        this._type = symtabEntry._type;
        this._typeName = symtabEntry._typeName;
        this._sourceFile = symtabEntry._sourceFile;
        this._info = symtabEntry._info;
        this._repID = (RepositoryID) idlid.clone();
        ((IDLID) this._repID).appendToName(this._name);
        if ((symtabEntry instanceof InterfaceEntry) || (symtabEntry instanceof ModuleEntry) || (symtabEntry instanceof StructEntry) || (symtabEntry instanceof UnionEntry) || ((symtabEntry instanceof SequenceEntry) && (this instanceof SequenceEntry))) {
            this._container = symtabEntry;
        } else {
            this._container = symtabEntry._container;
        }
        initDynamicVars();
        this._comment = symtabEntry._comment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SymtabEntry(SymtabEntry symtabEntry) {
        this._container = null;
        this._module = "";
        this._name = "";
        this._typeName = "";
        this._type = null;
        this._sourceFile = null;
        this._info = null;
        this._repID = new IDLID("", "", "1.0");
        this._emit = setEmit;
        this._comment = null;
        this._isReferencable = true;
        this._module = symtabEntry._module;
        this._name = symtabEntry._name;
        this._type = symtabEntry._type;
        this._typeName = symtabEntry._typeName;
        this._sourceFile = symtabEntry._sourceFile;
        this._info = symtabEntry._info;
        this._repID = (RepositoryID) symtabEntry._repID.clone();
        this._container = symtabEntry._container;
        if (this._type instanceof ForwardEntry) {
            ((ForwardEntry) this._type).types.addElement(this);
        }
        initDynamicVars();
        this._comment = symtabEntry._comment;
    }

    void initDynamicVars() {
        this._dynamicVars = new Vector(maxKey + 1);
        for (int i = 0; i <= maxKey; i++) {
            this._dynamicVars.addElement(null);
        }
    }

    public Object clone() {
        return new SymtabEntry(this);
    }

    public final String fullName() {
        return this._module.equals("") ? this._name : this._module + '/' + this._name;
    }

    public String module() {
        return this._module;
    }

    public void module(String str) {
        if (str == null) {
            this._module = "";
        } else {
            this._module = str;
        }
    }

    public String name() {
        return this._name;
    }

    public void name(String str) {
        if (str == null) {
            this._name = "";
        } else {
            this._name = str;
        }
        if (this._repID instanceof IDLID) {
            ((IDLID) this._repID).replaceName(str);
        }
    }

    public String typeName() {
        return this._typeName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void typeName(String str) {
        this._typeName = str;
    }

    public SymtabEntry type() {
        return this._type;
    }

    public void type(SymtabEntry symtabEntry) {
        if (symtabEntry == null) {
            typeName("");
        } else {
            typeName(symtabEntry.fullName());
        }
        this._type = symtabEntry;
        if (this._type instanceof ForwardEntry) {
            ((ForwardEntry) this._type).types.addElement(this);
        }
    }

    public IncludeEntry sourceFile() {
        return this._sourceFile;
    }

    public void sourceFile(IncludeEntry includeEntry) {
        this._sourceFile = includeEntry;
    }

    public SymtabEntry container() {
        return this._container;
    }

    public void container(SymtabEntry symtabEntry) {
        if ((symtabEntry instanceof InterfaceEntry) || (symtabEntry instanceof ModuleEntry)) {
            this._container = symtabEntry;
        }
    }

    public RepositoryID repositoryID() {
        return this._repID;
    }

    public void repositoryID(RepositoryID repositoryID) {
        this._repID = repositoryID;
    }

    public boolean emit() {
        return this._emit && this._isReferencable;
    }

    public void emit(boolean z) {
        this._emit = z;
    }

    public Comment comment() {
        return this._comment;
    }

    public void comment(Comment comment) {
        this._comment = comment;
    }

    public boolean isReferencable() {
        return this._isReferencable;
    }

    public void isReferencable(boolean z) {
        this._isReferencable = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void enteringInclude() {
        includeStack.push(new Boolean(setEmit));
        setEmit = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void exitingInclude() {
        setEmit = ((Boolean) includeStack.pop()).booleanValue();
    }

    public static int getVariableKey() {
        int i = maxKey + 1;
        maxKey = i;
        return i;
    }

    public void dynamicVariable(int i, Object obj) throws NoSuchFieldException {
        if (i > maxKey) {
            throw new NoSuchFieldException(Integer.toString(i));
        }
        if (i >= this._dynamicVars.size()) {
            growVars();
        }
        this._dynamicVars.setElementAt(obj, i);
    }

    public Object dynamicVariable(int i) throws NoSuchFieldException {
        if (i > maxKey) {
            throw new NoSuchFieldException(Integer.toString(i));
        }
        if (i >= this._dynamicVars.size()) {
            growVars();
        }
        return this._dynamicVars.elementAt(i);
    }

    void growVars() {
        int size = (maxKey - this._dynamicVars.size()) + 1;
        for (int i = 0; i < size; i++) {
            this._dynamicVars.addElement(null);
        }
    }

    public void generate(Hashtable hashtable, PrintWriter printWriter) {
    }

    public Generator generator() {
        return null;
    }
}
