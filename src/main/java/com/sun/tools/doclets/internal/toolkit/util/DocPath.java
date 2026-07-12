package com.sun.tools.doclets.internal.toolkit.util;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.PackageDoc;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/util/DocPath.class */
public class DocPath {
    private final String path;
    public static final DocPath empty = new DocPath("");
    public static final DocPath parent = new DocPath("..");

    public static DocPath create(String str) {
        return (str == null || str.isEmpty()) ? empty : new DocPath(str);
    }

    public static DocPath forClass(ClassDoc classDoc) {
        return classDoc == null ? empty : forPackage(classDoc.containingPackage()).resolve(forName(classDoc));
    }

    public static DocPath forName(ClassDoc classDoc) {
        return classDoc == null ? empty : new DocPath(classDoc.name() + ".html");
    }

    public static DocPath forPackage(ClassDoc classDoc) {
        return classDoc == null ? empty : forPackage(classDoc.containingPackage());
    }

    public static DocPath forPackage(PackageDoc packageDoc) {
        return packageDoc == null ? empty : create(packageDoc.name().replace('.', '/'));
    }

    public static DocPath forRoot(PackageDoc packageDoc) {
        String name = packageDoc == null ? "" : packageDoc.name();
        if (name.isEmpty()) {
            return empty;
        }
        return new DocPath(name.replace('.', '/').replaceAll("[^/]+", ".."));
    }

    public static DocPath relativePath(PackageDoc packageDoc, PackageDoc packageDoc2) {
        return forRoot(packageDoc).resolve(forPackage(packageDoc2));
    }

    protected DocPath(String str) {
        this.path = str.endsWith(RuntimeConstants.SIG_PACKAGE) ? str.substring(0, str.length() - 1) : str;
    }

    public boolean equals(Object obj) {
        return (obj instanceof DocPath) && this.path.equals(((DocPath) obj).path);
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    public DocPath basename() {
        int lastIndexOf = this.path.lastIndexOf(RuntimeConstants.SIG_PACKAGE);
        return lastIndexOf == -1 ? this : new DocPath(this.path.substring(lastIndexOf + 1));
    }

    public DocPath parent() {
        int lastIndexOf = this.path.lastIndexOf(RuntimeConstants.SIG_PACKAGE);
        return lastIndexOf == -1 ? empty : new DocPath(this.path.substring(0, lastIndexOf));
    }

    public DocPath resolve(String str) {
        if (str == null || str.isEmpty()) {
            return this;
        }
        if (this.path.isEmpty()) {
            return new DocPath(str);
        }
        return new DocPath(this.path + RuntimeConstants.SIG_PACKAGE + str);
    }

    public DocPath resolve(DocPath docPath) {
        if (docPath == null || docPath.isEmpty()) {
            return this;
        }
        if (this.path.isEmpty()) {
            return docPath;
        }
        return new DocPath(this.path + RuntimeConstants.SIG_PACKAGE + docPath.getPath());
    }

    public DocPath invert() {
        return new DocPath(this.path.replaceAll("[^/]+", ".."));
    }

    public boolean isEmpty() {
        return this.path.isEmpty();
    }

    public DocLink fragment(String str) {
        return new DocLink(this.path, (String) null, str);
    }

    public DocLink query(String str) {
        return new DocLink(this.path, str, (String) null);
    }

    public String getPath() {
        return this.path;
    }
}
