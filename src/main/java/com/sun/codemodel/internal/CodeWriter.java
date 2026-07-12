package com.sun.codemodel.internal;

import com.sun.codemodel.internal.util.EncoderFactory;
import com.sun.codemodel.internal.util.UnicodeEscapeWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.CharsetEncoder;

/* loaded from: target.jar:com/sun/codemodel/internal/CodeWriter.class */
public abstract class CodeWriter {
    protected String encoding = null;

    public abstract OutputStream openBinary(JPackage jPackage, String str) throws IOException;

    public abstract void close() throws IOException;

    public Writer openSource(JPackage pkg, String fileName) throws IOException {
        OutputStreamWriter outputStreamWriter;
        if (this.encoding != null) {
            outputStreamWriter = new OutputStreamWriter(openBinary(pkg, fileName), this.encoding);
        } else {
            outputStreamWriter = new OutputStreamWriter(openBinary(pkg, fileName));
        }
        final OutputStreamWriter bw = outputStreamWriter;
        try {
            return new UnicodeEscapeWriter(bw) { // from class: com.sun.codemodel.internal.CodeWriter.1
                private final CharsetEncoder encoder;

                {
                    this.encoder = EncoderFactory.createEncoder(bw.getEncoding());
                }

                @Override // com.sun.codemodel.internal.util.UnicodeEscapeWriter
                protected boolean requireEscaping(int ch) {
                    if (ch >= 32 || " \t\r\n".indexOf(ch) != -1) {
                        return ch >= 128 && !this.encoder.canEncode((char) ch);
                    }
                    return true;
                }
            };
        } catch (Throwable th) {
            return new UnicodeEscapeWriter(bw);
        }
    }
}
