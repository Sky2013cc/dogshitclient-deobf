package com.sun.tools.javadoc;

/* loaded from: target.jar:com/sun/tools/javadoc/ModifierFilter.class */
public class ModifierFilter {
    public static final long PACKAGE = Long.MIN_VALUE;
    public static final long ALL_ACCESS = -9223372036854775801L;
    private long oneOf;
    private long must;
    private long cannot;
    private static final int ACCESS_BITS = 7;

    public ModifierFilter(long j) {
        this(j, 0L, 0L);
    }

    public ModifierFilter(long j, long j2, long j3) {
        this.oneOf = j;
        this.must = j2;
        this.cannot = j3;
    }

    public boolean checkModifier(int i) {
        long j = (i & 7) == 0 ? i | Long.MIN_VALUE : i;
        return (this.oneOf == 0 || (this.oneOf & j) != 0) && (this.must & j) == this.must && (this.cannot & j) == 0;
    }
}
