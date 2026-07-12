package com.sun.tools.doclets.formats.html.markup;

import com.sun.tools.doclets.internal.toolkit.Content;
import com.sun.tools.doclets.internal.toolkit.util.DocletAbortException;
import com.sun.tools.doclets.internal.toolkit.util.DocletConstants;
import java.io.IOException;
import java.io.Writer;

/* loaded from: target.jar:com/sun/tools/doclets/formats/html/markup/RawHtml.class */
public class RawHtml extends Content {
    private String rawHtmlContent;
    public static final Content nbsp = new RawHtml("&nbsp;");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/doclets/formats/html/markup/RawHtml$State.class */
    public enum State {
        TEXT,
        ENTITY,
        TAG,
        STRING
    }

    public RawHtml(String str) {
        this.rawHtmlContent = (String) nullCheck(str);
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public void addContent(Content content) {
        throw new DocletAbortException("not supported");
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public void addContent(String str) {
        throw new DocletAbortException("not supported");
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean isEmpty() {
        return this.rawHtmlContent.isEmpty();
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public String toString() {
        return this.rawHtmlContent;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public int charCount() {
        return charCount(this.rawHtmlContent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int charCount(String str) {
        State state = State.TEXT;
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            switch (state) {
                case TEXT:
                    switch (charAt) {
                        case '&':
                            state = State.ENTITY;
                            i++;
                            break;
                        case '<':
                            state = State.TAG;
                            break;
                        default:
                            i++;
                            break;
                    }
                case ENTITY:
                    if (Character.isLetterOrDigit(charAt)) {
                        break;
                    } else {
                        state = State.TEXT;
                        break;
                    }
                case TAG:
                    switch (charAt) {
                        case '\"':
                            state = State.STRING;
                            break;
                        case '>':
                            state = State.TEXT;
                            break;
                    }
                case STRING:
                    switch (charAt) {
                        case '\"':
                            state = State.TAG;
                            break;
                    }
            }
        }
        return i;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean write(Writer writer, boolean z) throws IOException {
        writer.write(this.rawHtmlContent);
        return this.rawHtmlContent.endsWith(DocletConstants.NL);
    }
}
