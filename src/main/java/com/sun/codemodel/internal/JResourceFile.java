package com.sun.codemodel.internal;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: target.jar:com/sun/codemodel/internal/JResourceFile.class */
public abstract class JResourceFile {
    private final String name;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void build(OutputStream outputStream) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public JResourceFile(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isResource() {
        return true;
    }
}
