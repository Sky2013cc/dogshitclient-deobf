package com.sun.source.doctree;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/DocTree.class */
public interface DocTree {
    Kind getKind();

    <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d);

    @Exported
    /* loaded from: target.jar:com/sun/source/doctree/DocTree$Kind.class */
    public enum Kind {
        ATTRIBUTE,
        AUTHOR("author"),
        CODE("code"),
        COMMENT,
        DEPRECATED("deprecated"),
        DOC_COMMENT,
        DOC_ROOT("docRoot"),
        END_ELEMENT,
        ENTITY,
        ERRONEOUS,
        EXCEPTION("exception"),
        IDENTIFIER,
        INHERIT_DOC("inheritDoc"),
        LINK("link"),
        LINK_PLAIN("linkplain"),
        LITERAL(Constants.ATTRVALUE_LITERAL),
        PARAM("param"),
        REFERENCE,
        RETURN("return"),
        SEE("see"),
        SERIAL("serial"),
        SERIAL_DATA("serialData"),
        SERIAL_FIELD("serialField"),
        SINCE("since"),
        START_ELEMENT,
        TEXT,
        THROWS("throws"),
        UNKNOWN_BLOCK_TAG,
        UNKNOWN_INLINE_TAG,
        VALUE("value"),
        VERSION("version"),
        OTHER;

        public final String tagName;

        Kind() {
            this.tagName = null;
        }

        Kind(String str) {
            this.tagName = str;
        }
    }
}
