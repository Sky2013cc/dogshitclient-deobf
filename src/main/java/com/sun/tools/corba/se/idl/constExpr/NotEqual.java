package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/NotEqual.class */
public class NotEqual extends BinaryExpr {
    /* JADX INFO: Access modifiers changed from: protected */
    public NotEqual(Expression expression, Expression expression2) {
        super("!=", expression, expression2);
    }

    @Override // com.sun.tools.corba.se.idl.constExpr.Expression
    public Object evaluate() throws EvaluationException {
        try {
            Object evaluate = left().evaluate();
            if (evaluate instanceof Boolean) {
                value(new Boolean(((Boolean) evaluate).booleanValue() != ((Boolean) right().evaluate()).booleanValue()));
            } else {
                Number number = (Number) evaluate;
                Number number2 = (Number) right().evaluate();
                if ((number instanceof Float) || (number instanceof Double) || (number2 instanceof Float) || (number2 instanceof Double)) {
                    value(new Boolean(number.doubleValue() != number2.doubleValue()));
                } else {
                    value(new Boolean(!((BigInteger) number).equals((BigInteger) number2)));
                }
            }
            return value();
        } catch (ClassCastException e) {
            throw new EvaluationException(Util.getMessage("EvaluationException.1", new String[]{Util.getMessage("EvaluationException.notEqual"), left().value().getClass().getName(), right().value().getClass().getName()}));
        }
    }
}
