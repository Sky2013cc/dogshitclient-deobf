package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/NativeGen.class */
public interface NativeGen extends Generator {
    void generate(Hashtable hashtable, NativeEntry nativeEntry, PrintWriter printWriter);
}
