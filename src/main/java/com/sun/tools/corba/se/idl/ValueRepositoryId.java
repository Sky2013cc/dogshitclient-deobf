package com.sun.tools.corba.se.idl;

import java.security.MessageDigest;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/ValueRepositoryId.class */
public class ValueRepositoryId {
    private MessageDigest sha;
    private int index;
    private Hashtable types;
    private String hashcode;

    public ValueRepositoryId() {
        try {
            this.sha = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
        }
        this.index = 0;
        this.types = new Hashtable();
        this.hashcode = null;
    }

    public void addValue(int i) {
        this.sha.update((byte) ((i >> 24) & 15));
        this.sha.update((byte) ((i >> 16) & 15));
        this.sha.update((byte) ((i >> 8) & 15));
        this.sha.update((byte) (i & 15));
        this.index++;
    }

    public void addType(SymtabEntry symtabEntry) {
        this.types.put(symtabEntry, new Integer(this.index));
    }

    public boolean isNewType(SymtabEntry symtabEntry) {
        Object obj = this.types.get(symtabEntry);
        if (obj == null) {
            addType(symtabEntry);
            return true;
        }
        addValue(-1);
        addValue(((Integer) obj).intValue());
        return false;
    }

    public String getHashcode() {
        if (this.hashcode == null) {
            byte[] digest = this.sha.digest();
            this.hashcode = hexOf(digest[0]) + hexOf(digest[1]) + hexOf(digest[2]) + hexOf(digest[3]) + hexOf(digest[4]) + hexOf(digest[5]) + hexOf(digest[6]) + hexOf(digest[7]);
        }
        return this.hashcode;
    }

    private static String hexOf(byte b) {
        int i = (b >> 4) & 15;
        int i2 = b & 15;
        return "0123456789ABCDEF".substring(i, i + 1) + "0123456789ABCDEF".substring(i2, i2 + 1);
    }
}
