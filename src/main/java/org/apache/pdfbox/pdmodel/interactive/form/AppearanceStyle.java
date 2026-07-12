package org.apache.pdfbox.pdmodel.interactive.form;

import org.apache.pdfbox.pdmodel.font.PDFont;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:org/apache/pdfbox/pdmodel/interactive/form/AppearanceStyle.class */
public class AppearanceStyle {
    private PDFont font;
    private float fontSize = 12.0f;
    private float leading = 14.4f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDFont getFont() {
        return this.font;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFont(PDFont font) {
        this.font = font;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFontSize() {
        return this.fontSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
        this.leading = fontSize * 1.2f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getLeading() {
        return this.leading;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLeading(float leading) {
        this.leading = leading;
    }
}
