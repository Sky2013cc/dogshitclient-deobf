package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.om.ParsedNameClass;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/host/ParsedNameClassHost.class */
final class ParsedNameClassHost implements ParsedNameClass {
    final ParsedNameClass lhs;
    final ParsedNameClass rhs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParsedNameClassHost(ParsedNameClass lhs, ParsedNameClass rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
