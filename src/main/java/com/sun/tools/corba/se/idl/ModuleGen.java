package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ModuleGen.class */
public interface ModuleGen extends Generator {
    void generate(Hashtable hashtable, ModuleEntry moduleEntry, PrintWriter printWriter);
}
