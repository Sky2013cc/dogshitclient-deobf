package org.apache.fontbox.ttf.gsub;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: target.jar:org/apache/fontbox/ttf/gsub/GlyphArraySplitterRegexImpl.class */
public class GlyphArraySplitterRegexImpl implements GlyphArraySplitter {
    private static final String GLYPH_ID_SEPARATOR = "_";
    private final CompoundCharacterTokenizer compoundCharacterTokenizer;

    public GlyphArraySplitterRegexImpl(Set<List<Integer>> matchers) {
        this.compoundCharacterTokenizer = new CompoundCharacterTokenizer(getMatchersAsStrings(matchers));
    }

    @Override // org.apache.fontbox.ttf.gsub.GlyphArraySplitter
    public List<List<Integer>> split(List<Integer> glyphIds) {
        String originalGlyphsAsText = convertGlyphIdsToString(glyphIds);
        List<String> tokens = this.compoundCharacterTokenizer.tokenize(originalGlyphsAsText);
        List<List<Integer>> modifiedGlyphs = new ArrayList<>(tokens.size());
        tokens.forEach(token -> {
            modifiedGlyphs.add(convertGlyphIdsToList(token));
        });
        return modifiedGlyphs;
    }

    private Set<String> getMatchersAsStrings(Set<List<Integer>> matchers) {
        Set<String> stringMatchers = new TreeSet<>((Comparator<? super String>) (s1, s2) -> {
            if (s1.length() == s2.length()) {
                return s2.compareTo(s1);
            }
            return s2.length() - s1.length();
        });
        matchers.forEach(glyphIds -> {
            stringMatchers.add(convertGlyphIdsToString(glyphIds));
        });
        return stringMatchers;
    }

    private String convertGlyphIdsToString(List<Integer> glyphIds) {
        StringBuilder sb = new StringBuilder(20);
        sb.append(GLYPH_ID_SEPARATOR);
        glyphIds.forEach(glyphId -> {
            sb.append(glyphId).append(GLYPH_ID_SEPARATOR);
        });
        return sb.toString();
    }

    private List<Integer> convertGlyphIdsToList(String glyphIdsAsString) {
        List<Integer> gsubProcessedGlyphsIds = new ArrayList<>();
        for (String str : glyphIdsAsString.split(GLYPH_ID_SEPARATOR)) {
            String glyphId = str.trim();
            if (!glyphId.isEmpty()) {
                gsubProcessedGlyphsIds.add(Integer.valueOf(glyphId));
            }
        }
        return gsubProcessedGlyphsIds;
    }
}
