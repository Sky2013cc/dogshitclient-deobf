package org.apache.fontbox.ttf.gsub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.fontbox.ttf.CmapLookup;
import org.apache.fontbox.ttf.model.GsubData;
import org.apache.fontbox.ttf.model.ScriptFeature;

/* loaded from: target.jar:org/apache/fontbox/ttf/gsub/GsubWorkerForBengali.class */
public class GsubWorkerForBengali implements GsubWorker {
    private static final Log LOG = LogFactory.getLog(GsubWorkerForBengali.class);
    private static final String INIT_FEATURE = "init";
    private static final List<String> FEATURES_IN_ORDER = Arrays.asList("locl", "nukt", "akhn", "rphf", "blwf", "pstf", "half", "vatu", "cjct", INIT_FEATURE, "pres", "abvs", "blws", "psts", "haln", "calt");
    private static final char[] BEFORE_HALF_CHARS = {2495, 2503, 2504};
    private static final BeforeAndAfterSpanComponent[] BEFORE_AND_AFTER_SPAN_CHARS = {new BeforeAndAfterSpanComponent(2507, 2503, 2494), new BeforeAndAfterSpanComponent(2508, 2503, 2519)};
    private final CmapLookup cmapLookup;
    private final GsubData gsubData;
    private final List<Integer> beforeHalfGlyphIds = getBeforeHalfGlyphIds();
    private final Map<Integer, BeforeAndAfterSpanComponent> beforeAndAfterSpanGlyphIds = getBeforeAndAfterSpanGlyphIds();

    /* JADX INFO: Access modifiers changed from: package-private */
    public GsubWorkerForBengali(CmapLookup cmapLookup, GsubData gsubData) {
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
        return Collections.unmodifiableList(repositionGlyphs(intermediateGlyphsFromGsub));
    }

    private List<Integer> repositionGlyphs(List<Integer> originalGlyphIds) {
        List<Integer> glyphsRepositionedByBeforeHalf = repositionBeforeHalfGlyphIds(originalGlyphIds);
        return repositionBeforeAndAfterSpanGlyphIds(glyphsRepositionedByBeforeHalf);
    }

    private List<Integer> repositionBeforeHalfGlyphIds(List<Integer> originalGlyphIds) {
        List<Integer> repositionedGlyphIds = new ArrayList<>(originalGlyphIds);
        for (int index = 1; index < originalGlyphIds.size(); index++) {
            int glyphId = originalGlyphIds.get(index).intValue();
            if (this.beforeHalfGlyphIds.contains(Integer.valueOf(glyphId))) {
                int previousGlyphId = originalGlyphIds.get(index - 1).intValue();
                repositionedGlyphIds.set(index, Integer.valueOf(previousGlyphId));
                repositionedGlyphIds.set(index - 1, Integer.valueOf(glyphId));
            }
        }
        return repositionedGlyphIds;
    }

    private List<Integer> repositionBeforeAndAfterSpanGlyphIds(List<Integer> originalGlyphIds) {
        List<Integer> repositionedGlyphIds = new ArrayList<>(originalGlyphIds);
        for (int index = 1; index < originalGlyphIds.size(); index++) {
            int glyphId = originalGlyphIds.get(index).intValue();
            BeforeAndAfterSpanComponent beforeAndAfterSpanComponent = this.beforeAndAfterSpanGlyphIds.get(Integer.valueOf(glyphId));
            if (beforeAndAfterSpanComponent != null) {
                int previousGlyphId = originalGlyphIds.get(index - 1).intValue();
                repositionedGlyphIds.set(index, Integer.valueOf(previousGlyphId));
                repositionedGlyphIds.set(index - 1, getGlyphId(beforeAndAfterSpanComponent.beforeComponentCharacter));
                repositionedGlyphIds.add(index + 1, getGlyphId(beforeAndAfterSpanComponent.afterComponentCharacter));
            }
        }
        return repositionedGlyphIds;
    }

    private List<Integer> applyGsubFeature(ScriptFeature scriptFeature, List<Integer> originalGlyphs) {
        Set<List<Integer>> allGlyphIdsForSubstitution = scriptFeature.getAllGlyphIdsForSubstitution();
        if (allGlyphIdsForSubstitution.isEmpty()) {
            LOG.debug("getAllGlyphIdsForSubstitution() for " + scriptFeature.getName() + " is empty");
            return originalGlyphs;
        }
        GlyphArraySplitter glyphArraySplitter = new GlyphArraySplitterRegexImpl(allGlyphIdsForSubstitution);
        List<List<Integer>> tokens = glyphArraySplitter.split(originalGlyphs);
        List<Integer> gsubProcessedGlyphs = new ArrayList<>(tokens.size());
        tokens.forEach(chunk -> {
            if (scriptFeature.canReplaceGlyphs(chunk)) {
                Integer glyphId = scriptFeature.getReplacementForGlyphs(chunk);
                gsubProcessedGlyphs.add(glyphId);
            } else {
                gsubProcessedGlyphs.addAll(chunk);
            }
        });
        LOG.debug("originalGlyphs: " + originalGlyphs + ", gsubProcessedGlyphs: " + gsubProcessedGlyphs);
        return gsubProcessedGlyphs;
    }

    private List<Integer> getBeforeHalfGlyphIds() {
        List<Integer> glyphIds = new ArrayList<>(BEFORE_HALF_CHARS.length);
        for (char character : BEFORE_HALF_CHARS) {
            glyphIds.add(getGlyphId(character));
        }
        if (this.gsubData.isFeatureSupported(INIT_FEATURE)) {
            ScriptFeature feature = this.gsubData.getFeature(INIT_FEATURE);
            for (List<Integer> glyphCluster : feature.getAllGlyphIdsForSubstitution()) {
                glyphIds.add(feature.getReplacementForGlyphs(glyphCluster));
            }
        }
        return Collections.unmodifiableList(glyphIds);
    }

    private Integer getGlyphId(char character) {
        return Integer.valueOf(this.cmapLookup.getGlyphId(character));
    }

    private Map<Integer, BeforeAndAfterSpanComponent> getBeforeAndAfterSpanGlyphIds() {
        Map<Integer, BeforeAndAfterSpanComponent> result = new HashMap<>();
        for (BeforeAndAfterSpanComponent beforeAndAfterSpanComponent : BEFORE_AND_AFTER_SPAN_CHARS) {
            result.put(getGlyphId(beforeAndAfterSpanComponent.originalCharacter), beforeAndAfterSpanComponent);
        }
        return Collections.unmodifiableMap(result);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:org/apache/fontbox/ttf/gsub/GsubWorkerForBengali$BeforeAndAfterSpanComponent.class */
    public static class BeforeAndAfterSpanComponent {
        private final char originalCharacter;
        private final char beforeComponentCharacter;
        private final char afterComponentCharacter;

        BeforeAndAfterSpanComponent(char originalCharacter, char beforeComponentCharacter, char afterComponentCharacter) {
            this.originalCharacter = originalCharacter;
            this.beforeComponentCharacter = beforeComponentCharacter;
            this.afterComponentCharacter = afterComponentCharacter;
        }
    }
}
