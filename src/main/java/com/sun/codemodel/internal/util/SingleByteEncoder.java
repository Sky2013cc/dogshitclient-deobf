package com.sun.codemodel.internal.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import sun.nio.cs.Surrogate;

/* loaded from: target.jar:com/sun/codemodel/internal/util/SingleByteEncoder.class */
abstract class SingleByteEncoder extends CharsetEncoder {
    private final short[] index1;
    private final String index2;
    private final int mask1;
    private final int mask2;
    private final int shift;
    private final Surrogate.Parser sgp;

    /* JADX INFO: Access modifiers changed from: protected */
    public SingleByteEncoder(Charset cs, short[] index1, String index2, int mask1, int mask2, int shift) {
        super(cs, 1.0f, 1.0f);
        this.sgp = new Surrogate.Parser();
        this.index1 = index1;
        this.index2 = index2;
        this.mask1 = mask1;
        this.mask2 = mask2;
        this.shift = shift;
    }

    @Override // java.nio.charset.CharsetEncoder
    public boolean canEncode(char c) {
        char testEncode = this.index2.charAt(this.index1[(c & this.mask1) >> this.shift] + (c & this.mask2));
        if (testEncode == 0) {
            return false;
        }
        return true;
    }

    private CoderResult encodeArrayLoop(CharBuffer src, ByteBuffer dst) {
        char[] sa = src.array();
        int sp = src.arrayOffset() + src.position();
        int sl = src.arrayOffset() + src.limit();
        int sp2 = sp <= sl ? sp : sl;
        byte[] da = dst.array();
        int dp = dst.arrayOffset() + dst.position();
        int dl = dst.arrayOffset() + dst.limit();
        int dp2 = dp <= dl ? dp : dl;
        while (sp2 < sl) {
            try {
                char c = sa[sp2];
                if (sun.nio.cs.Surrogate.is(c)) {
                    if (this.sgp.parse(c, sa, sp2, sl) < 0) {
                        CoderResult error = this.sgp.error();
                        src.position(sp2 - src.arrayOffset());
                        dst.position(dp2 - dst.arrayOffset());
                        return error;
                    }
                    CoderResult unmappableResult = this.sgp.unmappableResult();
                    src.position(sp2 - src.arrayOffset());
                    dst.position(dp2 - dst.arrayOffset());
                    return unmappableResult;
                }
                if (c >= 65534) {
                    CoderResult unmappableForLength = CoderResult.unmappableForLength(1);
                    src.position(sp2 - src.arrayOffset());
                    dst.position(dp2 - dst.arrayOffset());
                    return unmappableForLength;
                }
                if (dl - dp2 < 1) {
                    CoderResult coderResult = CoderResult.OVERFLOW;
                    src.position(sp2 - src.arrayOffset());
                    dst.position(dp2 - dst.arrayOffset());
                    return coderResult;
                }
                char e = this.index2.charAt(this.index1[(c & this.mask1) >> this.shift] + (c & this.mask2));
                if (e == 0 && c != 0) {
                    CoderResult unmappableForLength2 = CoderResult.unmappableForLength(1);
                    src.position(sp2 - src.arrayOffset());
                    dst.position(dp2 - dst.arrayOffset());
                    return unmappableForLength2;
                }
                sp2++;
                int i = dp2;
                dp2++;
                da[i] = (byte) e;
            } catch (Throwable th) {
                src.position(sp2 - src.arrayOffset());
                dst.position(dp2 - dst.arrayOffset());
                throw th;
            }
        }
        CoderResult coderResult2 = CoderResult.UNDERFLOW;
        src.position(sp2 - src.arrayOffset());
        dst.position(dp2 - dst.arrayOffset());
        return coderResult2;
    }

    private CoderResult encodeBufferLoop(CharBuffer src, ByteBuffer dst) {
        int mark = src.position();
        while (src.hasRemaining()) {
            try {
                char c = src.get();
                if (sun.nio.cs.Surrogate.is(c)) {
                    if (this.sgp.parse(c, src) < 0) {
                        CoderResult error = this.sgp.error();
                        src.position(mark);
                        return error;
                    }
                    CoderResult unmappableResult = this.sgp.unmappableResult();
                    src.position(mark);
                    return unmappableResult;
                }
                if (c >= 65534) {
                    CoderResult unmappableForLength = CoderResult.unmappableForLength(1);
                    src.position(mark);
                    return unmappableForLength;
                }
                if (!dst.hasRemaining()) {
                    CoderResult coderResult = CoderResult.OVERFLOW;
                    src.position(mark);
                    return coderResult;
                }
                char e = this.index2.charAt(this.index1[(c & this.mask1) >> this.shift] + (c & this.mask2));
                if (e == 0 && c != 0) {
                    CoderResult unmappableForLength2 = CoderResult.unmappableForLength(1);
                    src.position(mark);
                    return unmappableForLength2;
                }
                mark++;
                dst.put((byte) e);
            } catch (Throwable th) {
                src.position(mark);
                throw th;
            }
        }
        CoderResult coderResult2 = CoderResult.UNDERFLOW;
        src.position(mark);
        return coderResult2;
    }

    @Override // java.nio.charset.CharsetEncoder
    protected CoderResult encodeLoop(CharBuffer src, ByteBuffer dst) {
        if (src.hasArray() && dst.hasArray()) {
            return encodeArrayLoop(src, dst);
        }
        return encodeBufferLoop(src, dst);
    }

    public byte encode(char inputChar) {
        return (byte) this.index2.charAt(this.index1[(inputChar & this.mask1) >> this.shift] + (inputChar & this.mask2));
    }
}
