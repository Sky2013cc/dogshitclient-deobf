package com.sun.tools.corba.se.idl;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/RepositoryID.class */
public class RepositoryID {
    private String _id;

    public RepositoryID() {
        this._id = "";
    }

    public RepositoryID(String str) {
        this._id = str;
    }

    public String ID() {
        return this._id;
    }

    public Object clone() {
        return new RepositoryID(this._id);
    }

    public String toString() {
        return ID();
    }

    public static boolean hasValidForm(String str) {
        return str != null && str.indexOf(58) > 0;
    }
}
