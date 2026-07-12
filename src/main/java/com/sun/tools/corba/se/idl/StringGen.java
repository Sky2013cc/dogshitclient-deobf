package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/StringGen.class */
public interface StringGen extends Generator {
    void generate(Hashtable hashtable, StringEntry stringEntry, PrintWriter printWriter);
}
