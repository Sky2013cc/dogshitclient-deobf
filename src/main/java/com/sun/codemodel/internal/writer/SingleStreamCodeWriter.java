package com.sun.codemodel.internal.writer;

import com.sun.codemodel.internal.CodeWriter;
import com.sun.codemodel.internal.JPackage;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/* loaded from: target.jar:com/sun/codemodel/internal/writer/SingleStreamCodeWriter.class */
public class SingleStreamCodeWriter extends CodeWriter {
    private final PrintStream out;

    public SingleStreamCodeWriter(OutputStream os) {
        this.out = new PrintStream(os);
    }

    @Override // com.sun.codemodel.internal.CodeWriter
    public OutputStream openBinary(JPackage pkg, String fileName) throws IOException {
        String pkgName = pkg.name();
        if (pkgName.length() != 0) {
            pkgName = pkgName + '.';
        }
        this.out.println("-----------------------------------" + pkgName + fileName + "-----------------------------------");
        return new FilterOutputStream(this.out) { // from class: com.sun.codemodel.internal.writer.SingleStreamCodeWriter.1
            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }
        };
    }

    @Override // com.sun.codemodel.internal.CodeWriter
    public void close() throws IOException {
        this.out.close();
    }
}
