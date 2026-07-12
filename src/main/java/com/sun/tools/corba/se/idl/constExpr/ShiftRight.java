package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/ShiftRight.class */
public class ShiftRight extends BinaryExpr {
    /* JADX INFO: Access modifiers changed from: protected */
    public ShiftRight(Expression expression, Expression expression2) {
        super(">>", expression, expression2);
    }

    @Override // com.sun.tools.corba.se.idl.constExpr.Expression
    public Object evaluate() throws EvaluationException {
        try {
            Number number = (Number) left().evaluate();
            Number number2 = (Number) right().evaluate();
            if ((number instanceof Float) || (number instanceof Double) || (number2 instanceof Float) || (number2 instanceof Double)) {
                throw new EvaluationException(Util.getMessage("EvaluationException.1", new String[]{Util.getMessage("EvaluationException.right"), left().value().getClass().getName(), right().value().getClass().getName()}));
            }
            BigInteger bigInteger = (BigInteger) coerceToTarget((BigInteger) number);
            BigInteger bigInteger2 = (BigInteger) number2;
            if (bigInteger.signum() == -1) {
                if (type().equals(Constants.IDL_SHORT)) {
                    bigInteger = bigInteger.add(twoPow16);
                } else if (type().equals(Constants.IDL_INT)) {
                    bigInteger = bigInteger.add(twoPow32);
                } else if (type().equals(Constants.IDL_LONG)) {
                    bigInteger = bigInteger.add(twoPow64);
                }
            }
            value(bigInteger.shiftRight(bigInteger2.intValue()));
            return value();
        } catch (ClassCastException e) {
            throw new EvaluationException(Util.getMessage("EvaluationException.1", new String[]{Util.getMessage("EvaluationException.right"), left().value().getClass().getName(), right().value().getClass().getName()}));
        }
    }
}
