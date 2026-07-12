package com.sun.xml.internal.rngom.parse.compact;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/compact/EscapeSyntaxException.class */
class EscapeSyntaxException extends RuntimeException {
    private final String key;
    private final int lineNumber;
    private final int columnNumber;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EscapeSyntaxException(String key, int lineNumber, int columnNumber) {
        this.key = key;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getKey() {
        return this.key;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLineNumber() {
        return this.lineNumber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getColumnNumber() {
        return this.columnNumber;
    }
}
