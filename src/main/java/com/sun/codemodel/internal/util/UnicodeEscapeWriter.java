package com.sun.codemodel.internal.util;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/* loaded from: target.jar:com/sun/codemodel/internal/util/UnicodeEscapeWriter.class */
public class UnicodeEscapeWriter extends FilterWriter {
    public UnicodeEscapeWriter(Writer next) {
        super(next);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public final void write(int ch) throws IOException {
        if (requireEscaping(ch)) {
            this.out.write("\\u");
            String s = Integer.toHexString(ch);
            for (int i = s.length(); i < 4; i++) {
                this.out.write(48);
            }
            this.out.write(s);
            return;
        }
        this.out.write(ch);
    }

    protected boolean requireEscaping(int ch) {
        if (ch >= 128) {
            return true;
        }
        return ch < 32 && " \t\r\n".indexOf(ch) == -1;
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public final void write(char[] buf, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            write(buf[off + i]);
        }
    }

    @Override // java.io.Writer
    public final void write(char[] buf) throws IOException {
        write(buf, 0, buf.length);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public final void write(String buf, int off, int len) throws IOException {
        write(buf.toCharArray(), off, len);
    }

    @Override // java.io.Writer
    public final void write(String buf) throws IOException {
        write(buf.toCharArray(), 0, buf.length());
    }
}
