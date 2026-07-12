package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ParameterGen.class */
public interface ParameterGen extends Generator {
    void generate(Hashtable hashtable, ParameterEntry parameterEntry, PrintWriter printWriter);
}
