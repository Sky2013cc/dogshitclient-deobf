package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/EnumGen.class */
public interface EnumGen extends Generator {
    void generate(Hashtable hashtable, EnumEntry enumEntry, PrintWriter printWriter);
}
