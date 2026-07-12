package com.sun.tools.corba.se.idl;

import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/IDLID.class */
public class IDLID extends RepositoryID {
    private String _prefix;
    private String _name;
    private String _version;

    public IDLID() {
        this._prefix = "";
        this._name = "";
        this._version = "1.0";
    }

    public IDLID(String str, String str2, String str3) {
        this._prefix = str;
        this._name = str2;
        this._version = str3;
    }

    @Override // com.sun.tools.corba.se.idl.RepositoryID
    public String ID() {
        if (this._prefix.equals("")) {
            return Constants.IDL_REPOSITORY_ID_PREFIX + this._name + ':' + this._version;
        }
        return Constants.IDL_REPOSITORY_ID_PREFIX + this._prefix + '/' + this._name + ':' + this._version;
    }

    public String prefix() {
        return this._prefix;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void prefix(String str) {
        if (str == null) {
            this._prefix = "";
        } else {
            this._prefix = str;
        }
    }

    public String name() {
        return this._name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void name(String str) {
        if (str == null) {
            this._name = "";
        } else {
            this._name = str;
        }
    }

    public String version() {
        return this._version;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void version(String str) {
        if (str == null) {
            this._version = "";
        } else {
            this._version = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void appendToName(String str) {
        if (str != null) {
            if (this._name.equals("")) {
                this._name = str;
            } else {
                this._name += '/' + str;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceName(String str) {
        if (str == null) {
            this._name = "";
            return;
        }
        int lastIndexOf = this._name.lastIndexOf(47);
        if (lastIndexOf < 0) {
            this._name = str;
        } else {
            this._name = this._name.substring(0, lastIndexOf + 1) + str;
        }
    }

    @Override // com.sun.tools.corba.se.idl.RepositoryID
    public Object clone() {
        return new IDLID(this._prefix, this._name, this._version);
    }
}
