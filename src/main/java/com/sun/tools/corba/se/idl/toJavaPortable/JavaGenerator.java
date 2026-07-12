package com.sun.tools.corba.se.idl.toJavaPortable;

import com.sun.tools.corba.se.idl.SymtabEntry;
import java.io.PrintWriter;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/toJavaPortable/JavaGenerator.class */
public interface JavaGenerator {
    int helperType(int i, String str, TCOffsets tCOffsets, String str2, SymtabEntry symtabEntry, PrintWriter printWriter);

    void helperRead(String str, SymtabEntry symtabEntry, PrintWriter printWriter);

    void helperWrite(SymtabEntry symtabEntry, PrintWriter printWriter);

    int read(int i, String str, String str2, SymtabEntry symtabEntry, PrintWriter printWriter);

    int write(int i, String str, String str2, SymtabEntry symtabEntry, PrintWriter printWriter);

    int type(int i, String str, TCOffsets tCOffsets, String str2, SymtabEntry symtabEntry, PrintWriter printWriter);
}
