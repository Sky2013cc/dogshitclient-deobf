package com.sun.tools.corba.se.idl.som.cff;

import java.io.DataInputStream;
import java.io.InputStream;

/* compiled from: FileLocator.java */
/* loaded from: target.jar:com/sun/tools/corba/se/idl/som/cff/NamedDataInputStream.class */
class NamedDataInputStream extends DataInputStream {
    public String fullyQualifiedFileName;
    public boolean inZipFile;

    /* JADX INFO: Access modifiers changed from: protected */
    public NamedDataInputStream(InputStream inputStream, String str, boolean z) {
        super(inputStream);
        this.fullyQualifiedFileName = str;
        this.inZipFile = z;
    }
}
