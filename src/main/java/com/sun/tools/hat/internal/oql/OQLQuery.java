package com.sun.tools.hat.internal.oql;

/* loaded from: target.jar:com/sun/tools/hat/internal/oql/OQLQuery.class */
class OQLQuery {
    String selectExpr;
    boolean isInstanceOf;
    String className;
    String identifier;
    String whereExpr;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OQLQuery(String str, boolean z, String str2, String str3, String str4) {
        this.selectExpr = str;
        this.isInstanceOf = z;
        this.className = str2;
        this.identifier = str3;
        this.whereExpr = str4;
    }
}
