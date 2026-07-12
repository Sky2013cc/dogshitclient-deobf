package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ValueBoxGen.class */
public interface ValueBoxGen extends Generator {
    void generate(Hashtable hashtable, ValueBoxEntry valueBoxEntry, PrintWriter printWriter);
}
