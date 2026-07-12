package com.sun.codemodel.internal.util;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/* loaded from: target.jar:com/sun/codemodel/internal/util/JavadocEscapeWriter.class */
public class JavadocEscapeWriter extends FilterWriter {
    public JavadocEscapeWriter(Writer next) {
        super(next);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int ch) throws IOException {
        if (ch == 60) {
            this.out.write("&lt;");
        } else if (ch == 38) {
            this.out.write("&amp;");
        } else {
            this.out.write(ch);
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] buf, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            write(buf[off + i]);
        }
    }

    @Override // java.io.Writer
    public void write(char[] buf) throws IOException {
        write(buf, 0, buf.length);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String buf, int off, int len) throws IOException {
        write(buf.toCharArray(), off, len);
    }

    @Override // java.io.Writer
    public void write(String buf) throws IOException {
        write(buf.toCharArray(), 0, buf.length());
    }
}
