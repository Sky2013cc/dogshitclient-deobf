package com.sun.tools.corba.se.idl.toJavaPortable;

import com.sun.tools.corba.se.idl.EnumEntry;
import com.sun.tools.corba.se.idl.InterfaceEntry;
import com.sun.tools.corba.se.idl.StringEntry;
import com.sun.tools.corba.se.idl.StructEntry;
import com.sun.tools.corba.se.idl.SymtabEntry;
import com.sun.tools.corba.se.idl.TypedefEntry;
import com.sun.tools.corba.se.idl.UnionEntry;
import java.util.Enumeration;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/toJavaPortable/TCOffsets.class */
public class TCOffsets {
    private Hashtable tcs = new Hashtable();
    private int offset = 0;

    public int offset(String str) {
        Integer num = (Integer) this.tcs.get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public void set(SymtabEntry symtabEntry) {
        if (symtabEntry == null) {
            this.offset += 8;
            return;
        }
        this.tcs.put(symtabEntry.fullName(), new Integer(this.offset));
        this.offset += 4;
        String stripLeadingUnderscoresFromID = Util.stripLeadingUnderscoresFromID(symtabEntry.repositoryID().ID());
        if (symtabEntry instanceof InterfaceEntry) {
            this.offset += alignStrLen(stripLeadingUnderscoresFromID) + alignStrLen(symtabEntry.name());
            return;
        }
        if (symtabEntry instanceof StructEntry) {
            this.offset += alignStrLen(stripLeadingUnderscoresFromID) + alignStrLen(symtabEntry.name()) + 4;
            return;
        }
        if (symtabEntry instanceof UnionEntry) {
            this.offset += alignStrLen(stripLeadingUnderscoresFromID) + alignStrLen(symtabEntry.name()) + 12;
            return;
        }
        if (symtabEntry instanceof EnumEntry) {
            this.offset += alignStrLen(stripLeadingUnderscoresFromID) + alignStrLen(symtabEntry.name()) + 4;
            Enumeration elements = ((EnumEntry) symtabEntry).elements().elements();
            while (elements.hasMoreElements()) {
                this.offset += alignStrLen((String) elements.nextElement());
            }
            return;
        }
        if (symtabEntry instanceof StringEntry) {
            this.offset += 4;
        } else if (symtabEntry instanceof TypedefEntry) {
            this.offset += alignStrLen(stripLeadingUnderscoresFromID) + alignStrLen(symtabEntry.name());
            if (((TypedefEntry) symtabEntry).arrayInfo().size() != 0) {
                this.offset += 8;
            }
        }
    }

    public int alignStrLen(String str) {
        int length = str.length() + 1;
        int i = 4 - (length % 4);
        if (i == 4) {
            i = 0;
        }
        return length + i + 4;
    }

    public void setMember(SymtabEntry symtabEntry) {
        this.offset += alignStrLen(symtabEntry.name());
        if (((TypedefEntry) symtabEntry).arrayInfo().size() != 0) {
            this.offset += 4;
        }
    }

    public int currentOffset() {
        return this.offset;
    }

    public void bumpCurrentOffset(int i) {
        this.offset += i;
    }
}
