package com.sun.tools.corba.se.idl.constExpr;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/BinaryExpr.class */
public abstract class BinaryExpr extends Expression {
    private String _op;
    private Expression _left;
    private Expression _right;

    public BinaryExpr(String str, Expression expression, Expression expression2) {
        this._op = "";
        this._left = null;
        this._right = null;
        this._op = str;
        this._left = expression;
        this._right = expression2;
    }

    public void op(String str) {
        this._op = str == null ? "" : str;
    }

    public String op() {
        return this._op;
    }

    public void left(Expression expression) {
        this._left = expression;
    }

    public Expression left() {
        return this._left;
    }

    public void right(Expression expression) {
        this._right = expression;
    }

    public Expression right() {
        return this._right;
    }
}
