package com.sun.tools.corba.se.idl;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/TokenBuffer.class */
class TokenBuffer {
    private final int DEFAULT_SIZE = 10;
    private int _size;
    private Token[] _buffer;
    private int _currPos;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TokenBuffer() {
        this._size = 0;
        this._buffer = null;
        this._currPos = -1;
        this._size = 10;
        this._buffer = new Token[this._size];
        this._currPos = -1;
    }

    TokenBuffer(int i) throws Exception {
        this._size = 0;
        this._buffer = null;
        this._currPos = -1;
        this._size = i;
        this._buffer = new Token[this._size];
        this._currPos = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insert(Token token) {
        int i = this._currPos + 1;
        this._currPos = i;
        this._currPos = i % this._size;
        this._buffer[this._currPos] = token;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token lookBack(int i) {
        return this._buffer[this._currPos - i >= 0 ? this._currPos - i : (this._currPos - i) + this._size];
    }

    Token current() {
        return this._buffer[this._currPos];
    }
}
