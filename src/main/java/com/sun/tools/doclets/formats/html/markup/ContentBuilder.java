package com.sun.tools.doclets.formats.html.markup;

import com.sun.tools.doclets.internal.toolkit.Content;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: target.jar:com/sun/tools/doclets/formats/html/markup/ContentBuilder.class */
public class ContentBuilder extends Content {
    protected List<Content> contents = Collections.emptyList();

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public void addContent(Content content) {
        nullCheck(content);
        ensureMutableContents();
        if (content instanceof ContentBuilder) {
            this.contents.addAll(((ContentBuilder) content).contents);
        } else {
            this.contents.add(content);
        }
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public void addContent(String str) {
        StringContent stringContent;
        if (str.isEmpty()) {
            return;
        }
        ensureMutableContents();
        Content content = this.contents.isEmpty() ? null : this.contents.get(this.contents.size() - 1);
        if (content != null && (content instanceof StringContent)) {
            stringContent = (StringContent) content;
        } else {
            List<Content> list = this.contents;
            StringContent stringContent2 = new StringContent();
            stringContent = stringContent2;
            list.add(stringContent2);
        }
        stringContent.addContent(str);
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean write(Writer writer, boolean z) throws IOException {
        Iterator<Content> it = this.contents.iterator();
        while (it.hasNext()) {
            z = it.next().write(writer, z);
        }
        return z;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean isEmpty() {
        Iterator<Content> it = this.contents.iterator();
        while (it.hasNext()) {
            if (!it.next().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public int charCount() {
        int i = 0;
        Iterator<Content> it = this.contents.iterator();
        while (it.hasNext()) {
            i += it.next().charCount();
        }
        return i;
    }

    private void ensureMutableContents() {
        if (this.contents.isEmpty()) {
            this.contents = new ArrayList();
        }
    }
}
