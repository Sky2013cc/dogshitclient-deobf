package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/AttributeGen.class */
public interface AttributeGen extends Generator {
    void generate(Hashtable hashtable, AttributeEntry attributeEntry, PrintWriter printWriter);
}
