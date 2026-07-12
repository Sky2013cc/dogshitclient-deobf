package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/Modulo.class */
public class Modulo extends BinaryExpr {
    /* JADX INFO: Access modifiers changed from: protected */
    public Modulo(Expression expression, Expression expression2) {
        super("%", expression, expression2);
    }

    @Override // com.sun.tools.corba.se.idl.constExpr.Expression
    public Object evaluate() throws EvaluationException {
        try {
            Number number = (Number) left().evaluate();
            Number number2 = (Number) right().evaluate();
            if ((number instanceof Float) || (number instanceof Double) || (number2 instanceof Float) || (number2 instanceof Double)) {
                throw new EvaluationException(Util.getMessage("EvaluationException.1", new String[]{Util.getMessage("EvaluationException.mod"), left().value().getClass().getName(), right().value().getClass().getName()}));
            }
            value(((BigInteger) number).remainder((BigInteger) number2));
            return value();
        } catch (ClassCastException e) {
            throw new EvaluationException(Util.getMessage("EvaluationException.1", new String[]{Util.getMessage("EvaluationException.mod"), left().value().getClass().getName(), right().value().getClass().getName()}));
        }
    }
}
