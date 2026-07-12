package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ExceptionGen.class */
public interface ExceptionGen extends Generator {
    void generate(Hashtable hashtable, ExceptionEntry exceptionEntry, PrintWriter printWriter);
}
