package com.sun.codemodel.internal.fmt;

import com.sun.codemodel.internal.JResourceFile;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/* loaded from: target.jar:com/sun/codemodel/internal/fmt/JPropertyFile.class */
public class JPropertyFile extends JResourceFile {
    private final Properties data;

    public JPropertyFile(String name) {
        super(name);
        this.data = new Properties();
    }

    public void add(String key, String value) {
        this.data.put(key, value);
    }

    @Override // com.sun.codemodel.internal.JResourceFile
    public void build(OutputStream out) throws IOException {
        this.data.store(out, (String) null);
    }
}
