package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ForwardEntry.class */
public class ForwardEntry extends SymtabEntry implements InterfaceType {
    static ForwardGen forwardGen;
    Vector derivers;
    Vector types;
    private int _type;

    /* JADX INFO: Access modifiers changed from: protected */
    public ForwardEntry() {
        this.derivers = new Vector();
        this.types = new Vector();
        this._type = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ForwardEntry(ForwardEntry forwardEntry) {
        super(forwardEntry);
        this.derivers = new Vector();
        this.types = new Vector();
        this._type = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ForwardEntry(SymtabEntry symtabEntry, IDLID idlid) {
        super(symtabEntry, idlid);
        this.derivers = new Vector();
        this.types = new Vector();
        this._type = 0;
        if (module().equals("")) {
            module(name());
        } else if (!name().equals("")) {
            module(module() + RuntimeConstants.SIG_PACKAGE + name());
        }
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Object clone() {
        return new ForwardEntry(this);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public void generate(Hashtable hashtable, PrintWriter printWriter) {
        forwardGen.generate(hashtable, this, printWriter);
    }

    @Override // com.sun.tools.corba.se.idl.SymtabEntry
    public Generator generator() {
        return forwardGen;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean replaceForwardDecl(InterfaceEntry interfaceEntry) {
        boolean z = true;
        try {
            ForwardEntry forwardEntry = (ForwardEntry) Parser.symbolTable.get(interfaceEntry.fullName());
            if (forwardEntry != null) {
                z = interfaceEntry.getInterfaceType() == forwardEntry.getInterfaceType();
                forwardEntry.type(interfaceEntry);
                interfaceEntry.forwardedDerivers = forwardEntry.derivers;
                Enumeration elements = forwardEntry.derivers.elements();
                while (elements.hasMoreElements()) {
                    ((InterfaceEntry) elements.nextElement()).replaceForwardDecl(forwardEntry, interfaceEntry);
                }
                Enumeration elements2 = forwardEntry.types.elements();
                while (elements2.hasMoreElements()) {
                    ((SymtabEntry) elements2.nextElement()).type(interfaceEntry);
                }
            }
        } catch (Exception e) {
        }
        return z;
    }

    @Override // com.sun.tools.corba.se.idl.InterfaceType
    public int getInterfaceType() {
        return this._type;
    }

    @Override // com.sun.tools.corba.se.idl.InterfaceType
    public void setInterfaceType(int i) {
        this._type = i;
    }
}
