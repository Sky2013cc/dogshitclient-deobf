package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ForwardGen.class */
public interface ForwardGen extends Generator {
    void generate(Hashtable hashtable, ForwardEntry forwardEntry, PrintWriter printWriter);
}
