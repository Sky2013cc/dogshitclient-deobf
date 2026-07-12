package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaField.class */
public class JavaField {
    private String name;
    private String signature;

    public JavaField(String str, String str2) {
        this.name = str;
        this.signature = str2;
    }

    public boolean hasId() {
        char charAt = this.signature.charAt(0);
        return charAt == '[' || charAt == 'L';
    }

    public String getName() {
        return this.name;
    }

    public String getSignature() {
        return this.signature;
    }
}
