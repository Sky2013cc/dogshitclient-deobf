package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;
import org.slf4j.Marker;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/Positive.class */
public class Positive extends UnaryExpr {
    /* JADX INFO: Access modifiers changed from: protected */
    public Positive(Expression expression) {
        super(Marker.ANY_NON_NULL_MARKER, expression);
    }

    @Override // com.sun.tools.corba.se.idl.constExpr.Expression
    public Object evaluate() throws EvaluationException {
        try {
            Number number = (Number) operand().evaluate();
            if ((number instanceof Float) || (number instanceof Double)) {
                value(new Double(number.doubleValue()));
            } else {
                value(((BigInteger) number).multiply(BigInteger.valueOf(((BigInteger) number).signum())));
            }
            return value();
        } catch (ClassCastException e) {
            throw new EvaluationException(Util.getMessage("EvaluationException.2", new String[]{Util.getMessage("EvaluationException.pos"), operand().value().getClass().getName()}));
        }
    }
}
