package com.sun.tools.doclets.formats.html.markup;

import com.sun.tools.doclets.internal.toolkit.Content;
import com.sun.tools.doclets.internal.toolkit.util.DocletAbortException;
import com.sun.tools.doclets.internal.toolkit.util.DocletConstants;
import java.io.IOException;
import java.io.Writer;

/* loaded from: target.jar:com/sun/tools/doclets/formats/html/markup/StringContent.class */
public class StringContent extends Content {
    private StringBuilder stringContent = new StringBuilder();

    public StringContent() {
    }

    public StringContent(String str) {
        appendChars(str);
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public void addContent(Content content) {
        throw new DocletAbortException("not supported");
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public void addContent(String str) {
        appendChars(str);
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean isEmpty() {
        return this.stringContent.length() == 0;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public int charCount() {
        return RawHtml.charCount(this.stringContent.toString());
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public String toString() {
        return this.stringContent.toString();
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean write(Writer writer, boolean z) throws IOException {
        String sb = this.stringContent.toString();
        writer.write(sb);
        return sb.endsWith(DocletConstants.NL);
    }

    private void appendChars(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '&':
                    this.stringContent.append("&amp;");
                    break;
                case '<':
                    this.stringContent.append("&lt;");
                    break;
                case '>':
                    this.stringContent.append("&gt;");
                    break;
                default:
                    this.stringContent.append(charAt);
                    break;
            }
        }
    }
}
