package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/InterfaceGen.class */
public interface InterfaceGen extends Generator {
    void generate(Hashtable hashtable, InterfaceEntry interfaceEntry, PrintWriter printWriter);
}
