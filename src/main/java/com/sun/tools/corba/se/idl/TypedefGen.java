package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/TypedefGen.class */
public interface TypedefGen extends Generator {
    void generate(Hashtable hashtable, TypedefEntry typedefEntry, PrintWriter printWriter);
}
