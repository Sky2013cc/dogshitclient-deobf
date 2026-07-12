package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/ShiftLeft.class */
public class ShiftLeft extends BinaryExpr {
    /* JADX INFO: Access modifiers changed from: protected */
    public ShiftLeft(Expression expression, Expression expression2) {
        super("<<", expression, expression2);
    }

    @Override // com.sun.tools.corba.se.idl.constExpr.Expression
    public Object evaluate() throws EvaluationException {
        try {
            Number number = (Number) left().evaluate();
            Number number2 = (Number) right().evaluate();
            if ((number instanceof Float) || (number instanceof Double) || (number2 instanceof Float) || (number2 instanceof Double)) {
                throw new EvaluationException(Util.getMessage("EvaluationException.1", new String[]{Util.getMessage("EvaluationException.left"), left().value().getClass().getName(), right().value().getClass().getName()}));
            }
            BigInteger shiftLeft = ((BigInteger) coerceToTarget(number)).shiftLeft(((BigInteger) number2).intValue());
            if (type().indexOf(Constants.IDL_SHORT) >= 0) {
                shiftLeft = shiftLeft.mod(twoPow16);
            } else if (type().indexOf(Constants.IDL_INT) >= 0) {
                shiftLeft = shiftLeft.mod(twoPow32);
            } else if (type().indexOf(Constants.IDL_LONG) >= 0) {
                shiftLeft = shiftLeft.mod(twoPow64);
            }
            value(coerceToTarget(shiftLeft));
            return value();
        } catch (ClassCastException e) {
            throw new EvaluationException(Util.getMessage("EvaluationException.1", new String[]{Util.getMessage("EvaluationException.left"), left().value().getClass().getName(), right().value().getClass().getName()}));
        }
    }
}
