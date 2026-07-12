package com.sun.tools.doclets.formats.html.markup;

import com.sun.tools.doclets.internal.toolkit.Content;
import com.sun.tools.doclets.internal.toolkit.util.DocletAbortException;
import com.sun.tools.doclets.internal.toolkit.util.DocletConstants;
import java.io.IOException;
import java.io.Writer;

/* loaded from: target.jar:com/sun/tools/doclets/formats/html/markup/DocType.class */
public class DocType extends Content {
    private String docType;
    public static final DocType TRANSITIONAL = new DocType("Transitional", "http://www.w3.org/TR/html4/loose.dtd");
    public static final DocType FRAMESET = new DocType("Frameset", "http://www.w3.org/TR/html4/frameset.dtd");

    private DocType(String str, String str2) {
        this.docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 " + str + "//EN\" \"" + str2 + "\">" + DocletConstants.NL;
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
        return this.docType.length() == 0;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean write(Writer writer, boolean z) throws IOException {
        writer.write(this.docType);
        return true;
    }
}
