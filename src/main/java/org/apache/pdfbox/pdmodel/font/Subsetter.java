package org.apache.pdfbox.pdmodel.font;

import java.io.IOException;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/font/Subsetter.class */
interface Subsetter {
    void addToSubset(int i);

    void subset() throws IOException;
}
