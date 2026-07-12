package org.apache.fontbox.ttf.model;

import java.util.Set;

/* loaded from: target.jar:org/apache/fontbox/ttf/model/GsubData.class */
public interface GsubData {
    public static final GsubData NO_DATA_FOUND = new GsubData() { // from class: org.apache.fontbox.ttf.model.GsubData.1
        @Override // org.apache.fontbox.ttf.model.GsubData
        public boolean isFeatureSupported(String featureName) {
            throw new UnsupportedOperationException();
        }

        @Override // org.apache.fontbox.ttf.model.GsubData
        public Language getLanguage() {
            throw new UnsupportedOperationException();
        }

        @Override // org.apache.fontbox.ttf.model.GsubData
        public ScriptFeature getFeature(String featureName) {
            throw new UnsupportedOperationException();
        }

        @Override // org.apache.fontbox.ttf.model.GsubData
        public String getActiveScriptName() {
            throw new UnsupportedOperationException();
        }

        @Override // org.apache.fontbox.ttf.model.GsubData
        public Set<String> getSupportedFeatures() {
            throw new UnsupportedOperationException();
        }
    };

    Language getLanguage();

    String getActiveScriptName();

    boolean isFeatureSupported(String str);

    ScriptFeature getFeature(String str);

    Set<String> getSupportedFeatures();
}
