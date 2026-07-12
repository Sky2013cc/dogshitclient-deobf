package de.erichseifert.vectorgraphics2d;

import de.erichseifert.vectorgraphics2d.util.PageSize;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/SizedDocument.class */
public abstract class SizedDocument implements Document {
    private final PageSize a;
    private final boolean b;

    public SizedDocument(PageSize pageSize, boolean z) {
        this.a = pageSize;
        this.b = z;
    }

    public PageSize getPageSize() {
        return this.a;
    }

    @Override // de.erichseifert.vectorgraphics2d.Document
    public boolean isCompressed() {
        return this.b;
    }
}
