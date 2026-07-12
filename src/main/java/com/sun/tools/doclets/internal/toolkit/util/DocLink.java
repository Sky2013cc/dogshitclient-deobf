package com.sun.tools.doclets.internal.toolkit.util;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/util/DocLink.class */
public class DocLink {
    final String path;
    final String query;
    final String fragment;

    public static DocLink fragment(String str) {
        return new DocLink((String) null, (String) null, str);
    }

    public DocLink(DocPath docPath) {
        this(docPath.getPath(), (String) null, (String) null);
    }

    public DocLink(DocPath docPath, String str, String str2) {
        this(docPath.getPath(), str, str2);
    }

    public DocLink(String str, String str2, String str3) {
        this.path = str;
        this.query = str2;
        this.fragment = str3;
    }

    public String toString() {
        if (this.path != null && isEmpty(this.query) && isEmpty(this.fragment)) {
            return this.path;
        }
        StringBuilder sb = new StringBuilder();
        if (this.path != null) {
            sb.append(this.path);
        }
        if (!isEmpty(this.query)) {
            sb.append("?").append(this.query);
        }
        if (!isEmpty(this.fragment)) {
            sb.append("#").append(this.fragment);
        }
        return sb.toString();
    }

    private static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
