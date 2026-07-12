package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/UnionGen.class */
public interface UnionGen extends Generator {
    void generate(Hashtable hashtable, UnionEntry unionEntry, PrintWriter printWriter);
}
