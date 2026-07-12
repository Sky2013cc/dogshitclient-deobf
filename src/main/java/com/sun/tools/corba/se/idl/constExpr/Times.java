package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;
import org.slf4j.Marker;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/Times.class */
public class Times extends BinaryExpr {
    /* JADX INFO: Access modifiers changed from: protected */
    public Times(Expression expression, Expression expression2) {
        super(Marker.ANY_MARKER, expression, expression2);
    }

    @Override // com.sun.tools.corba.se.idl.constExpr.Expression
    public Object evaluate() throws EvaluationException {
        try {
            Number number = (Number) left().evaluate();
            Number number2 = (Number) right().evaluate();
            boolean z = (number instanceof Float) || (number instanceof Double);
            boolean z2 = (number2 instanceof Float) || (number2 instanceof Double);
            if (z && z2) {
                value(new Double(number.doubleValue() * number2.doubleValue()));
            } else {
                if (z || z2) {
                    throw new EvaluationException(Util.getMessage("EvaluationException.1", new String[]{Util.getMessage("EvaluationException.times"), left().value().getClass().getName(), right().value().getClass().getName()}));
                }
                value(((BigInteger) number).multiply((BigInteger) number2));
            }
            return value();
        } catch (ClassCastException e) {
            throw new EvaluationException(Util.getMessage("EvaluationException.1", new String[]{Util.getMessage("EvaluationException.times"), left().value().getClass().getName(), right().value().getClass().getName()}));
        }
    }
}
