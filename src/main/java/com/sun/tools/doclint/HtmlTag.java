package com.sun.tools.doclint;

import com.sun.tools.javac.util.StringUtils;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Name;
import jdk.internal.dynalink.CallSiteDescriptor;

/* loaded from: target.jar:com/sun/tools/doclint/HtmlTag.class */
public enum HtmlTag {
    A(BlockType.INLINE, EndKind.REQUIRED, attrs(AttrKind.OK, Attr.HREF, Attr.TARGET, Attr.NAME)),
    B(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT, Flag.NO_NEST), new AttrMap[0]),
    BIG(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT), new AttrMap[0]),
    BLOCKQUOTE(BlockType.BLOCK, EndKind.REQUIRED, EnumSet.of(Flag.ACCEPTS_BLOCK, Flag.ACCEPTS_INLINE), new AttrMap[0]),
    BODY(BlockType.OTHER, EndKind.REQUIRED, new AttrMap[0]),
    BR(BlockType.INLINE, EndKind.NONE, attrs(AttrKind.USE_CSS, Attr.CLEAR)),
    CAPTION(BlockType.TABLE_ITEM, EndKind.REQUIRED, EnumSet.of(Flag.ACCEPTS_INLINE, Flag.EXPECT_CONTENT), new AttrMap[0]),
    CENTER(BlockType.BLOCK, EndKind.REQUIRED, EnumSet.of(Flag.ACCEPTS_BLOCK, Flag.ACCEPTS_INLINE), new AttrMap[0]),
    CITE(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT, Flag.NO_NEST), new AttrMap[0]),
    CODE(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT, Flag.NO_NEST), new AttrMap[0]),
    DD(BlockType.LIST_ITEM, EndKind.OPTIONAL, EnumSet.of(Flag.ACCEPTS_BLOCK, Flag.ACCEPTS_INLINE, Flag.EXPECT_CONTENT), new AttrMap[0]),
    DFN(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT, Flag.NO_NEST), new AttrMap[0]),
    DIV(BlockType.BLOCK, EndKind.REQUIRED, EnumSet.of(Flag.ACCEPTS_BLOCK, Flag.ACCEPTS_INLINE), new AttrMap[0]),
    DL(BlockType.BLOCK, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT), attrs(AttrKind.USE_CSS, Attr.COMPACT)) { // from class: com.sun.tools.doclint.HtmlTag.1
        @Override // com.sun.tools.doclint.HtmlTag
        public boolean accepts(HtmlTag htmlTag) {
            return htmlTag == DT || htmlTag == DD;
        }
    },
    DT(BlockType.LIST_ITEM, EndKind.OPTIONAL, EnumSet.of(Flag.ACCEPTS_INLINE, Flag.EXPECT_CONTENT), new AttrMap[0]),
    EM(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.NO_NEST), new AttrMap[0]),
    FONT(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT), attrs(AttrKind.USE_CSS, Attr.SIZE, Attr.COLOR, Attr.FACE)),
    FRAME(BlockType.OTHER, EndKind.NONE, new AttrMap[0]),
    FRAMESET(BlockType.OTHER, EndKind.REQUIRED, new AttrMap[0]),
    H1(BlockType.BLOCK, EndKind.REQUIRED, new AttrMap[0]),
    H2(BlockType.BLOCK, EndKind.REQUIRED, new AttrMap[0]),
    H3(BlockType.BLOCK, EndKind.REQUIRED, new AttrMap[0]),
    H4(BlockType.BLOCK, EndKind.REQUIRED, new AttrMap[0]),
    H5(BlockType.BLOCK, EndKind.REQUIRED, new AttrMap[0]),
    H6(BlockType.BLOCK, EndKind.REQUIRED, new AttrMap[0]),
    HEAD(BlockType.OTHER, EndKind.REQUIRED, new AttrMap[0]),
    HR(BlockType.BLOCK, EndKind.NONE, attrs(AttrKind.OK, Attr.WIDTH)),
    HTML(BlockType.OTHER, EndKind.REQUIRED, new AttrMap[0]),
    I(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT, Flag.NO_NEST), new AttrMap[0]),
    IMG(BlockType.INLINE, EndKind.NONE, attrs(AttrKind.OK, Attr.SRC, Attr.ALT, Attr.HEIGHT, Attr.WIDTH), attrs(AttrKind.OBSOLETE, Attr.NAME), attrs(AttrKind.USE_CSS, Attr.ALIGN, Attr.HSPACE, Attr.VSPACE, Attr.BORDER)),
    LI(BlockType.LIST_ITEM, EndKind.OPTIONAL, EnumSet.of(Flag.ACCEPTS_BLOCK, Flag.ACCEPTS_INLINE), attrs(AttrKind.OK, Attr.VALUE)),
    LINK(BlockType.OTHER, EndKind.NONE, new AttrMap[0]),
    MENU(BlockType.BLOCK, EndKind.REQUIRED, new AttrMap[0]) { // from class: com.sun.tools.doclint.HtmlTag.2
        @Override // com.sun.tools.doclint.HtmlTag
        public boolean accepts(HtmlTag htmlTag) {
            return htmlTag == LI;
        }
    },
    META(BlockType.OTHER, EndKind.NONE, new AttrMap[0]),
    NOFRAMES(BlockType.OTHER, EndKind.REQUIRED, new AttrMap[0]),
    NOSCRIPT(BlockType.BLOCK, EndKind.REQUIRED, new AttrMap[0]),
    OL(BlockType.BLOCK, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT), attrs(AttrKind.OK, Attr.START, Attr.TYPE)) { // from class: com.sun.tools.doclint.HtmlTag.3
        @Override // com.sun.tools.doclint.HtmlTag
        public boolean accepts(HtmlTag htmlTag) {
            return htmlTag == LI;
        }
    },
    P(BlockType.BLOCK, EndKind.OPTIONAL, EnumSet.of(Flag.EXPECT_CONTENT), attrs(AttrKind.USE_CSS, Attr.ALIGN)),
    PRE(BlockType.BLOCK, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT), new AttrMap[0]) { // from class: com.sun.tools.doclint.HtmlTag.4
        @Override // com.sun.tools.doclint.HtmlTag
        public boolean accepts(HtmlTag htmlTag) {
            switch (htmlTag) {
                case IMG:
                case BIG:
                case SMALL:
                case SUB:
                case SUP:
                    return false;
                default:
                    return htmlTag.blockType == BlockType.INLINE;
            }
        }
    },
    SCRIPT(BlockType.OTHER, EndKind.REQUIRED, attrs(AttrKind.OK, Attr.SRC)),
    SMALL(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT), new AttrMap[0]),
    SPAN(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT), new AttrMap[0]),
    STRONG(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT), new AttrMap[0]),
    SUB(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT, Flag.NO_NEST), new AttrMap[0]),
    SUP(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT, Flag.NO_NEST), new AttrMap[0]),
    TABLE(BlockType.BLOCK, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT), attrs(AttrKind.OK, Attr.SUMMARY, Attr.FRAME, Attr.RULES, Attr.BORDER, Attr.CELLPADDING, Attr.CELLSPACING, Attr.WIDTH), attrs(AttrKind.USE_CSS, Attr.ALIGN, Attr.BGCOLOR)) { // from class: com.sun.tools.doclint.HtmlTag.5
        @Override // com.sun.tools.doclint.HtmlTag
        public boolean accepts(HtmlTag htmlTag) {
            switch (htmlTag) {
                case CAPTION:
                case THEAD:
                case TBODY:
                case TFOOT:
                case TR:
                    return true;
                default:
                    return false;
            }
        }
    },
    TBODY(BlockType.TABLE_ITEM, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT), attrs(AttrKind.OK, Attr.ALIGN, Attr.CHAR, Attr.CHAROFF, Attr.VALIGN)) { // from class: com.sun.tools.doclint.HtmlTag.6
        @Override // com.sun.tools.doclint.HtmlTag
        public boolean accepts(HtmlTag htmlTag) {
            return htmlTag == TR;
        }
    },
    TD(BlockType.TABLE_ITEM, EndKind.OPTIONAL, EnumSet.of(Flag.ACCEPTS_BLOCK, Flag.ACCEPTS_INLINE), attrs(AttrKind.OK, Attr.COLSPAN, Attr.ROWSPAN, Attr.HEADERS, Attr.SCOPE, Attr.ABBR, Attr.AXIS, Attr.ALIGN, Attr.CHAR, Attr.CHAROFF, Attr.VALIGN), attrs(AttrKind.USE_CSS, Attr.WIDTH, Attr.BGCOLOR, Attr.HEIGHT, Attr.NOWRAP)),
    TFOOT(BlockType.TABLE_ITEM, EndKind.REQUIRED, attrs(AttrKind.OK, Attr.ALIGN, Attr.CHAR, Attr.CHAROFF, Attr.VALIGN)) { // from class: com.sun.tools.doclint.HtmlTag.7
        @Override // com.sun.tools.doclint.HtmlTag
        public boolean accepts(HtmlTag htmlTag) {
            return htmlTag == TR;
        }
    },
    TH(BlockType.TABLE_ITEM, EndKind.OPTIONAL, EnumSet.of(Flag.ACCEPTS_BLOCK, Flag.ACCEPTS_INLINE), attrs(AttrKind.OK, Attr.COLSPAN, Attr.ROWSPAN, Attr.HEADERS, Attr.SCOPE, Attr.ABBR, Attr.AXIS, Attr.ALIGN, Attr.CHAR, Attr.CHAROFF, Attr.VALIGN), attrs(AttrKind.USE_CSS, Attr.WIDTH, Attr.BGCOLOR, Attr.HEIGHT, Attr.NOWRAP)),
    THEAD(BlockType.TABLE_ITEM, EndKind.REQUIRED, attrs(AttrKind.OK, Attr.ALIGN, Attr.CHAR, Attr.CHAROFF, Attr.VALIGN)) { // from class: com.sun.tools.doclint.HtmlTag.8
        @Override // com.sun.tools.doclint.HtmlTag
        public boolean accepts(HtmlTag htmlTag) {
            return htmlTag == TR;
        }
    },
    TITLE(BlockType.OTHER, EndKind.REQUIRED, new AttrMap[0]),
    TR(BlockType.TABLE_ITEM, EndKind.OPTIONAL, attrs(AttrKind.OK, Attr.ALIGN, Attr.CHAR, Attr.CHAROFF, Attr.VALIGN), attrs(AttrKind.USE_CSS, Attr.BGCOLOR)) { // from class: com.sun.tools.doclint.HtmlTag.9
        @Override // com.sun.tools.doclint.HtmlTag
        public boolean accepts(HtmlTag htmlTag) {
            return htmlTag == TH || htmlTag == TD;
        }
    },
    TT(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT, Flag.NO_NEST), new AttrMap[0]),
    U(BlockType.INLINE, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT, Flag.NO_NEST), new AttrMap[0]),
    UL(BlockType.BLOCK, EndKind.REQUIRED, EnumSet.of(Flag.EXPECT_CONTENT), attrs(AttrKind.OK, Attr.COMPACT, Attr.TYPE)) { // from class: com.sun.tools.doclint.HtmlTag.10
        @Override // com.sun.tools.doclint.HtmlTag
        public boolean accepts(HtmlTag htmlTag) {
            return htmlTag == LI;
        }
    },
    VAR(BlockType.INLINE, EndKind.REQUIRED, new AttrMap[0]);

    public final BlockType blockType;
    public final EndKind endKind;
    public final Set<Flag> flags;
    private final Map<Attr, AttrKind> attrs;
    private static final Map<String, HtmlTag> index = new HashMap();

    /* loaded from: target.jar:com/sun/tools/doclint/HtmlTag$AttrKind.class */
    public enum AttrKind {
        INVALID,
        OBSOLETE,
        USE_CSS,
        OK
    }

    /* loaded from: target.jar:com/sun/tools/doclint/HtmlTag$BlockType.class */
    public enum BlockType {
        BLOCK,
        INLINE,
        LIST_ITEM,
        TABLE_ITEM,
        OTHER
    }

    /* loaded from: target.jar:com/sun/tools/doclint/HtmlTag$EndKind.class */
    public enum EndKind {
        NONE,
        OPTIONAL,
        REQUIRED
    }

    /* loaded from: target.jar:com/sun/tools/doclint/HtmlTag$Flag.class */
    public enum Flag {
        ACCEPTS_BLOCK,
        ACCEPTS_INLINE,
        EXPECT_CONTENT,
        NO_NEST
    }

    static {
        for (HtmlTag htmlTag : values()) {
            index.put(htmlTag.getText(), htmlTag);
        }
    }

    /* loaded from: target.jar:com/sun/tools/doclint/HtmlTag$Attr.class */
    public enum Attr {
        ABBR,
        ALIGN,
        ALT,
        AXIS,
        BGCOLOR,
        BORDER,
        CELLSPACING,
        CELLPADDING,
        CHAR,
        CHAROFF,
        CLEAR,
        CLASS,
        COLOR,
        COLSPAN,
        COMPACT,
        FACE,
        FRAME,
        HEADERS,
        HEIGHT,
        HREF,
        HSPACE,
        ID,
        NAME,
        NOWRAP,
        REVERSED,
        ROWSPAN,
        RULES,
        SCOPE,
        SIZE,
        SPACE,
        SRC,
        START,
        STYLE,
        SUMMARY,
        TARGET,
        TYPE,
        VALIGN,
        VALUE,
        VSPACE,
        WIDTH;

        static final Map<String, Attr> index = new HashMap();

        static {
            for (Attr attr : values()) {
                index.put(attr.getText(), attr);
            }
        }

        public String getText() {
            return StringUtils.toLowerCase(name());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/doclint/HtmlTag$AttrMap.class */
    public static class AttrMap extends EnumMap<Attr, AttrKind> {
        private static final long serialVersionUID = 0;

        AttrMap() {
            super(Attr.class);
        }
    }

    HtmlTag(BlockType blockType, EndKind endKind, AttrMap... attrMapArr) {
        this(blockType, endKind, Collections.emptySet(), attrMapArr);
    }

    HtmlTag(BlockType blockType, EndKind endKind, Set set, AttrMap... attrMapArr) {
        this.blockType = blockType;
        this.endKind = endKind;
        this.flags = set;
        this.attrs = new EnumMap(Attr.class);
        for (AttrMap attrMap : attrMapArr) {
            this.attrs.putAll(attrMap);
        }
        this.attrs.put(Attr.CLASS, AttrKind.OK);
        this.attrs.put(Attr.ID, AttrKind.OK);
        this.attrs.put(Attr.STYLE, AttrKind.OK);
    }

    public boolean accepts(HtmlTag htmlTag) {
        if (this.flags.contains(Flag.ACCEPTS_BLOCK) && this.flags.contains(Flag.ACCEPTS_INLINE)) {
            return htmlTag.blockType == BlockType.BLOCK || htmlTag.blockType == BlockType.INLINE;
        }
        if (this.flags.contains(Flag.ACCEPTS_BLOCK)) {
            return htmlTag.blockType == BlockType.BLOCK;
        }
        if (this.flags.contains(Flag.ACCEPTS_INLINE)) {
            return htmlTag.blockType == BlockType.INLINE;
        }
        switch (this.blockType) {
            case BLOCK:
            case INLINE:
                return htmlTag.blockType == BlockType.INLINE;
            case OTHER:
                return true;
            default:
                throw new AssertionError(this + CallSiteDescriptor.TOKEN_DELIMITER + htmlTag);
        }
    }

    public boolean acceptsText() {
        return accepts(B);
    }

    public String getText() {
        return StringUtils.toLowerCase(name());
    }

    public Attr getAttr(Name name) {
        return Attr.index.get(StringUtils.toLowerCase(name.toString()));
    }

    public AttrKind getAttrKind(Name name) {
        AttrKind attrKind = this.attrs.get(getAttr(name));
        return attrKind == null ? AttrKind.INVALID : attrKind;
    }

    private static AttrMap attrs(AttrKind attrKind, Attr... attrArr) {
        AttrMap attrMap = new AttrMap();
        for (Attr attr : attrArr) {
            attrMap.put((AttrMap) attr, (Attr) attrKind);
        }
        return attrMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HtmlTag get(Name name) {
        return index.get(StringUtils.toLowerCase(name.toString()));
    }
}
