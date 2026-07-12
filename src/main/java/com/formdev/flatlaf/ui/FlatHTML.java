package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JToolTip;
import javax.swing.event.DocumentEvent;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.text.AttributeSet;
import javax.swing.text.LabelView;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.StyleSheet;
import org.apache.fontbox.ttf.HeaderTable;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatHTML.class */
public class FlatHTML {
    static BiConsumer<JComponent, String> testUpdateRenderer;
    private static final Set<String> absoluteSizeKeywordsSet = new HashSet(Arrays.asList("xx-small", "x-small", FlatClientProperties.WINDOW_STYLE_SMALL, FlatClientProperties.MACOS_WINDOW_BUTTONS_SPACING_MEDIUM, FlatClientProperties.MACOS_WINDOW_BUTTONS_SPACING_LARGE, "x-large", "xx-large"));

    private FlatHTML() {
    }

    public static void updateRendererCSSFontBaseSize(JComponent c) {
        String text;
        int insertIndex;
        View view = (View) c.getClientProperty("html");
        if (view == null) {
            return;
        }
        HTMLDocument document = view.getDocument();
        if (!(document instanceof HTMLDocument)) {
            return;
        }
        StyleSheet styleSheet = document.getStyleSheet();
        Font font = c.getFont();
        if (styleSheet.getPointSize(7) != 36.0f || font == null || styleSheet.getPointSize(4) == font.getSize() || !usesAbsoluteSizeKeywordForFontSize(view)) {
            return;
        }
        if (c instanceof JLabel) {
            text = ((JLabel) c).getText();
        } else if (c instanceof AbstractButton) {
            text = ((AbstractButton) c).getText();
        } else if (c instanceof JToolTip) {
            text = ((JToolTip) c).getTipText();
        } else {
            return;
        }
        if (text == null || !BasicHTML.isHTMLString(text)) {
            return;
        }
        String style = "<style>BASE_SIZE " + font.getSize() + "</style>";
        String openTag = "";
        String closeTag = "";
        int headIndex = indexOfTag(text, HeaderTable.TAG, true);
        if (headIndex >= 0) {
            insertIndex = headIndex;
        } else {
            int styleIndex = indexOfTag(text, Constants.ATTR_STYLE, false);
            if (styleIndex >= 0) {
                insertIndex = styleIndex;
            } else {
                insertIndex = "<html>".length();
                openTag = "<head>";
                closeTag = "</head>";
            }
        }
        String newText = text.substring(0, insertIndex) + openTag + style + closeTag + text.substring(insertIndex);
        BasicHTML.updateRenderer(c, newText);
        if (testUpdateRenderer != null) {
            testUpdateRenderer.accept(c, newText);
        }
    }

    private static int indexOfTag(String html, String tag, boolean endIndex) {
        int tagLength = tag.length();
        int maxLength = (html.length() - tagLength) - 2;
        char lastTagChar = tag.charAt(tagLength - 1);
        for (int i = "<html>".length(); i < maxLength; i++) {
            if (html.charAt(i) == '<' && Character.toLowerCase(html.charAt(i + tagLength)) == lastTagChar) {
                for (int j = tagLength - 2; j >= 0 && Character.toLowerCase(html.charAt(i + 1 + j)) == tag.charAt(j); j--) {
                    if (j == 0) {
                        return endIndex ? html.indexOf(62, i + tagLength) + 1 : i;
                    }
                }
            }
        }
        return -1;
    }

    private static boolean usesAbsoluteSizeKeywordForFontSize(View view) {
        Object fontSize;
        AttributeSet attributes = view.getAttributes();
        if (attributes != null && (fontSize = attributes.getAttribute(CSS.Attribute.FONT_SIZE)) != null && absoluteSizeKeywordsSet.contains(fontSize.toString())) {
            return true;
        }
        int viewCount = view.getViewCount();
        for (int i = 0; i < viewCount; i++) {
            if (usesAbsoluteSizeKeywordForFontSize(view.getView(i))) {
                return true;
            }
        }
        return false;
    }

    public static void updateRendererCSSForeground(View view, Color foreground) {
        HTMLDocument document = view.getDocument();
        if (!(document instanceof HTMLDocument) || foreground == null) {
            return;
        }
        Style bodyStyle = document.getStyle("body");
        if (bodyStyle == null) {
            StyleSheet styleSheet = document.getStyleSheet();
            styleSheet.addRule(String.format("body { color: #%06x; }", Integer.valueOf(foreground.getRGB() & 16777215)));
            clearViewCaches(view);
        } else if (!foreground.equals(bodyStyle.getAttribute(StyleConstants.Foreground))) {
            bodyStyle.addAttribute(StyleConstants.Foreground, foreground);
            clearViewCaches(view);
        }
    }

    private static void clearViewCaches(View view) {
        if (view instanceof LabelView) {
            ((LabelView) view).changedUpdate((DocumentEvent) null, (Shape) null, (ViewFactory) null);
        }
        int viewCount = view.getViewCount();
        for (int i = 0; i < viewCount; i++) {
            clearViewCaches(view.getView(i));
        }
    }

    public static PropertyChangeListener createPropertyChangeListener(PropertyChangeListener superListener) {
        return e -> {
            if (superListener != null) {
                superListener.propertyChange(e);
            }
            propertyChange(e);
        };
    }

    public static void propertyChange(PropertyChangeEvent e) {
        if ("html".equals(e.getPropertyName()) && (e.getNewValue() instanceof View)) {
            updateRendererCSSFontBaseSize((JComponent) e.getSource());
        }
    }
}
