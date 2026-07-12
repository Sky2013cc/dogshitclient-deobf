package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/SequenceGen.class */
public interface SequenceGen extends Generator {
    void generate(Hashtable hashtable, SequenceEntry sequenceEntry, PrintWriter printWriter);
}
