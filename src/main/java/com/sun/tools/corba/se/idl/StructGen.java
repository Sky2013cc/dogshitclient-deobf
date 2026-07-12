package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/StructGen.class */
public interface StructGen extends Generator {
    void generate(Hashtable hashtable, StructEntry structEntry, PrintWriter printWriter);
}
