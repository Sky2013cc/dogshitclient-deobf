package com.sun.codemodel.internal.writer;

import com.sun.codemodel.internal.CodeWriter;
import com.sun.codemodel.internal.JPackage;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* loaded from: target.jar:com/sun/codemodel/internal/writer/ZipCodeWriter.class */
public class ZipCodeWriter extends CodeWriter {
    private final ZipOutputStream zip;
    private final OutputStream filter;

    public ZipCodeWriter(OutputStream target) {
        this.zip = new ZipOutputStream(target);
        this.filter = new FilterOutputStream(this.zip) { // from class: com.sun.codemodel.internal.writer.ZipCodeWriter.1
            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }
        };
    }

    @Override // com.sun.codemodel.internal.CodeWriter
    public OutputStream openBinary(JPackage pkg, String fileName) throws IOException {
        String name = fileName;
        if (!pkg.isUnnamed()) {
            name = toDirName(pkg) + name;
        }
        this.zip.putNextEntry(new ZipEntry(name));
        return this.filter;
    }

    private static String toDirName(JPackage pkg) {
        return pkg.name().replace('.', '/') + '/';
    }

    @Override // com.sun.codemodel.internal.CodeWriter
    public void close() throws IOException {
        this.zip.close();
    }
}
