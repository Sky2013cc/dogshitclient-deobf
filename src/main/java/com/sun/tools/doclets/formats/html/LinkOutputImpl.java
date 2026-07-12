package com.sun.tools.doclets.formats.html;

import com.sun.tools.doclets.internal.toolkit.util.links.LinkOutput;

/* loaded from: target.jar:com/sun/tools/doclets/formats/html/LinkOutputImpl.class */
public class LinkOutputImpl implements LinkOutput {
    public StringBuilder output = new StringBuilder();

    @Override // com.sun.tools.doclets.internal.toolkit.util.links.LinkOutput
    public void append(Object obj) {
        this.output.append(obj instanceof String ? (String) obj : ((LinkOutputImpl) obj).toString());
    }

    @Override // com.sun.tools.doclets.internal.toolkit.util.links.LinkOutput
    public void insert(int i, Object obj) {
        this.output.insert(i, obj.toString());
    }

    public String toString() {
        return this.output.toString();
    }
}
