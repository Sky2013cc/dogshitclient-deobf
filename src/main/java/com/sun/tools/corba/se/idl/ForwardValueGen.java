package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ForwardValueGen.class */
public interface ForwardValueGen extends Generator {
    void generate(Hashtable hashtable, ForwardValueEntry forwardValueEntry, PrintWriter printWriter);
}
