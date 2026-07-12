package org.apache.pdfbox.pdmodel.font;

import java.util.List;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/font/FontProvider.class */
public abstract class FontProvider {
    public abstract String toDebugString();

    public abstract List<? extends FontInfo> getFontInfo();
}
