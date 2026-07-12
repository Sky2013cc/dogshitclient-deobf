package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import java.math.BigInteger;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/BooleanOr.class */
public class BooleanOr extends BinaryExpr {
    /* JADX INFO: Access modifiers changed from: protected */
    public BooleanOr(Expression expression, Expression expression2) {
        super("||", expression, expression2);
    }

    @Override // com.sun.tools.corba.se.idl.constExpr.Expression
    public Object evaluate() throws EvaluationException {
        Boolean bool;
        Boolean bool2;
        try {
            Object evaluate = left().evaluate();
            Object evaluate2 = right().evaluate();
            if (evaluate instanceof Number) {
                if (evaluate instanceof BigInteger) {
                    bool = new Boolean(((BigInteger) evaluate).compareTo(zero) != 0);
                } else {
                    bool = new Boolean(((Number) evaluate).longValue() != 0);
                }
            } else {
                bool = (Boolean) evaluate;
            }
            if (evaluate2 instanceof Number) {
                if (evaluate2 instanceof BigInteger) {
                    bool2 = new Boolean(((BigInteger) evaluate2).compareTo(BigInteger.valueOf(0L)) != 0);
                } else {
                    bool2 = new Boolean(((Number) evaluate2).longValue() != 0);
                }
            } else {
                bool2 = (Boolean) evaluate2;
            }
            value(new Boolean(bool.booleanValue() || bool2.booleanValue()));
            return value();
        } catch (ClassCastException e) {
            throw new EvaluationException(Util.getMessage("EvaluationException.1", new String[]{Util.getMessage("EvaluationException.booleanOr"), left().value().getClass().getName(), right().value().getClass().getName()}));
        }
    }
}
