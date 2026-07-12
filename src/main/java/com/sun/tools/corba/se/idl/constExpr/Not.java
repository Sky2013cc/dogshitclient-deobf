package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/Not.class */
public class Not extends UnaryExpr {
    /* JADX INFO: Access modifiers changed from: protected */
    public Not(Expression expression) {
        super("~", expression);
    }

    @Override // com.sun.tools.corba.se.idl.constExpr.Expression
    public Object evaluate() throws EvaluationException {
        try {
            Number number = (Number) operand().evaluate();
            if ((number instanceof Float) || (number instanceof Double)) {
                throw new EvaluationException(Util.getMessage("EvaluationException.2", new String[]{Util.getMessage("EvaluationException.not"), operand().value().getClass().getName()}));
            }
            BigInteger bigInteger = (BigInteger) coerceToTarget((BigInteger) number);
            if (type().equals(Constants.IDL_SHORT) || type().equals(Constants.IDL_INT) || type().equals(Constants.IDL_LONG)) {
                value(bigInteger.add(one).multiply(negOne));
            } else if (type().equals("unsigned short")) {
                value(twoPow16.subtract(one).subtract(bigInteger));
            } else if (type().equals("unsigned long")) {
                value(twoPow32.subtract(one).subtract(bigInteger));
            } else if (type().equals("unsigned long long")) {
                value(twoPow64.subtract(one).subtract(bigInteger));
            } else {
                value(bigInteger.not());
            }
            return value();
        } catch (ClassCastException e) {
            throw new EvaluationException(Util.getMessage("EvaluationException.2", new String[]{Util.getMessage("EvaluationException.not"), operand().value().getClass().getName()}));
        }
    }
}
