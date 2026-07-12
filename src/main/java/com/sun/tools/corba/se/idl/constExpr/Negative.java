package com.sun.tools.corba.se.idl.constExpr;

import com.sun.tools.corba.se.idl.Util;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.math.BigInteger;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/Negative.class */
public class Negative extends UnaryExpr {
    /* JADX INFO: Access modifiers changed from: protected */
    public Negative(Expression expression) {
        super(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR, expression);
    }

    @Override // com.sun.tools.corba.se.idl.constExpr.Expression
    public Object evaluate() throws EvaluationException {
        try {
            Number number = (Number) operand().evaluate();
            if ((number instanceof Float) || (number instanceof Double)) {
                value(new Double(-number.doubleValue()));
            } else {
                value(((BigInteger) number).multiply(BigInteger.valueOf(-1L)));
            }
            return value();
        } catch (ClassCastException e) {
            throw new EvaluationException(Util.getMessage("EvaluationException.2", new String[]{Util.getMessage("EvaluationException.neg"), operand().value().getClass().getName()}));
        }
    }
}
