package sun.rmi.rmic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

/* loaded from: target.jar:sun/rmi/rmic/IndentingWriter.class */
public class IndentingWriter extends BufferedWriter {
    private boolean beginningOfLine;
    private int currentIndent;
    private int indentStep;
    private int tabSize;

    public IndentingWriter(Writer writer) {
        super(writer);
        this.beginningOfLine = true;
        this.currentIndent = 0;
        this.indentStep = 4;
        this.tabSize = 8;
    }

    public IndentingWriter(Writer writer, int i) {
        this(writer);
        if (this.indentStep < 0) {
            throw new IllegalArgumentException("negative indent step");
        }
        this.indentStep = i;
    }

    public IndentingWriter(Writer writer, int i, int i2) {
        this(writer);
        if (this.indentStep < 0) {
            throw new IllegalArgumentException("negative indent step");
        }
        this.indentStep = i;
        this.tabSize = i2;
    }

    @Override // java.io.BufferedWriter, java.io.Writer
    public void write(int i) throws IOException {
        checkWrite();
        super.write(i);
    }

    @Override // java.io.BufferedWriter, java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        if (i2 > 0) {
            checkWrite();
        }
        super.write(cArr, i, i2);
    }

    @Override // java.io.BufferedWriter, java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        if (i2 > 0) {
            checkWrite();
        }
        super.write(str, i, i2);
    }

    @Override // java.io.BufferedWriter
    public void newLine() throws IOException {
        super.newLine();
        this.beginningOfLine = true;
    }

    protected void checkWrite() throws IOException {
        int i;
        if (this.beginningOfLine) {
            this.beginningOfLine = false;
            int i2 = this.currentIndent;
            while (true) {
                i = i2;
                if (i < this.tabSize) {
                    break;
                }
                super.write(9);
                i2 = i - this.tabSize;
            }
            while (i > 0) {
                super.write(32);
                i--;
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

    public void p(String str) throws IOException {
        write(str);
    }

    public void pln() throws IOException {
        newLine();
    }

    public void pln(String str) throws IOException {
        p(str);
        pln();
    }

    public void plnI(String str) throws IOException {
        p(str);
        pln();
        pI();
    }

    public void pO(String str) throws IOException {
        pO();
        p(str);
    }

    public void pOln(String str) throws IOException {
        pO(str);
        pln();
    }

    public void pOlnI(String str) throws IOException {
        pO(str);
        pln();
        pI();
    }

    public void p(Object obj) throws IOException {
        write(obj.toString());
    }

    public void pln(Object obj) throws IOException {
        p(obj.toString());
        pln();
    }

    public void plnI(Object obj) throws IOException {
        p(obj.toString());
        pln();
        pI();
    }

    public void pO(Object obj) throws IOException {
        pO();
        p(obj.toString());
    }

    public void pOln(Object obj) throws IOException {
        pO(obj.toString());
        pln();
    }

    public void pOlnI(Object obj) throws IOException {
        pO(obj.toString());
        pln();
        pI();
    }
}
