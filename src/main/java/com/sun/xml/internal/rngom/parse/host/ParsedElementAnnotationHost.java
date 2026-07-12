package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/host/ParsedElementAnnotationHost.class */
final class ParsedElementAnnotationHost implements ParsedElementAnnotation {
    final ParsedElementAnnotation lhs;
    final ParsedElementAnnotation rhs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParsedElementAnnotationHost(ParsedElementAnnotation lhs, ParsedElementAnnotation rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
