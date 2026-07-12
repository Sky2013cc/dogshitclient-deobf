package org.apache.pdfbox.pdmodel.font;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.fontbox.cmap.CMap;
import org.apache.fontbox.cmap.CMapParser;
import org.apache.pdfbox.io.RandomAccessRead;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:org/apache/pdfbox/pdmodel/font/CMapManager.class */
public final class CMapManager {
    private static final Map<String, CMap> CMAP_CACHE = new ConcurrentHashMap();

    private CMapManager() {
    }

    public static CMap getPredefinedCMap(String cMapName) throws IOException {
        CMap cmap = CMAP_CACHE.get(cMapName);
        if (cmap != null) {
            return cmap;
        }
        CMap targetCmap = new CMapParser().parsePredefined(cMapName);
        CMAP_CACHE.put(targetCmap.getName(), targetCmap);
        return targetCmap;
    }

    public static CMap parseCMap(RandomAccessRead randomAccessRead) throws IOException {
        CMap targetCmap = null;
        if (randomAccessRead != null) {
            targetCmap = new CMapParser().parse(randomAccessRead);
        }
        return targetCmap;
    }
}
