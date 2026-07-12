package com.sun.tools.internal.xjc.model;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.math.BigInteger;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/internal/xjc/model/Multiplicity.class */
public final class Multiplicity {
    public final BigInteger min;
    public final BigInteger max;
    public static final Multiplicity ZERO = new Multiplicity(0, 0);
    public static final Multiplicity ONE = new Multiplicity(1, 1);
    public static final Multiplicity OPTIONAL = new Multiplicity(0, 1);
    public static final Multiplicity STAR = new Multiplicity(0, (Integer) null);
    public static final Multiplicity PLUS = new Multiplicity(1, (Integer) null);

    public static Multiplicity create(BigInteger min, BigInteger max) {
        if (BigInteger.ZERO.equals(min) && max == null) {
            return STAR;
        }
        if (BigInteger.ONE.equals(min) && max == null) {
            return PLUS;
        }
        if (max != null) {
            if (BigInteger.ZERO.equals(min) && BigInteger.ZERO.equals(max)) {
                return ZERO;
            }
            if (BigInteger.ZERO.equals(min) && BigInteger.ONE.equals(max)) {
                return OPTIONAL;
            }
            if (BigInteger.ONE.equals(min) && BigInteger.ONE.equals(max)) {
                return ONE;
            }
        }
        return new Multiplicity(min, max);
    }

    public static Multiplicity create(int min, Integer max) {
        return create(BigInteger.valueOf(min), BigInteger.valueOf(max.intValue()));
    }

    private Multiplicity(BigInteger min, BigInteger max) {
        this.min = min;
        this.max = max;
    }

    private Multiplicity(int min, int max) {
        this(BigInteger.valueOf(min), BigInteger.valueOf(max));
    }

    private Multiplicity(int min, Integer max) {
        this(BigInteger.valueOf(min), max == null ? null : BigInteger.valueOf(max.intValue()));
    }

    public boolean equals(Object o) {
        if (!(o instanceof Multiplicity)) {
            return false;
        }
        Multiplicity that = (Multiplicity) o;
        if (this.min.equals(that.min)) {
            return this.max != null ? this.max.equals(that.max) : that.max == null;
        }
        return false;
    }

    public int hashCode() {
        return this.min.add(this.max).intValue();
    }

    public boolean isUnique() {
        return this.max != null && BigInteger.ONE.equals(this.min) && BigInteger.ONE.equals(this.max);
    }

    public boolean isOptional() {
        return this.max != null && BigInteger.ZERO.equals(this.min) && BigInteger.ONE.equals(this.max);
    }

    public boolean isAtMostOnce() {
        return this.max != null && this.max.compareTo(BigInteger.ONE) <= 0;
    }

    public boolean isZero() {
        if (this.max == null) {
            return false;
        }
        return BigInteger.ZERO.equals(this.max);
    }

    public boolean includes(Multiplicity rhs) {
        if (rhs.min.compareTo(this.min) == -1) {
            return false;
        }
        if (this.max == null) {
            return true;
        }
        return rhs.max != null && rhs.max.compareTo(this.max) <= 0;
    }

    public String getMaxString() {
        return this.max == null ? Constants.ATTRVALUE_UNBOUNDED : this.max.toString();
    }

    public String toString() {
        return RuntimeConstants.SIG_METHOD + this.min + ',' + getMaxString() + ')';
    }

    public static Multiplicity choice(Multiplicity lhs, Multiplicity rhs) {
        return create(lhs.min.min(rhs.min), (lhs.max == null || rhs.max == null) ? null : lhs.max.max(rhs.max));
    }

    public static Multiplicity group(Multiplicity lhs, Multiplicity rhs) {
        return create(lhs.min.add(rhs.min), (lhs.max == null || rhs.max == null) ? null : lhs.max.add(rhs.max));
    }

    public static Multiplicity multiply(Multiplicity lhs, Multiplicity rhs) {
        BigInteger max;
        BigInteger min = lhs.min.multiply(rhs.min);
        if (isZero(lhs.max) || isZero(rhs.max)) {
            max = BigInteger.ZERO;
        } else if (lhs.max == null || rhs.max == null) {
            max = null;
        } else {
            max = lhs.max.multiply(rhs.max);
        }
        return create(min, max);
    }

    private static boolean isZero(BigInteger i) {
        return i != null && BigInteger.ZERO.equals(i);
    }

    public static Multiplicity oneOrMore(Multiplicity c) {
        if (c.max != null && !BigInteger.ZERO.equals(c.max)) {
            return create(c.min, (BigInteger) null);
        }
        return c;
    }

    public Multiplicity makeOptional() {
        return BigInteger.ZERO.equals(this.min) ? this : create(BigInteger.ZERO, this.max);
    }

    public Multiplicity makeRepeated() {
        return (this.max == null || BigInteger.ZERO.equals(this.max)) ? this : create(this.min, (BigInteger) null);
    }
}
