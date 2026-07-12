package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/IncludeGen.class */
public interface IncludeGen extends Generator {
    void generate(Hashtable hashtable, IncludeEntry includeEntry, PrintWriter printWriter);
}
