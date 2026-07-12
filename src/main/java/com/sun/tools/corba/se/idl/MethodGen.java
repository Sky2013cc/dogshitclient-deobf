package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/MethodGen.class */
public interface MethodGen extends Generator {
    void generate(Hashtable hashtable, MethodEntry methodEntry, PrintWriter printWriter);
}
