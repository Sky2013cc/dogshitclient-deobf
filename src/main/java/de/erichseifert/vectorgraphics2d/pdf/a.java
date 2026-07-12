package de.erichseifert.vectorgraphics2d.pdf;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/pdf/a.class */
class a implements PDFObject {
    public final Map<String, Object> a = new LinkedHashMap();
    public final c b;
    public final boolean c;

    public a(Map<String, Object> map, c cVar, boolean z) {
        this.b = cVar;
        this.c = z;
        if (map != null) {
            this.a.putAll(map);
        }
    }
}
