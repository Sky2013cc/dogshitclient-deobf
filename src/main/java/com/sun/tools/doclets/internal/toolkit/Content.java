package com.sun.tools.doclets.internal.toolkit;

import com.sun.tools.doclets.internal.toolkit.util.DocletAbortException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/Content.class */
public abstract class Content {
    public abstract void addContent(Content content);

    public abstract void addContent(String str);

    public abstract boolean write(Writer writer, boolean z) throws IOException;

    public abstract boolean isEmpty();

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        try {
            write(stringWriter, true);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new DocletAbortException(e);
        }
    }

    public boolean isValid() {
        return !isEmpty();
    }

    public int charCount() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T> T nullCheck(T t) {
        t.getClass();
        return t;
    }
}
