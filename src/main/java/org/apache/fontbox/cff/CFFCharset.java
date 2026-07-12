package org.apache.fontbox.cff;

/* loaded from: target.jar:org/apache/fontbox/cff/CFFCharset.class */
public interface CFFCharset {
    boolean isCIDFont();

    void addSID(int i, int i2, String str);

    void addCID(int i, int i2);

    int getSIDForGID(int i);

    int getGIDForSID(int i);

    int getGIDForCID(int i);

    int getSID(String str);

    String getNameForGID(int i);

    int getCIDForGID(int i);
}
