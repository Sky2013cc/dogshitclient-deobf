package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/InterfaceEntry.class */
public class InterfaceEntry extends SymtabEntry implements InterfaceType {
    private Vector _derivedFromNames;
    private Vector _derivedFrom;
    private Vector _methods;
    Vector _allMethods;
    Vector forwardedDerivers;
    private Vector _contained;
    private Vector _state;
    private int _interfaceType;
    static InterfaceGen interfaceGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public InterfaceEntry() {
        this._derivedFromNames = new Vector();
        this._derivedFrom = new Vector();
        this._methods = new Vector();
        this._allMethods = new Vector();
        this.forwardedDerivers = new Vector();
        this._contained = new Vector();
        this._state = null;
        this._interfaceType = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public InterfaceEntry(InterfaceEntry interfaceEntry) {
        super(interfaceEntry);
        this._derivedFromNames = new Vector();
        this._derivedFrom = new Vector();
        this._methods = new Vector();
        this._allMethods = new Vector();
        this.forwardedDerivers = new Vector();
        this._contained = new Vector();
        this._state = null;
        this._interfaceType = 0;
        this._derivedFromNames = (Vector) interfaceEntry._derivedFromNames.clone();
        this._derivedFrom = (Vector) interfaceEntry._derivedFrom.clone();
        this._methods = (Vector) interfaceEntry._methods.clone();
        this._allMethods = (Vector) interfaceEntry._allMethods.clone();
        this.forwardedDerivers = (Vector) interfaceEntry.forwardedDerivers.clone();
        this._contained = (Vector) interfaceEntry._contained.clone();
        this._interfaceType = interfaceEntry._interfaceType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public InterfaceEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        this._derivedFromNames = new Vector();
        this._derivedFrom = new Vector();
        this._methods = new Vector();
        this._allMethods = new Vector();
        this.forwardedDerivers = new Vector();
        this._contained = new Vector();
        this._state = null;
        this._interfaceType = 0;
        if (module().equals("")) {
            module(name());
        } else if (!name().equals("")) {
            module(module() + RuntimeConstants.SIG_PACKAGE + name());
        }
    }

    public boolean isAbstract() {
        return this._interfaceType == 1;
    }

    public boolean isLocal() {
        return this._interfaceType == 2;
    }

    public boolean isLocalServant() {
        return this._interfaceType == 3;
    }

    public boolean isLocalSignature() {
        return this._interfaceType == 4;
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new InterfaceEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        interfaceGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return interfaceGen;
    }

    public void addDerivedFrom(SymtabEntry symtabEntry) {
        this._derivedFrom.addElement(symtabEntry);
    }

    public Vector derivedFrom() {
        return this._derivedFrom;
    }

    public void addDerivedFromName(String str) {
        this._derivedFromNames.addElement(str);
    }

    public Vector derivedFromNames() {
        return this._derivedFromNames;
    }

    public void addMethod(MethodEntry methodEntry) {
        this._methods.addElement(methodEntry);
    }

    public Vector methods() {
        return this._methods;
    }

    public void addContained(SymtabEntry symtabEntry) {
        this._contained.addElement(symtabEntry);
    }

    public Vector contained() {
        return this._contained;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void methodsAddElement(MethodEntry methodEntry, Scanner scanner) {
        if (verifyMethod(methodEntry, scanner, false)) {
            addMethod(methodEntry);
            this._allMethods.addElement(methodEntry);
            addToForwardedAllMethods(methodEntry, scanner);
        }
    }

    void addToForwardedAllMethods(MethodEntry methodEntry, Scanner scanner) {
        Enumeration elements = this.forwardedDerivers.elements();
        while (elements.hasMoreElements()) {
            InterfaceEntry interfaceEntry = (InterfaceEntry) elements.nextElement();
            if (interfaceEntry.verifyMethod(methodEntry, scanner, true)) {
                interfaceEntry._allMethods.addElement(methodEntry);
            }
        }
    }

    private boolean verifyMethod(MethodEntry methodEntry, Scanner scanner, boolean z) {
        boolean z2 = true;
        String lowerCase = methodEntry.name().toLowerCase();
        Enumeration elements = this._allMethods.elements();
        while (true) {
            if (!elements.hasMoreElements()) {
                break;
            }
            MethodEntry methodEntry2 = (MethodEntry) elements.nextElement();
            String lowerCase2 = methodEntry2.name().toLowerCase();
            if (methodEntry != methodEntry2 && lowerCase.equals(lowerCase2)) {
                if (z) {
                    ParseException.methodClash(scanner, fullName(), methodEntry.name());
                } else {
                    ParseException.alreadyDeclared(scanner, methodEntry.name());
                }
                z2 = false;
            }
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void derivedFromAddElement(SymtabEntry symtabEntry, Scanner scanner) {
        addDerivedFrom(symtabEntry);
        addDerivedFromName(symtabEntry.fullName());
        addParentType(symtabEntry, scanner);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addParentType(SymtabEntry symtabEntry, Scanner scanner) {
        if (symtabEntry instanceof ForwardEntry) {
            addToDerivers((ForwardEntry) symtabEntry);
            return;
        }
        InterfaceEntry interfaceEntry = (InterfaceEntry) symtabEntry;
        Enumeration elements = interfaceEntry._allMethods.elements();
        while (elements.hasMoreElements()) {
            MethodEntry methodEntry = (MethodEntry) elements.nextElement();
            if (verifyMethod(methodEntry, scanner, true)) {
                this._allMethods.addElement(methodEntry);
            }
            addToForwardedAllMethods(methodEntry, scanner);
        }
        lookForForwardEntrys(scanner, interfaceEntry);
    }

    private void lookForForwardEntrys(Scanner scanner, InterfaceEntry interfaceEntry) {
        Enumeration elements = interfaceEntry.derivedFrom().elements();
        while (elements.hasMoreElements()) {
            SymtabEntry symtabEntry = (SymtabEntry) elements.nextElement();
            if (symtabEntry instanceof ForwardEntry) {
                addToDerivers((ForwardEntry) symtabEntry);
            } else if (symtabEntry == interfaceEntry) {
                ParseException.selfInherit(scanner, interfaceEntry.fullName());
            } else {
                lookForForwardEntrys(scanner, (InterfaceEntry) symtabEntry);
            }
        }
    }

    public boolean replaceForwardDecl(ForwardEntry forwardEntry, InterfaceEntry interfaceEntry) {
        int indexOf = this._derivedFrom.indexOf(forwardEntry);
        if (indexOf >= 0) {
            this._derivedFrom.setElementAt(interfaceEntry, indexOf);
        }
        return indexOf >= 0;
    }

    private void addToDerivers(ForwardEntry forwardEntry) {
        forwardEntry.derivers.addElement(this);
        Enumeration elements = this.forwardedDerivers.elements();
        while (elements.hasMoreElements()) {
            forwardEntry.derivers.addElement((InterfaceEntry) elements.nextElement());
        }
    }

    public Vector state() {
        return this._state;
    }

    public void initState() {
        this._state = new Vector();
    }

    public void addStateElement(InterfaceState interfaceState, Scanner scanner) {
        if (this._state == null) {
            this._state = new Vector();
        }
        String name = interfaceState.entry.name();
        Enumeration elements = this._state.elements();
        while (elements.hasMoreElements()) {
            if (name.equals(((InterfaceState) elements.nextElement()).entry.name())) {
                ParseException.duplicateState(scanner, name);
            }
        }
        this._state.addElement(interfaceState);
    }

    @Override // com.sun.tools.corba.se.idl.InterfaceType
    public int getInterfaceType() {
        return this._interfaceType;
    }

    @Override // com.sun.tools.corba.se.idl.InterfaceType
    public void setInterfaceType(int i) {
        this._interfaceType = i;
    }

    public Vector allMethods() {
        return this._allMethods;
    }
}
