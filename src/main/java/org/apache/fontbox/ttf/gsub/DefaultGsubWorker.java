package org.apache.fontbox.ttf.gsub;

import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* loaded from: target.jar:org/apache/fontbox/ttf/gsub/DefaultGsubWorker.class */
class DefaultGsubWorker implements GsubWorker {
    private static final Log LOG = LogFactory.getLog(DefaultGsubWorker.class);

    @Override // org.apache.fontbox.ttf.gsub.GsubWorker
    public List<Integer> applyTransforms(List<Integer> originalGlyphIds) {
        LOG.warn(getClass().getSimpleName() + " class does not perform actual GSUB substitutions. Perhaps the selected language is not yet supported by the FontBox library.");
        return Collections.unmodifiableList(originalGlyphIds);
    }
}
