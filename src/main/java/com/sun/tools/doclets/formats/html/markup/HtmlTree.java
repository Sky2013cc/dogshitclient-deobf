package com.sun.tools.doclets.formats.html.markup;

import com.sun.tools.doclets.formats.html.markup.HtmlTag;
import com.sun.tools.doclets.internal.toolkit.Content;
import com.sun.tools.doclets.internal.toolkit.util.DocletConstants;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: target.jar:com/sun/tools/doclets/formats/html/markup/HtmlTree.class */
public class HtmlTree extends Content {
    private HtmlTag htmlTag;
    private Map<HtmlAttr, String> attrs;
    private List<Content> content;
    public static final Content EMPTY = new StringContent("");
    public static final BitSet NONENCODING_CHARS = new BitSet(256);

    static {
        for (int i = 97; i <= 122; i++) {
            NONENCODING_CHARS.set(i);
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            NONENCODING_CHARS.set(i2);
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            NONENCODING_CHARS.set(i3);
        }
        String str = ":/?#[]@!$&'()*+,;=-._~";
        for (int i4 = 0; i4 < str.length(); i4++) {
            NONENCODING_CHARS.set(str.charAt(i4));
        }
    }

    public HtmlTree(HtmlTag htmlTag) {
        this.attrs = Collections.emptyMap();
        this.content = Collections.emptyList();
        this.htmlTag = (HtmlTag) nullCheck(htmlTag);
    }

    public HtmlTree(HtmlTag htmlTag, Content... contentArr) {
        this(htmlTag);
        for (Content content : contentArr) {
            addContent(content);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addAttr(HtmlAttr htmlAttr, String str) {
        if (this.attrs.isEmpty()) {
            this.attrs = new LinkedHashMap(3);
        }
        this.attrs.put(nullCheck(htmlAttr), escapeHtmlChars(str));
    }

    public void setTitle(Content content) {
        addAttr(HtmlAttr.TITLE, stripHtml(content));
    }

    public void addStyle(HtmlStyle htmlStyle) {
        addAttr(HtmlAttr.CLASS, htmlStyle.toString());
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public void addContent(Content content) {
        if (content instanceof ContentBuilder) {
            Iterator<Content> it = ((ContentBuilder) content).contents.iterator();
            while (it.hasNext()) {
                addContent(it.next());
            }
        } else if (content == EMPTY || content.isValid()) {
            if (this.content.isEmpty()) {
                this.content = new ArrayList();
            }
            this.content.add(content);
        }
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public void addContent(String str) {
        if (!this.content.isEmpty()) {
            Content content = this.content.get(this.content.size() - 1);
            if (content instanceof StringContent) {
                content.addContent(str);
                return;
            } else {
                addContent(new StringContent(str));
                return;
            }
        }
        addContent(new StringContent(str));
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public int charCount() {
        int i = 0;
        Iterator<Content> it = this.content.iterator();
        while (it.hasNext()) {
            i += it.next().charCount();
        }
        return i;
    }

    private static String escapeHtmlChars(String str) {
        int i = 0;
        while (i < str.length()) {
            switch (str.charAt(i)) {
                case '&':
                case '<':
                case '>':
                    StringBuilder sb = new StringBuilder(str.substring(0, i));
                    while (i < str.length()) {
                        char charAt = str.charAt(i);
                        switch (charAt) {
                            case '&':
                                sb.append("&amp;");
                                break;
                            case '<':
                                sb.append("&lt;");
                                break;
                            case '>':
                                sb.append("&gt;");
                                break;
                            default:
                                sb.append(charAt);
                                break;
                        }
                        i++;
                    }
                    return sb.toString();
                default:
                    i++;
            }
        }
        return str;
    }

    private static String encodeURL(String str) {
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            if (NONENCODING_CHARS.get(b & 255)) {
                sb.append((char) b);
            } else {
                sb.append(String.format("%%%02X", Integer.valueOf(b & 255)));
            }
        }
        return sb.toString();
    }

    public static HtmlTree A(String str, Content content) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.A, (Content) nullCheck(content));
        htmlTree.addAttr(HtmlAttr.HREF, encodeURL(str));
        return htmlTree;
    }

    public static HtmlTree A_NAME(String str, Content content) {
        HtmlTree A_NAME = A_NAME(str);
        A_NAME.addContent((Content) nullCheck(content));
        return A_NAME;
    }

    public static HtmlTree A_NAME(String str) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.A);
        htmlTree.addAttr(HtmlAttr.NAME, (String) nullCheck(str));
        return htmlTree;
    }

