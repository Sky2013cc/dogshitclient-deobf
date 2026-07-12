package com.sun.codemodel.internal.fmt;

import com.sun.codemodel.internal.JResourceFile;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/* loaded from: target.jar:com/sun/codemodel/internal/fmt/JSerializedObject.class */
public class JSerializedObject extends JResourceFile {
    private final Object obj;

    public JSerializedObject(String name, Object obj) throws IOException {
        super(name);
        this.obj = obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.codemodel.internal.JResourceFile
    public void build(OutputStream os) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.obj);
        oos.close();
    }
}
