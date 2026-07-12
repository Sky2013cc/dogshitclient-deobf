package com.sun.tools.corba.se.idl.constExpr;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/UnaryExpr.class */
public abstract class UnaryExpr extends Expression {
    private String _op;
    private Expression _operand;

    public UnaryExpr(String str, Expression expression) {
        this._op = "";
        this._operand = null;
        this._op = str;
        this._operand = expression;
    }

    public void op(String str) {
        this._op = str == null ? "" : str;
    }

    public String op() {
        return this._op;
    }

    public void operand(Expression expression) {
        this._operand = expression;
    }

    public Expression operand() {
        return this._operand;
    }
}
