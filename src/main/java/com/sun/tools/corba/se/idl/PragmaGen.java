package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/PragmaGen.class */
public interface PragmaGen extends Generator {
    void generate(Hashtable hashtable, PragmaEntry pragmaEntry, PrintWriter printWriter);
}
