package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.ConstEntry;
import java.math.BigInteger;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/Terminal.class */
public class Terminal extends Expression {
    /* JADX INFO: Access modifiers changed from: protected */
    public Terminal(String str, Character ch, boolean z) {
        rep(str);
        value(ch);
        if (z) {
            type(Constants.IDL_CHAR);
        } else {
            type("char");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Terminal(String str, Boolean bool) {
        rep(str);
        value(bool);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Terminal(String str, BigInteger bigInteger) {
        rep(str);
        value(bigInteger);
    }

    protected Terminal(String str, Long l) {
        long longValue = l.longValue();
        rep(str);
        if (longValue > 2147483647L || longValue < -2147483648L) {
            value(l);
        } else {
            value(new Integer(l.intValue()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Terminal(String str, Double d) {
        rep(str);
        value(d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Terminal(String str, boolean z) {
        rep(str);
        value(str);
        if (z) {
            type(Constants.IDL_CONSTANT_STRING);
        } else {
            type("string");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Terminal(ConstEntry constEntry) {
        rep(constEntry.fullName());
        value(constEntry);
    }

    @Override // com.sun.tools.corba.se.idl.constExpr.Expression
    public Object evaluate() throws EvaluationException {
        if (value() instanceof ConstEntry) {
            return ((ConstEntry) value()).value().evaluate();
        }
        return value();
    }
}
