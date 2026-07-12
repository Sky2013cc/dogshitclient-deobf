package org.apache.pdfbox.pdmodel;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/PageMode.class */
public enum PageMode {
    USE_NONE("UseNone"),
    USE_OUTLINES("UseOutlines"),
    USE_THUMBS("UseThumbs"),
    FULL_SCREEN("FullScreen"),
    USE_OPTIONAL_CONTENT("UseOC"),
    USE_ATTACHMENTS("UseAttachments");

    private final String value;

    public static PageMode fromString(String value) {
        for (PageMode instance : values()) {
            if (instance.value.equals(value)) {
                return instance;
            }
        }
        throw new IllegalArgumentException(value);
    }

    PageMode(String value) {
        this.value = value;
    }

    public String stringValue() {
        return this.value;
    }
}
