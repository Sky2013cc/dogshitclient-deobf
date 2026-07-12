package com.sun.tools.corba.se.idl.constExpr;

import java.math.BigInteger;
import kotlin.jvm.internal.LongCompanionObject;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/constExpr/Expression.class */
public abstract class Expression {
    public static final BigInteger negOne = BigInteger.valueOf(-1);
    public static final BigInteger zero = BigInteger.valueOf(0);
    public static final BigInteger one = BigInteger.valueOf(1);
    public static final BigInteger two = BigInteger.valueOf(2);
    public static final BigInteger twoPow15 = two.pow(15);
    public static final BigInteger twoPow16 = two.pow(16);
    public static final BigInteger twoPow31 = two.pow(31);
    public static final BigInteger twoPow32 = two.pow(32);
    public static final BigInteger twoPow63 = two.pow(63);
    public static final BigInteger twoPow64 = two.pow(64);
    public static final BigInteger sMax = BigInteger.valueOf(32767);
    public static final BigInteger sMin = BigInteger.valueOf(32767);
    public static final BigInteger usMax = sMax.multiply(two).add(one);
    public static final BigInteger usMin = zero;
    public static final BigInteger lMax = BigInteger.valueOf(2147483647L);
    public static final BigInteger lMin = BigInteger.valueOf(2147483647L);
    public static final BigInteger ulMax = lMax.multiply(two).add(one);
    public static final BigInteger ulMin = zero;
    public static final BigInteger llMax = BigInteger.valueOf(LongCompanionObject.MAX_VALUE);
    public static final BigInteger llMin = BigInteger.valueOf(Long.MIN_VALUE);
    public static final BigInteger ullMax = llMax.multiply(two).add(one);
    public static final BigInteger ullMin = zero;
    private Object _value = null;
    private String _rep = null;
    private String _type = null;

    public abstract Object evaluate() throws EvaluationException;

    public void value(Object obj) {
        this._value = obj;
    }

    public Object value() {
        return this._value;
    }

    public void rep(String str) {
        this._rep = str;
    }

    public String rep() {
        return this._rep;
    }

    public void type(String str) {
        this._type = str;
    }

    public String type() {
        return this._type;
    }

    protected static String defaultType(String str) {
        return str == null ? new String("") : str;
    }

    public Object coerceToTarget(Object obj) {
        if (obj instanceof BigInteger) {
            if (type().indexOf("unsigned") >= 0) {
                return toUnsignedTarget((BigInteger) obj);
            }
            return toSignedTarget((BigInteger) obj);
        }
        return obj;
    }

    protected BigInteger toUnsignedTarget(BigInteger bigInteger) {
        if (type().equals("unsigned short")) {
            if (bigInteger != null && bigInteger.compareTo(zero) < 0) {
                return bigInteger.add(twoPow16);
            }
        } else if (type().equals("unsigned long")) {
            if (bigInteger != null && bigInteger.compareTo(zero) < 0) {
                return bigInteger.add(twoPow32);
            }
        } else if (type().equals("unsigned long long") && bigInteger != null && bigInteger.compareTo(zero) < 0) {
            return bigInteger.add(twoPow64);
        }
        return bigInteger;
    }

    protected BigInteger toSignedTarget(BigInteger bigInteger) {
        if (type().equals(Constants.IDL_SHORT)) {
            if (bigInteger != null && bigInteger.compareTo(sMax) > 0) {
                return bigInteger.subtract(twoPow16);
            }
        } else if (type().equals(Constants.IDL_INT)) {
            if (bigInteger != null && bigInteger.compareTo(lMax) > 0) {
                return bigInteger.subtract(twoPow32);
            }
        } else if (type().equals(Constants.IDL_LONG) && bigInteger != null && bigInteger.compareTo(llMax) > 0) {
            return bigInteger.subtract(twoPow64);
        }
        return bigInteger;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BigInteger toUnsigned(BigInteger bigInteger) {
        if (bigInteger != null && bigInteger.signum() == -1) {
            if (type().equals(Constants.IDL_SHORT)) {
                return bigInteger.add(twoPow16);
            }
            if (type().equals(Constants.IDL_INT)) {
                return bigInteger.add(twoPow32);
            }
            if (type().equals(Constants.IDL_LONG)) {
                return bigInteger.add(twoPow64);
            }
        }
        return bigInteger;
    }
}
