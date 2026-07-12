package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ConstGen.class */
public interface ConstGen extends Generator {
    void generate(Hashtable hashtable, ConstEntry constEntry, PrintWriter printWriter);
}
