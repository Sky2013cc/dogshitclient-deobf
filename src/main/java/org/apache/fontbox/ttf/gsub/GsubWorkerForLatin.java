package org.apache.fontbox.ttf.gsub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.fontbox.ttf.CmapLookup;
import org.apache.fontbox.ttf.model.GsubData;
import org.apache.fontbox.ttf.model.ScriptFeature;

/* loaded from: target.jar:org/apache/fontbox/ttf/gsub/GsubWorkerForLatin.class */
public class GsubWorkerForLatin implements GsubWorker {
    private static final Log LOG = LogFactory.getLog(GsubWorkerForLatin.class);
    private static final List<String> FEATURES_IN_ORDER = Arrays.asList("ccmp", "liga", "clig");
    private final CmapLookup cmapLookup;
    private final GsubData gsubData;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GsubWorkerForLatin(CmapLookup cmapLookup, GsubData gsubData) {
        this.cmapLookup = cmapLookup;
        this.gsubData = gsubData;
    }

    @Override // org.apache.fontbox.ttf.gsub.GsubWorker
    public List<Integer> applyTransforms(List<Integer> originalGlyphIds) {
        List<Integer> intermediateGlyphsFromGsub = originalGlyphIds;
        for (String feature : FEATURES_IN_ORDER) {
            if (!this.gsubData.isFeatureSupported(feature)) {
                LOG.debug("the feature " + feature + " was not found");
            } else {
                LOG.debug("applying the feature " + feature);
                ScriptFeature scriptFeature = this.gsubData.getFeature(feature);
                intermediateGlyphsFromGsub = applyGsubFeature(scriptFeature, intermediateGlyphsFromGsub);
            }
        }
        return Collections.unmodifiableList(intermediateGlyphsFromGsub);
    }

    private List<Integer> applyGsubFeature(ScriptFeature scriptFeature, List<Integer> originalGlyphs) {
        if (scriptFeature.getAllGlyphIdsForSubstitution().isEmpty()) {
            LOG.debug("getAllGlyphIdsForSubstitution() for " + scriptFeature.getName() + " is empty");
            return originalGlyphs;
        }
        GlyphArraySplitter glyphArraySplitter = new GlyphArraySplitterRegexImpl(scriptFeature.getAllGlyphIdsForSubstitution());
        List<List<Integer>> tokens = glyphArraySplitter.split(originalGlyphs);
        List<Integer> gsubProcessedGlyphs = new ArrayList<>();
        for (List<Integer> chunk : tokens) {
            if (scriptFeature.canReplaceGlyphs(chunk)) {
                int glyphId = scriptFeature.getReplacementForGlyphs(chunk).intValue();
                gsubProcessedGlyphs.add(Integer.valueOf(glyphId));
            } else {
                gsubProcessedGlyphs.addAll(chunk);
            }
        }
        LOG.debug("originalGlyphs: " + originalGlyphs + ", gsubProcessedGlyphs: " + gsubProcessedGlyphs);
        return gsubProcessedGlyphs;
    }
}
