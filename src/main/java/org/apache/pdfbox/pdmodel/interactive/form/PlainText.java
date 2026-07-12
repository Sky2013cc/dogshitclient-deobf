package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.font.PDFont;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:org/apache/pdfbox/pdmodel/interactive/form/PlainText.class */
public class PlainText {
    private static final float FONTSCALE = 1000.0f;
    private final List<Paragraph> paragraphs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlainText(String textValue) {
        if (textValue.isEmpty()) {
            this.paragraphs = new ArrayList(1);
            this.paragraphs.add(new Paragraph(""));
            return;
        }
        String[] parts = textValue.replace('\t', ' ').split("\\r\\n|\\n|\\r|\\u2028|\\u2029");
        this.paragraphs = new ArrayList(parts.length);
        for (String part : parts) {
            if (part.isEmpty()) {
                part = " ";
            }
            this.paragraphs.add(new Paragraph(part));
        }
    }

    PlainText(List<String> listValue) {
        this.paragraphs = new ArrayList(listValue.size());
        listValue.forEach(part -> {
            this.paragraphs.add(new Paragraph(part));
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Paragraph> getParagraphs() {
        return this.paragraphs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/interactive/form/PlainText$TextAttribute.class */
    public static class TextAttribute extends AttributedCharacterIterator.Attribute {
        private static final long serialVersionUID = -3138885145941283005L;
        public static final AttributedCharacterIterator.Attribute WIDTH = new TextAttribute("width");

        protected TextAttribute(String name) {
            super(name);
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/interactive/form/PlainText$Paragraph.class */
    static class Paragraph {
        private final String textContent;

        Paragraph(String text) {
            this.textContent = text;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getText() {
            return this.textContent;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public List<Line> getLines(PDFont font, float fontSize, float width) throws IOException {
            String substring;
            float substringWidth;
            BreakIterator iterator = BreakIterator.getLineInstance();
            iterator.setText(this.textContent);
            float scale = fontSize / PlainText.FONTSCALE;
            int start = iterator.first();
            int end = iterator.next();
            float lineWidth = 0.0f;
            List<Line> textLines = new ArrayList<>();
            Line textLine = new Line();
            while (end != -1) {
                String word = this.textContent.substring(start, end);
                float wordWidth = font.getStringWidth(word) * scale;
                boolean wordNeedsSplit = false;
                int splitOffset = end - start;
                lineWidth += wordWidth;
                if (lineWidth >= width && Character.isWhitespace(word.charAt(word.length() - 1))) {
                    float whitespaceWidth = font.getStringWidth(word.substring(word.length() - 1)) * scale;
                    lineWidth -= whitespaceWidth;
                }
                if (lineWidth >= width && !textLine.getWords().isEmpty()) {
                    textLine.setWidth(textLine.calculateWidth(font, fontSize));
                    textLines.add(textLine);
                    textLine = new Line();
                    lineWidth = font.getStringWidth(word) * scale;
                }
                if (wordWidth > width && textLine.getWords().isEmpty()) {
                    wordNeedsSplit = true;
                    do {
                        splitOffset--;
                        substring = word.substring(0, splitOffset);
                        substringWidth = font.getStringWidth(substring) * scale;
                    } while (substringWidth >= width);
                    word = substring;
                    wordWidth = font.getStringWidth(word) * scale;
                    lineWidth = wordWidth;
                }
                AttributedString as = new AttributedString(word);
                as.addAttribute(TextAttribute.WIDTH, Float.valueOf(wordWidth));
                Word wordInstance = new Word(word);
                wordInstance.setAttributes(as);
                textLine.addWord(wordInstance);
                if (wordNeedsSplit) {
                    start += splitOffset;
                } else {
                    start = end;
                    end = iterator.next();
                }
            }
            textLine.setWidth(textLine.calculateWidth(font, fontSize));
            textLines.add(textLine);
            return textLines;
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/interactive/form/PlainText$Line.class */
    static class Line {
        private final List<Word> words = new ArrayList();
        private float lineWidth;

        Line() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public float getWidth() {
            return this.lineWidth;
        }

        void setWidth(float width) {
            this.lineWidth = width;
        }

        float calculateWidth(PDFont font, float fontSize) throws IOException {
            float scale = fontSize / PlainText.FONTSCALE;
            float calculatedWidth = 0.0f;
            int indexOfWord = 0;
            for (Word word : this.words) {
                calculatedWidth += ((Float) word.getAttributes().getIterator().getAttribute(TextAttribute.WIDTH)).floatValue();
                String text = word.getText();
                if (indexOfWord == this.words.size() - 1 && Character.isWhitespace(text.charAt(text.length() - 1))) {
                    float whitespaceWidth = font.getStringWidth(text.substring(text.length() - 1)) * scale;
                    calculatedWidth -= whitespaceWidth;
                }
                indexOfWord++;
            }
            return calculatedWidth;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public List<Word> getWords() {
            return this.words;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public float getInterWordSpacing(float width) {
            return (width - this.lineWidth) / (this.words.size() - 1);
        }

        void addWord(Word word) {
            this.words.add(word);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/interactive/form/PlainText$Word.class */
    public static class Word {
        private AttributedString attributedString;
        private final String textContent;

        Word(String text) {
            this.textContent = text;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getText() {
            return this.textContent;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public AttributedString getAttributes() {
            return this.attributedString;
        }

        void setAttributes(AttributedString as) {
            this.attributedString = as;
        }
    }
}
