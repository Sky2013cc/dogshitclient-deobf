package com.sun.tools.internal.ws.processor.util;

import com.sun.tools.internal.ws.processor.generator.GeneratorException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.MessageFormat;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/util/IndentingWriter.class */
public class IndentingWriter extends BufferedWriter {
    private boolean beginningOfLine;
    private int currentIndent;
    private int indentStep;

    public IndentingWriter(Writer out) {
        super(out);
        this.beginningOfLine = true;
        this.currentIndent = 0;
        this.indentStep = 4;
    }

    public IndentingWriter(Writer out, int step) {
        this(out);
        if (this.indentStep < 0) {
            throw new IllegalArgumentException("negative indent step");
        }
        this.indentStep = step;
    }

    @Override // java.io.BufferedWriter, java.io.Writer
    public void write(int c) throws IOException {
        checkWrite();
        super.write(c);
    }

    @Override // java.io.BufferedWriter, java.io.Writer
    public void write(char[] cbuf, int off, int len) throws IOException {
        if (len > 0) {
            checkWrite();
        }
        super.write(cbuf, off, len);
    }

    @Override // java.io.BufferedWriter, java.io.Writer
    public void write(String s, int off, int len) throws IOException {
        if (len > 0) {
            checkWrite();
        }
        super.write(s, off, len);
    }

    @Override // java.io.BufferedWriter
    public void newLine() throws IOException {
        super.newLine();
        this.beginningOfLine = true;
    }

    protected void checkWrite() throws IOException {
        if (this.beginningOfLine) {
            this.beginningOfLine = false;
            for (int i = this.currentIndent; i > 0; i--) {
                super.write(32);
            }
        }
    }

    protected void indentIn() {
        this.currentIndent += this.indentStep;
    }

    protected void indentOut() {
        this.currentIndent -= this.indentStep;
        if (this.currentIndent < 0) {
            this.currentIndent = 0;
        }
    }

    public void pI() {
        indentIn();
    }

    public void pO() {
        indentOut();
    }

    public void pI(int levels) {
        for (int i = 0; i < levels; i++) {
            indentIn();
        }
    }

    public void pO(int levels) {
        for (int i = 0; i < levels; i++) {
            indentOut();
        }
    }

    public void p(String s) throws IOException {
        boolean canEncode = true;
        try {
            if (!canEncode(s)) {
                canEncode = false;
            }
        } catch (Throwable th) {
        }
        if (!canEncode) {
            throw new GeneratorException("generator.indentingwriter.charset.cantencode", s);
        }
        write(s);
    }

    protected boolean canEncode(String s) {
        CharsetEncoder encoder = Charset.forName(System.getProperty("file.encoding")).newEncoder();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (!encoder.canEncode(c)) {
                return false;
            }
        }
        return true;
    }

    public void p(String s1, String s2) throws IOException {
        p(s1);
        p(s2);
    }

    public void p(String s1, String s2, String s3) throws IOException {
        p(s1);
        p(s2);
        p(s3);
    }

    public void p(String s1, String s2, String s3, String s4) throws IOException {
        p(s1);
        p(s2);
        p(s3);
        p(s4);
    }

    public void p(String s1, String s2, String s3, String s4, String s5) throws IOException {
        p(s1);
        p(s2);
        p(s3);
        p(s4);
        p(s5);
    }

    public void pln() throws IOException {
        newLine();
    }

    public void pln(String s) throws IOException {
        p(s);
        pln();
    }

    public void pln(String s1, String s2) throws IOException {
        p(s1, s2);
        pln();
    }

    public void pln(String s1, String s2, String s3) throws IOException {
        p(s1, s2, s3);
        pln();
    }

    public void pln(String s1, String s2, String s3, String s4) throws IOException {
        p(s1, s2, s3, s4);
        pln();
    }

    public void pln(String s1, String s2, String s3, String s4, String s5) throws IOException {
        p(s1, s2, s3, s4, s5);
        pln();
    }

    public void plnI(String s) throws IOException {
        p(s);
        pln();
        pI();
    }

    public void pO(String s) throws IOException {
        pO();
        p(s);
    }

    public void pOln(String s) throws IOException {
        pO(s);
        pln();
    }

    public void pOlnI(String s) throws IOException {
        pO(s);
        pln();
        pI();
    }

    public void p(Object o) throws IOException {
        write(o.toString());
    }

    public void pln(Object o) throws IOException {
        p(o.toString());
        pln();
    }

    public void plnI(Object o) throws IOException {
        p(o.toString());
        pln();
        pI();
    }

    public void pO(Object o) throws IOException {
        pO();
        p(o.toString());
    }

    public void pOln(Object o) throws IOException {
        pO(o.toString());
        pln();
    }

    public void pOlnI(Object o) throws IOException {
        pO(o.toString());
        pln();
        pI();
    }

    public void pM(String s) throws IOException {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < s.length()) {
                int j = s.indexOf(10, i2);
                if (j == -1) {
                    p(s.substring(i2));
                    return;
                } else {
                    pln(s.substring(i2, j));
                    i = j + 1;
                }
            } else {
                return;
            }
        }
    }

    public void pMln(String s) throws IOException {
        pM(s);
        pln();
    }

    public void pMlnI(String s) throws IOException {
        pM(s);
        pln();
        pI();
    }

    public void pMO(String s) throws IOException {
        pO();
        pM(s);
    }

    public void pMOln(String s) throws IOException {
        pMO(s);
        pln();
    }

    public void pF(String pattern, Object[] arguments) throws IOException {
        pM(MessageFormat.format(pattern, arguments));
    }

    public void pFln(String pattern, Object[] arguments) throws IOException {
        pF(pattern, arguments);
        pln();
    }
}
