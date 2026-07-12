package org.apache.fontbox.ttf.model;

import java.util.List;
import java.util.Set;

/* loaded from: target.jar:org/apache/fontbox/ttf/model/ScriptFeature.class */
public interface ScriptFeature {
    String getName();

    Set<List<Integer>> getAllGlyphIdsForSubstitution();

    boolean canReplaceGlyphs(List<Integer> list);

    Integer getReplacementForGlyphs(List<Integer> list);
}
