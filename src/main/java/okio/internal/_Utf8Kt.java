package okio.internal;

import com.sun.tools.javac.util.Position;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okio.Utf8;
import org.jetbrains.annotations.NotNull;

/* compiled from: -Utf8.kt */
@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"��\u0016\n��\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a\n\u0010��\u001a\u00020\u0001*\u00020\u0002\u001a\u001e\u0010\u0003\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"commonAsUtf8ToByteArray", "", "", "commonToUtf8String", "beginIndex", "", "endIndex", "okio"})
@SourceDebugExtension({"SMAP\n-Utf8.kt\nKotlin\n*S Kotlin\n*F\n+ 1 -Utf8.kt\nokio/internal/_Utf8Kt\n+ 2 Utf8.kt\nokio/Utf8\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,60:1\n260#2,16:61\n277#2:78\n397#2,9:79\n127#2:88\n406#2,20:90\n279#2,3:110\n440#2,4:113\n127#2:117\n446#2,10:118\n127#2:128\n456#2,5:129\n127#2:134\n461#2,24:135\n283#2,3:159\n500#2,3:162\n286#2,12:165\n503#2:177\n127#2:178\n506#2,2:179\n127#2:181\n510#2,10:182\n127#2:192\n520#2,5:193\n127#2:198\n525#2,5:199\n127#2:204\n530#2,28:205\n302#2,6:233\n138#2,67:239\n68#3:77\n74#3:89\n*S KotlinDebug\n*F\n+ 1 -Utf8.kt\nokio/internal/_Utf8Kt\n*L\n34#1:61,16\n34#1:78\n34#1:79,9\n34#1:88\n34#1:90,20\n34#1:110,3\n34#1:113,4\n34#1:117\n34#1:118,10\n34#1:128\n34#1:129,5\n34#1:134\n34#1:135,24\n34#1:159,3\n34#1:162,3\n34#1:165,12\n34#1:177\n34#1:178\n34#1:179,2\n34#1:181\n34#1:182,10\n34#1:192\n34#1:193,5\n34#1:198\n34#1:199,5\n34#1:204\n34#1:205,28\n34#1:233,6\n50#1:239,67\n34#1:77\n34#1:89\n*E\n"})
/* loaded from: target.jar:okio/internal/_Utf8Kt.class */
public final class _Utf8Kt {
    public static /* synthetic */ String commonToUtf8String$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        return commonToUtf8String(bArr, i, i2);
    }

    @NotNull
    public static final String commonToUtf8String(@NotNull byte[] $this$commonToUtf8String, int beginIndex, int endIndex) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Intrinsics.checkNotNullParameter($this$commonToUtf8String, "<this>");
        if (beginIndex < 0 || endIndex > $this$commonToUtf8String.length || beginIndex > endIndex) {
            throw new ArrayIndexOutOfBoundsException("size=" + $this$commonToUtf8String.length + " beginIndex=" + beginIndex + " endIndex=" + endIndex);
        }
        char[] chars = new char[endIndex - beginIndex];
        int length = 0;
        int index$iv = beginIndex;
        while (index$iv < endIndex) {
            byte b0$iv = $this$commonToUtf8String[index$iv];
            if (b0$iv >= 0) {
                char c = (char) b0$iv;
                int i7 = length;
                length = i7 + 1;
                chars[i7] = c;
                index$iv++;
                while (index$iv < endIndex && $this$commonToUtf8String[index$iv] >= 0) {
                    int i8 = index$iv;
                    index$iv++;
                    char c2 = (char) $this$commonToUtf8String[i8];
                    int i9 = length;
                    length = i9 + 1;
                    chars[i9] = c2;
                }
            } else if ((b0$iv >> 5) == -2) {
                int i10 = index$iv;
                if (endIndex <= index$iv + 1) {
                    char c3 = (char) 65533;
                    int i11 = length;
                    length = i11 + 1;
                    chars[i11] = c3;
                    Unit unit = Unit.INSTANCE;
                    i = i10;
                    i2 = 1;
                } else {
                    byte b0$iv$iv = $this$commonToUtf8String[index$iv];
                    byte b1$iv$iv = $this$commonToUtf8String[index$iv + 1];
                    if ((b1$iv$iv & 192) == 128) {
                        int codePoint$iv$iv = (3968 ^ b1$iv$iv) ^ (b0$iv$iv << 6);
                        if (codePoint$iv$iv < 128) {
                            char c4 = (char) 65533;
                            int i12 = length;
                            length = i12 + 1;
                            chars[i12] = c4;
                            Unit unit2 = Unit.INSTANCE;
                            i = i10;
                        } else {
                            char c5 = (char) codePoint$iv$iv;
                            int i13 = length;
                            length = i13 + 1;
                            chars[i13] = c5;
                            Unit unit3 = Unit.INSTANCE;
                            i = i10;
                        }
                        i2 = 2;
                    } else {
                        char c6 = (char) 65533;
                        int i14 = length;
                        length = i14 + 1;
                        chars[i14] = c6;
                        Unit unit4 = Unit.INSTANCE;
                        i = i10;
                        i2 = 1;
                    }
                }
                index$iv = i + i2;
            } else if ((b0$iv >> 4) == -2) {
                int i15 = index$iv;
                if (endIndex <= index$iv + 2) {
                    char c7 = (char) 65533;
                    int i16 = length;
                    length = i16 + 1;
                    chars[i16] = c7;
                    Unit unit5 = Unit.INSTANCE;
                    i3 = i15;
                    if (endIndex > index$iv + 1) {
                        byte byte$iv$iv$iv = $this$commonToUtf8String[index$iv + 1];
                        if ((byte$iv$iv$iv & 192) == 128) {
                            i4 = 2;
                        }
                    }
                    i4 = 1;
                } else {
                    byte b0$iv$iv2 = $this$commonToUtf8String[index$iv];
                    byte b1$iv$iv2 = $this$commonToUtf8String[index$iv + 1];
                    if ((b1$iv$iv2 & 192) == 128) {
                        byte b2$iv$iv = $this$commonToUtf8String[index$iv + 2];
                        if ((b2$iv$iv & 192) == 128) {
                            int codePoint$iv$iv2 = (((-123008) ^ b2$iv$iv) ^ (b1$iv$iv2 << 6)) ^ (b0$iv$iv2 << 12);
                            if (codePoint$iv$iv2 < 2048) {
                                char c8 = (char) 65533;
                                int i17 = length;
                                length = i17 + 1;
                                chars[i17] = c8;
                                Unit unit6 = Unit.INSTANCE;
                                i3 = i15;
                            } else {
                                if (55296 <= codePoint$iv$iv2 ? codePoint$iv$iv2 < 57344 : false) {
                                    char c9 = (char) 65533;
                                    int i18 = length;
                                    length = i18 + 1;
                                    chars[i18] = c9;
                                    Unit unit7 = Unit.INSTANCE;
                                    i3 = i15;
                                } else {
                                    char c10 = (char) codePoint$iv$iv2;
                                    int i19 = length;
                                    length = i19 + 1;
                                    chars[i19] = c10;
                                    Unit unit8 = Unit.INSTANCE;
                                    i3 = i15;
                                }
                            }
                            i4 = 3;
                        } else {
                            char c11 = (char) 65533;
                            int i20 = length;
                            length = i20 + 1;
                            chars[i20] = c11;
                            Unit unit9 = Unit.INSTANCE;
                            i3 = i15;
                            i4 = 2;
                        }
                    } else {
                        char c12 = (char) 65533;
                        int i21 = length;
                        length = i21 + 1;
                        chars[i21] = c12;
                        Unit unit10 = Unit.INSTANCE;
                        i3 = i15;
                        i4 = 1;
                    }
                }
                index$iv = i3 + i4;
            } else if ((b0$iv >> 3) == -2) {
                int i22 = index$iv;
                if (endIndex <= index$iv + 3) {
                    if (65533 != 65533) {
                        char c13 = (char) ((65533 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                        int i23 = length;
                        int length2 = i23 + 1;
                        chars[i23] = c13;
                        char c14 = (char) ((65533 & Position.MAXCOLUMN) + 56320);
                        length = length2 + 1;
                        chars[length2] = c14;
                    } else {
                        int i24 = length;
                        length = i24 + 1;
                        chars[i24] = 65533;
                    }
                    Unit unit11 = Unit.INSTANCE;
                    i5 = i22;
                    if (endIndex > index$iv + 1) {
                        byte byte$iv$iv$iv2 = $this$commonToUtf8String[index$iv + 1];
                        if ((byte$iv$iv$iv2 & 192) == 128) {
                            if (endIndex > index$iv + 2) {
                                byte byte$iv$iv$iv3 = $this$commonToUtf8String[index$iv + 2];
                                if ((byte$iv$iv$iv3 & 192) == 128) {
                                    i6 = 3;
                                }
                            }
                            i6 = 2;
                        }
                    }
                    i6 = 1;
                } else {
                    byte b0$iv$iv3 = $this$commonToUtf8String[index$iv];
                    byte b1$iv$iv3 = $this$commonToUtf8String[index$iv + 1];
                    if ((b1$iv$iv3 & 192) == 128) {
                        byte b2$iv$iv2 = $this$commonToUtf8String[index$iv + 2];
                        if ((b2$iv$iv2 & 192) == 128) {
                            byte b3$iv$iv = $this$commonToUtf8String[index$iv + 3];
                            if ((b3$iv$iv & 192) == 128) {
                                int codePoint$iv$iv3 = (((3678080 ^ b3$iv$iv) ^ (b2$iv$iv2 << 6)) ^ (b1$iv$iv3 << 12)) ^ (b0$iv$iv3 << 18);
                                if (codePoint$iv$iv3 > 1114111) {
                                    if (65533 != 65533) {
                                        char c15 = (char) ((65533 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                        int i25 = length;
                                        int length3 = i25 + 1;
                                        chars[i25] = c15;
                                        char c16 = (char) ((65533 & Position.MAXCOLUMN) + 56320);
                                        length = length3 + 1;
                                        chars[length3] = c16;
                                    } else {
                                        int i26 = length;
                                        length = i26 + 1;
                                        chars[i26] = 65533;
                                    }
                                    Unit unit12 = Unit.INSTANCE;
                                    i5 = i22;
                                } else {
                                    if (55296 <= codePoint$iv$iv3 ? codePoint$iv$iv3 < 57344 : false) {
                                        if (65533 != 65533) {
                                            char c17 = (char) ((65533 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                            int i27 = length;
                                            int length4 = i27 + 1;
                                            chars[i27] = c17;
                                            char c18 = (char) ((65533 & Position.MAXCOLUMN) + 56320);
                                            length = length4 + 1;
                                            chars[length4] = c18;
                                        } else {
                                            int i28 = length;
                                            length = i28 + 1;
                                            chars[i28] = 65533;
                                        }
                                        Unit unit13 = Unit.INSTANCE;
                                        i5 = i22;
                                    } else if (codePoint$iv$iv3 < 65536) {
                                        if (65533 != 65533) {
                                            char c19 = (char) ((65533 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                            int i29 = length;
                                            int length5 = i29 + 1;
                                            chars[i29] = c19;
                                            char c20 = (char) ((65533 & Position.MAXCOLUMN) + 56320);
                                            length = length5 + 1;
                                            chars[length5] = c20;
                                        } else {
                                            int i30 = length;
                                            length = i30 + 1;
                                            chars[i30] = 65533;
                                        }
                                        Unit unit14 = Unit.INSTANCE;
                                        i5 = i22;
                                    } else {
                                        if (codePoint$iv$iv3 != 65533) {
                                            char c21 = (char) ((codePoint$iv$iv3 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                            int i31 = length;
                                            int length6 = i31 + 1;
                                            chars[i31] = c21;
                                            char c22 = (char) ((codePoint$iv$iv3 & Position.MAXCOLUMN) + 56320);
                                            length = length6 + 1;
                                            chars[length6] = c22;
                                        } else {
                                            int i32 = length;
                                            length = i32 + 1;
                                            chars[i32] = 65533;
                                        }
                                        Unit unit15 = Unit.INSTANCE;
                                        i5 = i22;
                                    }
                                }
                                i6 = 4;
                            } else {
                                if (65533 != 65533) {
                                    char c23 = (char) ((65533 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                    int i33 = length;
                                    int length7 = i33 + 1;
                                    chars[i33] = c23;
                                    char c24 = (char) ((65533 & Position.MAXCOLUMN) + 56320);
                                    length = length7 + 1;
                                    chars[length7] = c24;
                                } else {
                                    int i34 = length;
                                    length = i34 + 1;
                                    chars[i34] = 65533;
                                }
                                Unit unit16 = Unit.INSTANCE;
                                i5 = i22;
                                i6 = 3;
                            }
                        } else {
                            if (65533 != 65533) {
                                char c25 = (char) ((65533 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                int i35 = length;
                                int length8 = i35 + 1;
                                chars[i35] = c25;
                                char c26 = (char) ((65533 & Position.MAXCOLUMN) + 56320);
                                length = length8 + 1;
                                chars[length8] = c26;
                            } else {
                                int i36 = length;
                                length = i36 + 1;
                                chars[i36] = 65533;
                            }
                            Unit unit17 = Unit.INSTANCE;
                            i5 = i22;
                            i6 = 2;
                        }
                    } else {
                        if (65533 != 65533) {
                            char c27 = (char) ((65533 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                            int i37 = length;
                            int length9 = i37 + 1;
                            chars[i37] = c27;
                            char c28 = (char) ((65533 & Position.MAXCOLUMN) + 56320);
                            length = length9 + 1;
                            chars[length9] = c28;
                        } else {
                            int i38 = length;
                            length = i38 + 1;
                            chars[i38] = 65533;
                        }
                        Unit unit18 = Unit.INSTANCE;
                        i5 = i22;
                        i6 = 1;
                    }
                }
                index$iv = i5 + i6;
            } else {
                int i39 = length;
                length = i39 + 1;
                chars[i39] = 65533;
                index$iv++;
            }
        }
        return StringsKt.concatToString(chars, 0, length);
    }

    @NotNull
    public static final byte[] commonAsUtf8ToByteArray(@NotNull String $this$commonAsUtf8ToByteArray) {
        Intrinsics.checkNotNullParameter($this$commonAsUtf8ToByteArray, "<this>");
        byte[] bytes = new byte[4 * $this$commonAsUtf8ToByteArray.length()];
        int length = $this$commonAsUtf8ToByteArray.length();
        for (int index = 0; index < length; index++) {
            char b0 = $this$commonAsUtf8ToByteArray.charAt(index);
            if (Intrinsics.compare((int) b0, 128) >= 0) {
                int size = index;
                int endIndex$iv = $this$commonAsUtf8ToByteArray.length();
                int index$iv = index;
                while (index$iv < endIndex$iv) {
                    char c$iv = $this$commonAsUtf8ToByteArray.charAt(index$iv);
                    if (Intrinsics.compare((int) c$iv, 128) < 0) {
                        byte c = (byte) c$iv;
                        int i = size;
                        size = i + 1;
                        bytes[i] = c;
                        index$iv++;
                        while (index$iv < endIndex$iv && Intrinsics.compare((int) $this$commonAsUtf8ToByteArray.charAt(index$iv), 128) < 0) {
                            int i2 = index$iv;
                            index$iv++;
                            byte c2 = (byte) $this$commonAsUtf8ToByteArray.charAt(i2);
                            int i3 = size;
                            size = i3 + 1;
                            bytes[i3] = c2;
                        }
                    } else if (Intrinsics.compare((int) c$iv, 2048) < 0) {
                        byte c3 = (byte) ((c$iv >> 6) | 192);
                        int i4 = size;
                        int size2 = i4 + 1;
                        bytes[i4] = c3;
                        byte c4 = (byte) ((c$iv & '?') | 128);
                        size = size2 + 1;
                        bytes[size2] = c4;
                        index$iv++;
                    } else if (55296 <= c$iv ? c$iv < 57344 : false) {
                        if (Intrinsics.compare((int) c$iv, 56319) <= 0 && endIndex$iv > index$iv + 1) {
                            char charAt = $this$commonAsUtf8ToByteArray.charAt(index$iv + 1);
                            if (56320 <= charAt ? charAt < 57344 : false) {
                                int codePoint$iv = ((c$iv << '\n') + $this$commonAsUtf8ToByteArray.charAt(index$iv + 1)) - 56613888;
                                byte c5 = (byte) ((codePoint$iv >> 18) | 240);
                                int i5 = size;
                                int size3 = i5 + 1;
                                bytes[i5] = c5;
                                byte c6 = (byte) (((codePoint$iv >> 12) & 63) | 128);
                                int size4 = size3 + 1;
                                bytes[size3] = c6;
                                byte c7 = (byte) (((codePoint$iv >> 6) & 63) | 128);
                                int size5 = size4 + 1;
                                bytes[size4] = c7;
                                byte c8 = (byte) ((codePoint$iv & 63) | 128);
                                size = size5 + 1;
                                bytes[size5] = c8;
                                index$iv += 2;
                            }
                        }
                        int i6 = size;
                        size = i6 + 1;
                        bytes[i6] = 63;
                        index$iv++;
                    } else {
                        byte c9 = (byte) ((c$iv >> '\f') | 224);
                        int i7 = size;
                        int size6 = i7 + 1;
                        bytes[i7] = c9;
                        byte c10 = (byte) (((c$iv >> 6) & 63) | 128);
                        int size7 = size6 + 1;
                        bytes[size6] = c10;
                        byte c11 = (byte) ((c$iv & '?') | 128);
                        size = size7 + 1;
                        bytes[size7] = c11;
                        index$iv++;
                    }
                }
                byte[] copyOf = Arrays.copyOf(bytes, size);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
                return copyOf;
            }
            bytes[index] = (byte) b0;
        }
        byte[] copyOf2 = Arrays.copyOf(bytes, $this$commonAsUtf8ToByteArray.length());
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(...)");
        return copyOf2;
    }
}
