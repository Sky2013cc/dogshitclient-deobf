package com.sun.tools.corba.se.idl;

import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/PragmaHandler.class */
public abstract class PragmaHandler {
    private Preprocessor preprocessor = null;

    public abstract boolean process(String str, String str2) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(Preprocessor preprocessor) {
        this.preprocessor = preprocessor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String currentToken() {
        return this.preprocessor.currentToken();
    }

    protected SymtabEntry getEntryForName(String str) {
        return this.preprocessor.getEntryForName(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getStringToEOL() throws IOException {
        return this.preprocessor.getStringToEOL();
    }

    protected String getUntil(char c) throws IOException {
        return this.preprocessor.getUntil(c);
    }

    protected String nextToken() throws IOException {
        return this.preprocessor.nextToken();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SymtabEntry scopedName() throws IOException {
        return this.preprocessor.scopedName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void skipToEOL() throws IOException {
        this.preprocessor.skipToEOL();
    }

    protected String skipUntil(char c) throws IOException {
        return this.preprocessor.skipUntil(c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void parseException(String str) {
        this.preprocessor.parseException(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void openScope(SymtabEntry symtabEntry) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void closeScope(SymtabEntry symtabEntry) {
    }
}
