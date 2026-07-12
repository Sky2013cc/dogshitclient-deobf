package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/PrimitiveGen.class */
public interface PrimitiveGen extends Generator {
    void generate(Hashtable hashtable, PrimitiveEntry primitiveEntry, PrintWriter printWriter);
}
