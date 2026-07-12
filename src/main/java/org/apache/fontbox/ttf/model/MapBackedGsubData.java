package org.apache.fontbox.ttf.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: target.jar:org/apache/fontbox/ttf/model/MapBackedGsubData.class */
public class MapBackedGsubData implements GsubData {
    private final Language language;
    private final String activeScriptName;
    private final Map<String, Map<List<Integer>, Integer>> glyphSubstitutionMap;

    public MapBackedGsubData(Language language, String activeScriptName, Map<String, Map<List<Integer>, Integer>> glyphSubstitutionMap) {
        this.language = language;
        this.activeScriptName = activeScriptName;
        this.glyphSubstitutionMap = glyphSubstitutionMap;
    }

    @Override // org.apache.fontbox.ttf.model.GsubData
    public Language getLanguage() {
        return this.language;
    }

    @Override // org.apache.fontbox.ttf.model.GsubData
    public String getActiveScriptName() {
        return this.activeScriptName;
    }

    @Override // org.apache.fontbox.ttf.model.GsubData
    public boolean isFeatureSupported(String featureName) {
        return this.glyphSubstitutionMap.containsKey(featureName);
    }

    @Override // org.apache.fontbox.ttf.model.GsubData
    public ScriptFeature getFeature(String featureName) {
        if (!isFeatureSupported(featureName)) {
            throw new UnsupportedOperationException("The feature " + featureName + " is not supported!");
        }
        return new MapBackedScriptFeature(featureName, this.glyphSubstitutionMap.get(featureName));
    }

    @Override // org.apache.fontbox.ttf.model.GsubData
    public Set<String> getSupportedFeatures() {
        return this.glyphSubstitutionMap.keySet();
    }
}
