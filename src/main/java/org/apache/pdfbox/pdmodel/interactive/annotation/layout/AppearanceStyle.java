package org.apache.pdfbox.pdmodel.interactive.annotation.layout;

import org.apache.pdfbox.pdmodel.font.PDFont;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/interactive/annotation/layout/AppearanceStyle.class */
public class AppearanceStyle {
    private PDFont font;
    private float fontSize = 12.0f;
    private float leading = 14.4f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDFont getFont() {
        return this.font;
    }

    public void setFont(PDFont font) {
        this.font = font;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
        this.leading = fontSize * 1.2f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getLeading() {
        return this.leading;
    }

    void setLeading(float leading) {
        this.leading = leading;
    }
}