    public static HtmlTree CAPTION(Content content) {
        return new HtmlTree(HtmlTag.CAPTION, (Content) nullCheck(content));
    }

    public static HtmlTree CODE(Content content) {
        return new HtmlTree(HtmlTag.CODE, (Content) nullCheck(content));
    }

    public static HtmlTree DD(Content content) {
        return new HtmlTree(HtmlTag.DD, (Content) nullCheck(content));
    }

    public static HtmlTree DL(Content content) {
        return new HtmlTree(HtmlTag.DL, (Content) nullCheck(content));
    }

    public static HtmlTree DIV(HtmlStyle htmlStyle, Content content) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.DIV, (Content) nullCheck(content));
        if (htmlStyle != null) {
            htmlTree.addStyle(htmlStyle);
        }
        return htmlTree;
    }

    public static HtmlTree DIV(Content content) {
        return DIV(null, content);
    }

    public static HtmlTree DT(Content content) {
        return new HtmlTree(HtmlTag.DT, (Content) nullCheck(content));
    }

    public static HtmlTree FRAME(String str, String str2, String str3, String str4) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.FRAME);
        htmlTree.addAttr(HtmlAttr.SRC, (String) nullCheck(str));
        htmlTree.addAttr(HtmlAttr.NAME, (String) nullCheck(str2));
        htmlTree.addAttr(HtmlAttr.TITLE, (String) nullCheck(str3));
        if (str4 != null) {
            htmlTree.addAttr(HtmlAttr.SCROLLING, str4);
        }
        return htmlTree;
    }

    public static HtmlTree FRAME(String str, String str2, String str3) {
        return FRAME(str, str2, str3, null);
    }

    public static HtmlTree FRAMESET(String str, String str2, String str3, String str4) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.FRAMESET);
        if (str != null) {
            htmlTree.addAttr(HtmlAttr.COLS, str);
        }
        if (str2 != null) {
            htmlTree.addAttr(HtmlAttr.ROWS, str2);
        }
        htmlTree.addAttr(HtmlAttr.TITLE, (String) nullCheck(str3));
        htmlTree.addAttr(HtmlAttr.ONLOAD, (String) nullCheck(str4));
        return htmlTree;
    }

    public static HtmlTree HEADING(HtmlTag htmlTag, boolean z, HtmlStyle htmlStyle, Content content) {
        HtmlTree htmlTree = new HtmlTree(htmlTag, (Content) nullCheck(content));
        if (z) {
            htmlTree.setTitle(content);
        }
        if (htmlStyle != null) {
            htmlTree.addStyle(htmlStyle);
        }
        return htmlTree;
    }

    public static HtmlTree HEADING(HtmlTag htmlTag, HtmlStyle htmlStyle, Content content) {
        return HEADING(htmlTag, false, htmlStyle, content);
    }

    public static HtmlTree HEADING(HtmlTag htmlTag, boolean z, Content content) {
        return HEADING(htmlTag, z, null, content);
    }

    public static HtmlTree HEADING(HtmlTag htmlTag, Content content) {
        return HEADING(htmlTag, false, null, content);
    }

    public static HtmlTree HTML(String str, Content content, Content content2) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.HTML, (Content) nullCheck(content), (Content) nullCheck(content2));
        htmlTree.addAttr(HtmlAttr.LANG, (String) nullCheck(str));
        return htmlTree;
    }

    public static HtmlTree LI(Content content) {
        return LI(null, content);
    }

    public static HtmlTree LI(HtmlStyle htmlStyle, Content content) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.LI, (Content) nullCheck(content));
        if (htmlStyle != null) {
            htmlTree.addStyle(htmlStyle);
        }
        return htmlTree;
    }

    public static HtmlTree LINK(String str, String str2, String str3, String str4) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.LINK);
        htmlTree.addAttr(HtmlAttr.REL, (String) nullCheck(str));
        htmlTree.addAttr(HtmlAttr.TYPE, (String) nullCheck(str2));
        htmlTree.addAttr(HtmlAttr.HREF, (String) nullCheck(str3));
        htmlTree.addAttr(HtmlAttr.TITLE, (String) nullCheck(str4));
        return htmlTree;
    }

    public static HtmlTree META(String str, String str2, String str3) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.META);
        htmlTree.addAttr(HtmlAttr.HTTP_EQUIV, (String) nullCheck(str));
        htmlTree.addAttr(HtmlAttr.CONTENT, str2 + "; charset=" + str3);
        return htmlTree;
    }

    public static HtmlTree META(String str, String str2) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.META);
        htmlTree.addAttr(HtmlAttr.NAME, (String) nullCheck(str));
        htmlTree.addAttr(HtmlAttr.CONTENT, (String) nullCheck(str2));
        return htmlTree;
    }

    public static HtmlTree NOSCRIPT(Content content) {
        return new HtmlTree(HtmlTag.NOSCRIPT, (Content) nullCheck(content));
    }

    public static HtmlTree P(Content content) {
        return P(null, content);
    }

    public static HtmlTree P(HtmlStyle htmlStyle, Content content) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.P, (Content) nullCheck(content));
        if (htmlStyle != null) {
            htmlTree.addStyle(htmlStyle);
        }
        return htmlTree;
    }

    public static HtmlTree SCRIPT(String str, String str2) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.SCRIPT);
        htmlTree.addAttr(HtmlAttr.TYPE, (String) nullCheck(str));
        htmlTree.addAttr(HtmlAttr.SRC, (String) nullCheck(str2));
        return htmlTree;
    }

    public static HtmlTree SMALL(Content content) {
        return new HtmlTree(HtmlTag.SMALL, (Content) nullCheck(content));
    }

    public static HtmlTree SPAN(Content content) {
        return SPAN(null, content);
    }

    public static HtmlTree SPAN(HtmlStyle htmlStyle, Content content) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.SPAN, (Content) nullCheck(content));
        if (htmlStyle != null) {
            htmlTree.addStyle(htmlStyle);
        }
        return htmlTree;
    }

    public static HtmlTree SPAN(String str, HtmlStyle htmlStyle, Content content) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.SPAN, (Content) nullCheck(content));
        htmlTree.addAttr(HtmlAttr.ID, (String) nullCheck(str));
        if (htmlStyle != null) {
            htmlTree.addStyle(htmlStyle);
        }
        return htmlTree;
    }

    public static HtmlTree TABLE(HtmlStyle htmlStyle, int i, int i2, int i3, String str, Content content) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.TABLE, (Content) nullCheck(content));
        if (htmlStyle != null) {
            htmlTree.addStyle(htmlStyle);
        }
        htmlTree.addAttr(HtmlAttr.BORDER, Integer.toString(i));
        htmlTree.addAttr(HtmlAttr.CELLPADDING, Integer.toString(i2));
        htmlTree.addAttr(HtmlAttr.CELLSPACING, Integer.toString(i3));
        htmlTree.addAttr(HtmlAttr.SUMMARY, (String) nullCheck(str));
        return htmlTree;
    }

    public static HtmlTree TD(HtmlStyle htmlStyle, Content content) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.TD, (Content) nullCheck(content));
        if (htmlStyle != null) {
            htmlTree.addStyle(htmlStyle);
        }
        return htmlTree;
    }

    public static HtmlTree TD(Content content) {
        return TD(null, content);
    }

    public static HtmlTree TH(HtmlStyle htmlStyle, String str, Content content) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.TH, (Content) nullCheck(content));
        if (htmlStyle != null) {
            htmlTree.addStyle(htmlStyle);
        }
        htmlTree.addAttr(HtmlAttr.SCOPE, (String) nullCheck(str));
        return htmlTree;
    }

    public static HtmlTree TH(String str, Content content) {
        return TH(null, str, content);
    }

    public static HtmlTree TITLE(Content content) {
        return new HtmlTree(HtmlTag.TITLE, (Content) nullCheck(content));
    }

    public static HtmlTree TR(Content content) {
        return new HtmlTree(HtmlTag.TR, (Content) nullCheck(content));
    }

    public static HtmlTree UL(HtmlStyle htmlStyle, Content content) {
        HtmlTree htmlTree = new HtmlTree(HtmlTag.UL, (Content) nullCheck(content));
        htmlTree.addStyle((HtmlStyle) nullCheck(htmlStyle));
        return htmlTree;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean isEmpty() {
        return (hasContent() || hasAttrs()) ? false : true;
    }

    public boolean hasContent() {
        return !this.content.isEmpty();
    }

    public boolean hasAttrs() {
        return !this.attrs.isEmpty();
    }

    public boolean hasAttr(HtmlAttr htmlAttr) {
        return this.attrs.containsKey(htmlAttr);
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean isValid() {
        switch (this.htmlTag) {
            case A:
                return hasAttr(HtmlAttr.NAME) || (hasAttr(HtmlAttr.HREF) && hasContent());
            case BR:
                return !hasContent() && (!hasAttrs() || hasAttr(HtmlAttr.CLEAR));
            case FRAME:
                return hasAttr(HtmlAttr.SRC) && !hasContent();
            case HR:
                return !hasContent();
            case IMG:
                return hasAttr(HtmlAttr.SRC) && hasAttr(HtmlAttr.ALT) && !hasContent();
            case LINK:
                return hasAttr(HtmlAttr.HREF) && !hasContent();
            case META:
                return hasAttr(HtmlAttr.CONTENT) && !hasContent();
            case SCRIPT:
                return (hasAttr(HtmlAttr.TYPE) && hasAttr(HtmlAttr.SRC) && !hasContent()) || (hasAttr(HtmlAttr.TYPE) && hasContent());
            default:
                return hasContent();
        }
    }

    public boolean isInline() {
        return this.htmlTag.blockType == HtmlTag.BlockType.INLINE;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.Content
    public boolean write(Writer writer, boolean z) throws IOException {
        if (!isInline() && !z) {
            writer.write(DocletConstants.NL);
        }
        String htmlTag = this.htmlTag.toString();
        writer.write("<");
        writer.write(htmlTag);
        for (HtmlAttr htmlAttr : this.attrs.keySet()) {
            String str = this.attrs.get(htmlAttr);
            writer.write(" ");
            writer.write(htmlAttr.toString());
            if (!str.isEmpty()) {
                writer.write("=\"");
                writer.write(str);
                writer.write(OperatorName.SHOW_TEXT_LINE_AND_SPACE);
            }
        }
        writer.write(">");
        boolean z2 = false;
        Iterator<Content> it = this.content.iterator();
        while (it.hasNext()) {
            z2 = it.next().write(writer, z2);
        }
        if (this.htmlTag.endTagRequired()) {
            writer.write("</");
            writer.write(htmlTag);
            writer.write(">");
        }
        if (!isInline()) {
            writer.write(DocletConstants.NL);
            return true;
        }
        return false;
    }

    private static String stripHtml(Content content) {
        return content.toString().replaceAll("\\<.*?>", " ").replaceAll("\\b\\s{2,}\\b", " ").trim();
    }
}
