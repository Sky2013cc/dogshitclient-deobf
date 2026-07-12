package com.sun.tools.doclets.formats.html.markup;

import com.sun.tools.javac.util.StringUtils;

/* loaded from: target.jar:com/sun/tools/doclets/formats/html/markup/HtmlAttr.class */
public enum HtmlAttr {
    ALT,
    BORDER,
    CELLPADDING,
    CELLSPACING,
    CLASS,
    CLEAR,
    COLS,
    CONTENT,
    HREF,
    HTTP_EQUIV("http-equiv"),
    ID,
    LANG,
    NAME,
    ONLOAD,
    REL,
    ROWS,
    SCOPE,
    SCROLLING,
    SRC,
    SUMMARY,
    TARGET,
    TITLE,
    TYPE,
    WIDTH;

    private final String value;

    HtmlAttr() {
        this.value = StringUtils.toLowerCase(name());
    }

    HtmlAttr(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
