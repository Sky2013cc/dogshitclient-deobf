package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ValueGen.class */
public interface ValueGen extends Generator {
    void generate(Hashtable hashtable, ValueEntry valueEntry, PrintWriter printWriter);
}
