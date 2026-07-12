package com.sun.codemodel.internal.fmt;

import com.sun.codemodel.internal.JResourceFile;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/* loaded from: target.jar:com/sun/codemodel/internal/fmt/JTextFile.class */
public class JTextFile extends JResourceFile {
    private String contents;

    public JTextFile(String name) {
        super(name);
        this.contents = null;
    }

    public void setContents(String _contents) {
        this.contents = _contents;
    }

    @Override // com.sun.codemodel.internal.JResourceFile
    public void build(OutputStream out) throws IOException {
        Writer w = new OutputStreamWriter(out);
        w.write(this.contents);
        w.close();
    }
}
