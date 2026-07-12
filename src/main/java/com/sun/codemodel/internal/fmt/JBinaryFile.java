package com.sun.codemodel.internal.fmt;

import com.sun.codemodel.internal.JResourceFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: target.jar:com/sun/codemodel/internal/fmt/JBinaryFile.class */
public final class JBinaryFile extends JResourceFile {
    private final ByteArrayOutputStream baos;

    public JBinaryFile(String name) {
        super(name);
        this.baos = new ByteArrayOutputStream();
    }

    public OutputStream getDataStore() {
        return this.baos;
    }

    @Override // com.sun.codemodel.internal.JResourceFile
    public void build(OutputStream os) throws IOException {
        os.write(this.baos.toByteArray());
    }
}
