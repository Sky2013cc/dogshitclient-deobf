package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/BooleanNot.class */
public class BooleanNot extends UnaryExpr {
    /* JADX INFO: Access modifiers changed from: protected */
    public BooleanNot(Expression expression) {
        super("!", expression);
    }

    @Override // com.sun.tools.corba.se.idl.constExpr.Expression
    public Object evaluate() throws EvaluationException {
        Boolean bool;
        try {
            Object evaluate = operand().evaluate();
            if (evaluate instanceof Number) {
                if (evaluate instanceof BigInteger) {
                    bool = new Boolean(((BigInteger) evaluate).compareTo(zero) != 0);
                } else {
                    bool = new Boolean(((Number) evaluate).longValue() != 0);
                }
            } else {
                bool = (Boolean) evaluate;
            }
            value(new Boolean(!bool.booleanValue()));
            return value();
        } catch (ClassCastException e) {
            throw new EvaluationException(Util.getMessage("EvaluationException.2", new String[]{Util.getMessage("EvaluationException.booleanNot"), operand().value().getClass().getName()}));
        }
    }
}
