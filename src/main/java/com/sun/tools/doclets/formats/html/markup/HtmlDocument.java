package com.sun.tools.doclets.formats.html.markup;

import com.sun.tools.doclets.internal.toolkit.Content;
import com.sun.tools.doclets.internal.toolkit.util.DocletAbortException;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: target.jar:com/sun/tools/doclets/formats/html/markup/HtmlDocument.class */
public class HtmlDocument extends Content {
    private List<Content> docContent;

    public HtmlDocument(Content content, Content content2, Content content3) {
        this.docContent = Collections.emptyList();
        this.docContent = new ArrayList();
        addContent((Content) nullCheck(content));
        addContent((Content) nullCheck(content2));
        addContent((Content) nullCheck(content3));
    }

    public HtmlDocument(Content content, Content content2) {
        this.docContent = Collections.emptyList();
        this.docContent = new ArrayList();
        addContent((Content) nullCheck(content));
        addContent((Content) nullCheck(content2));
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public final void addContent(Content content) {
        if (content.isValid()) {
            this.docContent.add(content);
        }
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public void addContent(String str) {
        throw new DocletAbortException("not supported");
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean isEmpty() {
        return this.docContent.isEmpty();
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean write(Writer writer, boolean z) throws IOException {
        Iterator<Content> it = this.docContent.iterator();
        while (it.hasNext()) {
            z = it.next().write(writer, z);
        }
        return z;
    }
}
