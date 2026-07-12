package org.apache.fontbox.ttf.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: target.jar:org/apache/fontbox/ttf/model/MapBackedScriptFeature.class */
public class MapBackedScriptFeature implements ScriptFeature {
    private final String name;
    private final Map<List<Integer>, Integer> featureMap;

    public MapBackedScriptFeature(String name, Map<List<Integer>, Integer> featureMap) {
        this.name = name;
        this.featureMap = featureMap;
    }

    @Override // org.apache.fontbox.ttf.model.ScriptFeature
    public String getName() {
        return this.name;
    }

    @Override // org.apache.fontbox.ttf.model.ScriptFeature
    public Set<List<Integer>> getAllGlyphIdsForSubstitution() {
        return this.featureMap.keySet();
    }

    @Override // org.apache.fontbox.ttf.model.ScriptFeature
    public boolean canReplaceGlyphs(List<Integer> glyphIds) {
        return this.featureMap.containsKey(glyphIds);
    }

    @Override // org.apache.fontbox.ttf.model.ScriptFeature
    public Integer getReplacementForGlyphs(List<Integer> glyphIds) {
        if (!canReplaceGlyphs(glyphIds)) {
            throw new UnsupportedOperationException("The glyphs " + glyphIds + " cannot be replaced");
        }
        return this.featureMap.get(glyphIds);
    }

    public int hashCode() {
        int result = (31 * 1) + (this.featureMap == null ? 0 : this.featureMap.hashCode());
        return (31 * result) + (this.name == null ? 0 : this.name.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MapBackedScriptFeature other = (MapBackedScriptFeature) obj;
        if (this.featureMap == null) {
            if (other.featureMap != null) {
                return false;
            }
        } else if (!this.featureMap.equals(other.featureMap)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
            return true;
        }
        if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
