package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/GreaterThan.class */
public class GreaterThan extends BinaryExpr {
    /* JADX INFO: Access modifiers changed from: protected */
    public GreaterThan(Expression expression, Expression expression2) {
        super(">", expression, expression2);
    }

    @Override // com.sun.tools.corba.se.idl.constExpr.Expression
    public Object evaluate() throws EvaluationException {
        try {
            Object evaluate = left().evaluate();
            right().evaluate();
            if (evaluate instanceof Boolean) {
                throw new EvaluationException(Util.getMessage("EvaluationException.1", new String[]{Util.getMessage("EvaluationException.greaterThan"), left().value().getClass().getName(), right().value().getClass().getName()}));
            }
            Number number = (Number) evaluate;
            Number number2 = (Number) right().evaluate();
            if ((number instanceof Float) || (number instanceof Double) || (number2 instanceof Float) || (number2 instanceof Double)) {
                value(new Boolean(number.doubleValue() > number2.doubleValue()));
            } else {
                value(new Boolean(((BigInteger) number).compareTo((BigInteger) number2) > 0));
            }
            return value();
        } catch (ClassCastException e) {
            throw new EvaluationException(Util.getMessage("EvaluationException.1", new String[]{Util.getMessage("EvaluationException.greaterThan"), left().value().getClass().getName(), right().value().getClass().getName()}));
        }
    }
}
