package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.om.ParsedPattern;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/host/ParsedPatternHost.class */
public class ParsedPatternHost implements ParsedPattern {
    public final ParsedPattern lhs;
    public final ParsedPattern rhs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParsedPatternHost(ParsedPattern lhs, ParsedPattern rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
