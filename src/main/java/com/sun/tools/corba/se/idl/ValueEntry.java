package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ValueEntry.class */
public class ValueEntry extends InterfaceEntry {
    private Vector _supportsNames;
    private Vector _supports;
    private Vector _initializers;
    private boolean _custom;
    private boolean _isSafe;
    static ValueGen valueGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public ValueEntry() {
        this._supportsNames = new Vector();
        this._supports = new Vector();
        this._initializers = new Vector();
        this._custom = false;
        this._isSafe = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ValueEntry(ValueEntry valueEntry) {
        super(valueEntry);
        this._supportsNames = new Vector();
        this._supports = new Vector();
        this._initializers = new Vector();
        this._custom = false;
        this._isSafe = false;
        this._supportsNames = (Vector) valueEntry._supportsNames.clone();
        this._supports = (Vector) valueEntry._supports.clone();
        this._initializers = (Vector) valueEntry._initializers.clone();
        this._custom = valueEntry._custom;
        this._isSafe = valueEntry._isSafe;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ValueEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        this._supportsNames = new Vector();
        this._supports = new Vector();
        this._initializers = new Vector();
        this._custom = false;
        this._isSafe = false;
    }

    @Override // com.sun.tools.corba.se.idl.InterfaceEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new ValueEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.InterfaceEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        valueGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.InterfaceEntry, com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return valueGen;
    }

    public void addSupport(SymtabEntry symtabEntry) {
        this._supports.addElement(symtabEntry);
    }

    public Vector supports() {
        return this._supports;
    }

    public void addSupportName(String str) {
        this._supportsNames.addElement(str);
    }

    public Vector supportsNames() {
        return this._supportsNames;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public void derivedFromAddElement(SymtabEntry symtabEntry, boolean z, Scanner scanner) {
        if (((InterfaceType) symtabEntry).getInterfaceType() != 1) {
            if (isAbstract()) {
                ParseException.nonAbstractParent2(scanner, fullName(), symtabEntry.fullName());
            } else if (derivedFrom().size() > 0) {
                ParseException.nonAbstractParent3(scanner, fullName(), symtabEntry.fullName());
            }
        }
        if (derivedFrom().contains(symtabEntry)) {
            ParseException.alreadyDerived(scanner, symtabEntry.fullName(), fullName());
        }
        if (z) {
            this._isSafe = true;
        }
        addDerivedFrom(symtabEntry);
        addDerivedFromName(symtabEntry.fullName());
        addParentType(symtabEntry, scanner);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.corba.se.idl.InterfaceEntry
    public void derivedFromAddElement(SymtabEntry symtabEntry, Scanner scanner) {
        addSupport(symtabEntry);
        addSupportName(symtabEntry.fullName());
        addParentType(symtabEntry, scanner);
    }

    @Override // com.sun.tools.corba.se.idl.InterfaceEntry
    public boolean replaceForwardDecl(ForwardEntry forwardEntry, InterfaceEntry interfaceEntry) {
        if (super.replaceForwardDecl(forwardEntry, interfaceEntry)) {
            return true;
        }
        int indexOf = this._supports.indexOf(forwardEntry);
        if (indexOf >= 0) {
            this._supports.setElementAt(interfaceEntry, indexOf);
        }
        return indexOf >= 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initializersAddElement(MethodEntry methodEntry, Scanner scanner) {
        Vector parameters = methodEntry.parameters();
        int size = parameters.size();
        Enumeration elements = this._initializers.elements();
        while (elements.hasMoreElements()) {
            Vector parameters2 = ((MethodEntry) elements.nextElement()).parameters();
            if (size == parameters2.size()) {
                int i = 0;
                while (i < size && ((ParameterEntry) parameters.elementAt(i)).type().equals(((ParameterEntry) parameters2.elementAt(i)).type())) {
                    i++;
                }
                if (i >= size) {
                    ParseException.duplicateInit(scanner);
                }
            }
        }
        this._initializers.addElement(methodEntry);
    }

    public Vector initializers() {
        return this._initializers;
    }

    public void tagMethods() {
        Enumeration elements = methods().elements();
        while (elements.hasMoreElements()) {
            ((MethodEntry) elements.nextElement()).valueMethod(true);
        }
    }

    public boolean isCustom() {
        return this._custom;
    }

    public void setCustom(boolean z) {
        this._custom = z;
    }

    public boolean isSafe() {
        return this._isSafe;
    }
}
