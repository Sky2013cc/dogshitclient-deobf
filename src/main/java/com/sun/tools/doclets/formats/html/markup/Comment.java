package com.sun.tools.doclets.formats.html.markup;

import com.sun.tools.doclets.internal.toolkit.Content;
import com.sun.tools.doclets.internal.toolkit.util.DocletAbortException;
import com.sun.tools.doclets.internal.toolkit.util.DocletConstants;
import java.io.IOException;
import java.io.Writer;

/* loaded from: target.jar:com/sun/tools/doclets/formats/html/markup/Comment.class */
public class Comment extends Content {
    private String commentText;

    public Comment(String str) {
        this.commentText = (String) nullCheck(str);
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
        return this.commentText.isEmpty();
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean write(Writer writer, boolean z) throws IOException {
        if (!z) {
            writer.write(DocletConstants.NL);
        }
        writer.write("<!-- ");
        writer.write(this.commentText);
        writer.write(" -->" + DocletConstants.NL);
        return true;
    }
}
