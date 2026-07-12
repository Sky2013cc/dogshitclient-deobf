package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDAppearanceHandler.class */
public interface PDAppearanceHandler {
    void generateNormalAppearance();

    void generateRolloverAppearance();

    void generateDownAppearance();

    default void generateAppearanceStreams() {
        generateNormalAppearance();
        generateRolloverAppearance();
        generateDownAppearance();
    }
}
