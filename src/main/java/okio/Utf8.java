package okio;

import com.sun.tools.javac.util.Position;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* compiled from: Utf8.kt */
@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"��D\n��\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n��\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0001H\u0080\b\u001a\u0011\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0007H\u0080\b\u001a4\u0010\u0010\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001��\u001a4\u0010\u0017\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001��\u001a4\u0010\u0018\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001��\u001a4\u0010\u0019\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001��\u001a4\u0010\u001a\u001a\u00020\u0016*\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001��\u001a4\u0010\u001c\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001��\u001a%\u0010\u001d\u001a\u00020\u001e*\u00020\u001b2\b\b\u0002\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u0001H\u0007¢\u0006\u0002\b\u001f\"\u000e\u0010��\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\b\u001a\u00020\tX\u0080T¢\u0006\u0002\n��\"\u000e\u0010\n\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006 "}, d2 = {"HIGH_SURROGATE_HEADER", "", "LOG_SURROGATE_HEADER", "MASK_2BYTES", "MASK_3BYTES", "MASK_4BYTES", "REPLACEMENT_BYTE", "", "REPLACEMENT_CHARACTER", "", "REPLACEMENT_CODE_POINT", "isIsoControl", "", "codePoint", "isUtf8Continuation", "byte", "process2Utf8Bytes", "", "beginIndex", "endIndex", "yield", "Lkotlin/Function1;", "", "process3Utf8Bytes", "process4Utf8Bytes", "processUtf16Chars", "processUtf8Bytes", "", "processUtf8CodePoints", "utf8Size", "", "size", "okio"})
@JvmName(name = "Utf8")
@SourceDebugExtension({"SMAP\nUtf8.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utf8.kt\nokio/Utf8\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,559:1\n397#1,9:563\n127#1:572\n406#1,20:574\n440#1,4:595\n127#1:599\n446#1,10:601\n127#1:611\n456#1,5:612\n127#1:617\n461#1,24:618\n500#1,4:643\n127#1:647\n506#1,2:649\n127#1:651\n510#1,10:652\n127#1:662\n520#1,5:663\n127#1:668\n525#1,5:669\n127#1:674\n530#1,28:675\n397#1,9:704\n127#1:713\n406#1,20:715\n440#1,4:736\n127#1:740\n446#1,10:742\n127#1:752\n456#1,5:753\n127#1:758\n461#1,24:759\n500#1,4:784\n127#1:788\n506#1,2:790\n127#1:792\n510#1,10:793\n127#1:803\n520#1,5:804\n127#1:809\n525#1,5:810\n127#1:815\n530#1,28:816\n127#1:844\n127#1:846\n127#1:848\n127#1:850\n127#1:852\n127#1:854\n127#1:856\n127#1:858\n127#1:860\n1#2:560\n74#3:561\n68#3:562\n74#3:573\n68#3:594\n74#3:600\n68#3:642\n74#3:648\n68#3:703\n74#3:714\n68#3:735\n74#3:741\n68#3:783\n74#3:789\n74#3:845\n74#3:847\n74#3:849\n74#3:851\n74#3:853\n74#3:855\n74#3:857\n74#3:859\n74#3:861\n*S KotlinDebug\n*F\n+ 1 Utf8.kt\nokio/Utf8\n*L\n228#1:563,9\n228#1:572\n228#1:574,20\n232#1:595,4\n232#1:599\n232#1:601,10\n232#1:611\n232#1:612,5\n232#1:617\n232#1:618,24\n236#1:643,4\n236#1:647\n236#1:649,2\n236#1:651\n236#1:652,10\n236#1:662\n236#1:663,5\n236#1:668\n236#1:669,5\n236#1:674\n236#1:675,28\n277#1:704,9\n277#1:713\n277#1:715,20\n281#1:736,4\n281#1:740\n281#1:742,10\n281#1:752\n281#1:753,5\n281#1:758\n281#1:759,24\n285#1:784,4\n285#1:788\n285#1:790,2\n285#1:792\n285#1:793,10\n285#1:803\n285#1:804,5\n285#1:809\n285#1:810,5\n285#1:815\n285#1:816,28\n405#1:844\n443#1:846\n455#1:848\n460#1:850\n503#1:852\n507#1:854\n519#1:856\n524#1:858\n529#1:860\n127#1:561\n226#1:562\n228#1:573\n230#1:594\n232#1:600\n234#1:642\n236#1:648\n275#1:703\n277#1:714\n279#1:735\n281#1:741\n283#1:783\n285#1:789\n405#1:845\n443#1:847\n455#1:849\n460#1:851\n503#1:853\n507#1:855\n519#1:857\n524#1:859\n529#1:861\n*E\n"})
/* loaded from: target.jar:okio/Utf8.class */
public final class Utf8 {
    public static final byte REPLACEMENT_BYTE = 63;
    public static final char REPLACEMENT_CHARACTER = 65533;
    public static final int REPLACEMENT_CODE_POINT = 65533;
    public static final int HIGH_SURROGATE_HEADER = 55232;
    public static final int LOG_SURROGATE_HEADER = 56320;
    public static final int MASK_2BYTES = 3968;
    public static final int MASK_3BYTES = -123008;
    public static final int MASK_4BYTES = 3678080;

    @JvmOverloads
    @JvmName(name = "size")
    public static final long size(@NotNull String $this$utf8Size, int beginIndex) {
        Intrinsics.checkNotNullParameter($this$utf8Size, "<this>");
        return size$default($this$utf8Size, beginIndex, 0, 2, null);
    }

    @JvmOverloads
    @JvmName(name = "size")
    public static final long size(@NotNull String $this$utf8Size) {
        Intrinsics.checkNotNullParameter($this$utf8Size, "<this>");
        return size$default($this$utf8Size, 0, 0, 3, null);
    }

    public static /* synthetic */ long size$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return size(str, i, i2);
    }

    @JvmOverloads
    @JvmName(name = "size")
    public static final long size(@NotNull String $this$utf8Size, int beginIndex, int endIndex) {
        Intrinsics.checkNotNullParameter($this$utf8Size, "<this>");
        if (!(beginIndex >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + beginIndex).toString());
        }
        if (!(endIndex >= beginIndex)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + endIndex + " < " + beginIndex).toString());
        }
        if (!(endIndex <= $this$utf8Size.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + endIndex + " > " + $this$utf8Size.length()).toString());
        }
        long result = 0;
        int i = beginIndex;
        while (i < endIndex) {
            int c = $this$utf8Size.charAt(i);
            if (c < 128) {
                result++;
                i++;
            } else if (c < 2048) {
                result += 2;
                i++;
            } else if (c < 55296 || c > 57343) {
                result += 3;
                i++;
            } else {
                int low = i + 1 < endIndex ? $this$utf8Size.charAt(i + 1) : 0;
                if (c > 56319 || low < 56320 || low > 57343) {
                    result++;
                    i++;
                } else {
                    result += 4;
                    i += 2;
                }
            }
        }
        return result;
    }

    public static final boolean isIsoControl(int codePoint) {
        if (!(0 <= codePoint ? codePoint < 32 : false)) {
            if (!(127 <= codePoint ? codePoint < 160 : false)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isUtf8Continuation(byte b) {
        return (b & 192) == 128;
    }

    public static final void processUtf8Bytes(@NotNull String $this$processUtf8Bytes, int beginIndex, int endIndex, @NotNull Function1<? super Byte, Unit> yield) {
        Intrinsics.checkNotNullParameter($this$processUtf8Bytes, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int index = beginIndex;
        while (index < endIndex) {
            char c = $this$processUtf8Bytes.charAt(index);
            if (Intrinsics.compare((int) c, 128) < 0) {
                yield.invoke(Byte.valueOf((byte) c));
                index++;
                while (index < endIndex && Intrinsics.compare((int) $this$processUtf8Bytes.charAt(index), 128) < 0) {
                    int i = index;
                    index++;
                    yield.invoke(Byte.valueOf((byte) $this$processUtf8Bytes.charAt(i)));
                }
            } else if (Intrinsics.compare((int) c, 2048) < 0) {
                yield.invoke(Byte.valueOf((byte) ((c >> 6) | 192)));
                yield.invoke(Byte.valueOf((byte) ((c & '?') | 128)));
                index++;
            } else {
                if (55296 <= c ? c < 57344 : false) {
                    if (Intrinsics.compare((int) c, 56319) <= 0 && endIndex > index + 1) {
                        char charAt = $this$processUtf8Bytes.charAt(index + 1);
                        if (56320 <= charAt ? charAt < 57344 : false) {
                            int codePoint = ((c << '\n') + $this$processUtf8Bytes.charAt(index + 1)) - 56613888;
                            yield.invoke(Byte.valueOf((byte) ((codePoint >> 18) | 240)));
                            yield.invoke(Byte.valueOf((byte) (((codePoint >> 12) & 63) | 128)));
                            yield.invoke(Byte.valueOf((byte) (((codePoint >> 6) & 63) | 128)));
                            yield.invoke(Byte.valueOf((byte) ((codePoint & 63) | 128)));
                            index += 2;
                        }
                    }
                    yield.invoke((byte) 63);
                    index++;
                } else {
                    yield.invoke(Byte.valueOf((byte) ((c >> '\f') | 224)));
                    yield.invoke(Byte.valueOf((byte) (((c >> 6) & 63) | 128)));
                    yield.invoke(Byte.valueOf((byte) ((c & '?') | 128)));
                    index++;
                }
            }
        }
    }

    public static final void processUtf8CodePoints(@NotNull byte[] $this$processUtf8CodePoints, int beginIndex, int endIndex, @NotNull Function1<? super Integer, Unit> yield) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Intrinsics.checkNotNullParameter($this$processUtf8CodePoints, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int index = beginIndex;
        while (index < endIndex) {
            byte b0 = $this$processUtf8CodePoints[index];
            if (b0 >= 0) {
                yield.invoke(Integer.valueOf(b0));
                index++;
                while (index < endIndex && $this$processUtf8CodePoints[index] >= 0) {
                    int i7 = index;
                    index++;
                    yield.invoke(Integer.valueOf($this$processUtf8CodePoints[i7]));
                }
            } else if ((b0 >> 5) == -2) {
                int i8 = index;
                if (endIndex <= index + 1) {
                    yield.invoke(65533);
                    Unit unit = Unit.INSTANCE;
                    i = i8;
                    i2 = 1;
                } else {
                    byte b0$iv = $this$processUtf8CodePoints[index];
                    byte b1$iv = $this$processUtf8CodePoints[index + 1];
                    if ((b1$iv & 192) == 128) {
                        int codePoint$iv = (3968 ^ b1$iv) ^ (b0$iv << 6);
                        if (codePoint$iv < 128) {
                            yield.invoke(65533);
                            Unit unit2 = Unit.INSTANCE;
                            i = i8;
                        } else {
                            yield.invoke(Integer.valueOf(codePoint$iv));
                            Unit unit3 = Unit.INSTANCE;
                            i = i8;
                        }
                        i2 = 2;
                    } else {
                        yield.invoke(65533);
                        Unit unit4 = Unit.INSTANCE;
                        i = i8;
                        i2 = 1;
                    }
                }
                index = i + i2;
            } else if ((b0 >> 4) == -2) {
                int i9 = index;
                if (endIndex <= index + 2) {
                    yield.invoke(65533);
                    Unit unit5 = Unit.INSTANCE;
                    i3 = i9;
                    if (endIndex > index + 1) {
                        byte byte$iv$iv = $this$processUtf8CodePoints[index + 1];
                        if ((byte$iv$iv & 192) == 128) {
                            i4 = 2;
                        }
                    }
                    i4 = 1;
                } else {
                    byte b0$iv2 = $this$processUtf8CodePoints[index];
                    byte b1$iv2 = $this$processUtf8CodePoints[index + 1];
                    if ((b1$iv2 & 192) == 128) {
                        byte b2$iv = $this$processUtf8CodePoints[index + 2];
                        if ((b2$iv & 192) == 128) {
                            int codePoint$iv2 = (((-123008) ^ b2$iv) ^ (b1$iv2 << 6)) ^ (b0$iv2 << 12);
                            if (codePoint$iv2 < 2048) {
                                yield.invoke(65533);
                                Unit unit6 = Unit.INSTANCE;
                                i3 = i9;
                            } else {
                                if (55296 <= codePoint$iv2 ? codePoint$iv2 < 57344 : false) {
                                    yield.invoke(65533);
                                    Unit unit7 = Unit.INSTANCE;
                                    i3 = i9;
                                } else {
                                    yield.invoke(Integer.valueOf(codePoint$iv2));
                                    Unit unit8 = Unit.INSTANCE;
                                    i3 = i9;
                                }
                            }
                            i4 = 3;
                        } else {
                            yield.invoke(65533);
                            Unit unit9 = Unit.INSTANCE;
                            i3 = i9;
                            i4 = 2;
                        }
                    } else {
                        yield.invoke(65533);
                        Unit unit10 = Unit.INSTANCE;
                        i3 = i9;
                        i4 = 1;
                    }
                }
                index = i3 + i4;
            } else if ((b0 >> 3) == -2) {
                int i10 = index;
                if (endIndex <= index + 3) {
                    yield.invoke(65533);
                    Unit unit11 = Unit.INSTANCE;
                    i5 = i10;
                    if (endIndex > index + 1) {
                        byte byte$iv$iv2 = $this$processUtf8CodePoints[index + 1];
                        if ((byte$iv$iv2 & 192) == 128) {
                            if (endIndex > index + 2) {
                                byte byte$iv$iv3 = $this$processUtf8CodePoints[index + 2];
                                if ((byte$iv$iv3 & 192) == 128) {
                                    i6 = 3;
                                }
                            }
                            i6 = 2;
                        }
                    }
                    i6 = 1;
                } else {
                    byte b0$iv3 = $this$processUtf8CodePoints[index];
                    byte b1$iv3 = $this$processUtf8CodePoints[index + 1];
                    if ((b1$iv3 & 192) == 128) {
                        byte b2$iv2 = $this$processUtf8CodePoints[index + 2];
                        if ((b2$iv2 & 192) == 128) {
                            byte b3$iv = $this$processUtf8CodePoints[index + 3];
                            if ((b3$iv & 192) == 128) {
                                int codePoint$iv3 = (((3678080 ^ b3$iv) ^ (b2$iv2 << 6)) ^ (b1$iv3 << 12)) ^ (b0$iv3 << 18);
                                if (codePoint$iv3 > 1114111) {
                                    yield.invoke(65533);
                                    Unit unit12 = Unit.INSTANCE;
                                    i5 = i10;
                                } else {
                                    if (55296 <= codePoint$iv3 ? codePoint$iv3 < 57344 : false) {
                                        yield.invoke(65533);
                                        Unit unit13 = Unit.INSTANCE;
                                        i5 = i10;
                                    } else if (codePoint$iv3 < 65536) {
                                        yield.invoke(65533);
                                        Unit unit14 = Unit.INSTANCE;
                                        i5 = i10;
                                    } else {
                                        yield.invoke(Integer.valueOf(codePoint$iv3));
                                        Unit unit15 = Unit.INSTANCE;
                                        i5 = i10;
                                    }
                                }
                                i6 = 4;
                            } else {
                                yield.invoke(65533);
                                Unit unit16 = Unit.INSTANCE;
                                i5 = i10;
                                i6 = 3;
                            }
                        } else {
                            yield.invoke(65533);
                            Unit unit17 = Unit.INSTANCE;
                            i5 = i10;
                            i6 = 2;
                        }
                    } else {
                        yield.invoke(65533);
                        Unit unit18 = Unit.INSTANCE;
                        i5 = i10;
                        i6 = 1;
                    }
                }
                index = i5 + i6;
            } else {
                yield.invoke(65533);
                index++;
            }
        }
    }

    public static final void processUtf16Chars(@NotNull byte[] $this$processUtf16Chars, int beginIndex, int endIndex, @NotNull Function1<? super Character, Unit> yield) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Intrinsics.checkNotNullParameter($this$processUtf16Chars, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int index = beginIndex;
        while (index < endIndex) {
            byte b0 = $this$processUtf16Chars[index];
            if (b0 >= 0) {
                yield.invoke(Character.valueOf((char) b0));
                index++;
                while (index < endIndex && $this$processUtf16Chars[index] >= 0) {
                    int i7 = index;
                    index++;
                    yield.invoke(Character.valueOf((char) $this$processUtf16Chars[i7]));
                }
            } else if ((b0 >> 5) == -2) {
                int i8 = index;
                if (endIndex <= index + 1) {
                    yield.invoke(Character.valueOf((char) 65533));
                    Unit unit = Unit.INSTANCE;
                    i = i8;
                    i2 = 1;
                } else {
                    byte b0$iv = $this$processUtf16Chars[index];
                    byte b1$iv = $this$processUtf16Chars[index + 1];
                    if ((b1$iv & 192) == 128) {
                        int codePoint$iv = (3968 ^ b1$iv) ^ (b0$iv << 6);
                        if (codePoint$iv < 128) {
                            yield.invoke(Character.valueOf((char) 65533));
                            Unit unit2 = Unit.INSTANCE;
                            i = i8;
                        } else {
                            yield.invoke(Character.valueOf((char) codePoint$iv));
                            Unit unit3 = Unit.INSTANCE;
                            i = i8;
                        }
                        i2 = 2;
                    } else {
                        yield.invoke(Character.valueOf((char) 65533));
                        Unit unit4 = Unit.INSTANCE;
                        i = i8;
                        i2 = 1;
                    }
                }
                index = i + i2;
            } else if ((b0 >> 4) == -2) {
                int i9 = index;
                if (endIndex <= index + 2) {
                    yield.invoke(Character.valueOf((char) 65533));
                    Unit unit5 = Unit.INSTANCE;
                    i3 = i9;
                    if (endIndex > index + 1) {
                        byte byte$iv$iv = $this$processUtf16Chars[index + 1];
                        if ((byte$iv$iv & 192) == 128) {
                            i4 = 2;
                        }
                    }
                    i4 = 1;
                } else {
                    byte b0$iv2 = $this$processUtf16Chars[index];
                    byte b1$iv2 = $this$processUtf16Chars[index + 1];
                    if ((b1$iv2 & 192) == 128) {
                        byte b2$iv = $this$processUtf16Chars[index + 2];
                        if ((b2$iv & 192) == 128) {
                            int codePoint$iv2 = (((-123008) ^ b2$iv) ^ (b1$iv2 << 6)) ^ (b0$iv2 << 12);
                            if (codePoint$iv2 < 2048) {
                                yield.invoke(Character.valueOf((char) 65533));
                                Unit unit6 = Unit.INSTANCE;
                                i3 = i9;
                            } else {
                                if (55296 <= codePoint$iv2 ? codePoint$iv2 < 57344 : false) {
                                    yield.invoke(Character.valueOf((char) 65533));
                                    Unit unit7 = Unit.INSTANCE;
                                    i3 = i9;
                                } else {
                                    yield.invoke(Character.valueOf((char) codePoint$iv2));
                                    Unit unit8 = Unit.INSTANCE;
                                    i3 = i9;
                                }
                            }
                            i4 = 3;
                        } else {
                            yield.invoke(Character.valueOf((char) 65533));
                            Unit unit9 = Unit.INSTANCE;
                            i3 = i9;
                            i4 = 2;
                        }
                    } else {
                        yield.invoke(Character.valueOf((char) 65533));
                        Unit unit10 = Unit.INSTANCE;
                        i3 = i9;
                        i4 = 1;
                    }
                }
                index = i3 + i4;
            } else if ((b0 >> 3) == -2) {
                int i10 = index;
                if (endIndex <= index + 3) {
                    if (65533 != 65533) {
                        yield.invoke(Character.valueOf((char) ((65533 >>> 10) + HIGH_SURROGATE_HEADER)));
                        yield.invoke(Character.valueOf((char) ((65533 & Position.MAXCOLUMN) + 56320)));
                    } else {
                        yield.invoke((char) 65533);
                    }
                    Unit unit11 = Unit.INSTANCE;
                    i5 = i10;
                    if (endIndex > index + 1) {
                        byte byte$iv$iv2 = $this$processUtf16Chars[index + 1];
                        if ((byte$iv$iv2 & 192) == 128) {
                            if (endIndex > index + 2) {
                                byte byte$iv$iv3 = $this$processUtf16Chars[index + 2];
                                if ((byte$iv$iv3 & 192) == 128) {
                                    i6 = 3;
                                }
                            }
                            i6 = 2;
                        }
                    }
                    i6 = 1;
                } else {
                    byte b0$iv3 = $this$processUtf16Chars[index];
                    byte b1$iv3 = $this$processUtf16Chars[index + 1];
                    if ((b1$iv3 & 192) == 128) {
                        byte b2$iv2 = $this$processUtf16Chars[index + 2];
                        if ((b2$iv2 & 192) == 128) {
                            byte b3$iv = $this$processUtf16Chars[index + 3];
                            if ((b3$iv & 192) == 128) {
                                int codePoint$iv3 = (((3678080 ^ b3$iv) ^ (b2$iv2 << 6)) ^ (b1$iv3 << 12)) ^ (b0$iv3 << 18);
                                if (codePoint$iv3 > 1114111) {
                                    if (65533 != 65533) {
                                        yield.invoke(Character.valueOf((char) ((65533 >>> 10) + HIGH_SURROGATE_HEADER)));
                                        yield.invoke(Character.valueOf((char) ((65533 & Position.MAXCOLUMN) + 56320)));
                                    } else {
                                        yield.invoke((char) 65533);
                                    }
                                    Unit unit12 = Unit.INSTANCE;
                                    i5 = i10;
                                } else {
                                    if (55296 <= codePoint$iv3 ? codePoint$iv3 < 57344 : false) {
                                        if (65533 != 65533) {
                                            yield.invoke(Character.valueOf((char) ((65533 >>> 10) + HIGH_SURROGATE_HEADER)));
                                            yield.invoke(Character.valueOf((char) ((65533 & Position.MAXCOLUMN) + 56320)));
                                        } else {
                                            yield.invoke((char) 65533);
                                        }
                                        Unit unit13 = Unit.INSTANCE;
                                        i5 = i10;
                                    } else if (codePoint$iv3 < 65536) {
                                        if (65533 != 65533) {
                                            yield.invoke(Character.valueOf((char) ((65533 >>> 10) + HIGH_SURROGATE_HEADER)));
                                            yield.invoke(Character.valueOf((char) ((65533 & Position.MAXCOLUMN) + 56320)));
                                        } else {
                                            yield.invoke((char) 65533);
                                        }
                                        Unit unit14 = Unit.INSTANCE;
                                        i5 = i10;
                                    } else {
                                        if (codePoint$iv3 != 65533) {
                                            yield.invoke(Character.valueOf((char) ((codePoint$iv3 >>> 10) + HIGH_SURROGATE_HEADER)));
                                            yield.invoke(Character.valueOf((char) ((codePoint$iv3 & Position.MAXCOLUMN) + 56320)));
                                        } else {
                                            yield.invoke((char) 65533);
                                        }
                                        Unit unit15 = Unit.INSTANCE;
                                        i5 = i10;
                                    }
                                }
                                i6 = 4;
                            } else {
                                if (65533 != 65533) {
                                    yield.invoke(Character.valueOf((char) ((65533 >>> 10) + HIGH_SURROGATE_HEADER)));
                                    yield.invoke(Character.valueOf((char) ((65533 & Position.MAXCOLUMN) + 56320)));
                                } else {
                                    yield.invoke((char) 65533);
                                }
                                Unit unit16 = Unit.INSTANCE;
                                i5 = i10;
                                i6 = 3;
                            }
                        } else {
                            if (65533 != 65533) {
                                yield.invoke(Character.valueOf((char) ((65533 >>> 10) + HIGH_SURROGATE_HEADER)));
                                yield.invoke(Character.valueOf((char) ((65533 & Position.MAXCOLUMN) + 56320)));
                            } else {
                                yield.invoke((char) 65533);
                            }
                            Unit unit17 = Unit.INSTANCE;
                            i5 = i10;
                            i6 = 2;
                        }
                    } else {
                        if (65533 != 65533) {
                            yield.invoke(Character.valueOf((char) ((65533 >>> 10) + HIGH_SURROGATE_HEADER)));
                            yield.invoke(Character.valueOf((char) ((65533 & Position.MAXCOLUMN) + 56320)));
                        } else {
                            yield.invoke((char) 65533);
                        }
                        Unit unit18 = Unit.INSTANCE;
                        i5 = i10;
                        i6 = 1;
                    }
                }
                index = i5 + i6;
            } else {
                yield.invoke((char) 65533);
                index++;
            }
        }
    }

    public static final int process2Utf8Bytes(@NotNull byte[] $this$process2Utf8Bytes, int beginIndex, int endIndex, @NotNull Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter($this$process2Utf8Bytes, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        if (endIndex <= beginIndex + 1) {
            yield.invoke(65533);
            return 1;
        }
        byte b0 = $this$process2Utf8Bytes[beginIndex];
        byte b1 = $this$process2Utf8Bytes[beginIndex + 1];
        if (!((b1 & 192) == 128)) {
            yield.invoke(65533);
            return 1;
        }
        int codePoint = (3968 ^ b1) ^ (b0 << 6);
        if (codePoint < 128) {
            yield.invoke(65533);
            return 2;
        }
        yield.invoke(Integer.valueOf(codePoint));
        return 2;
    }

    public static final int process3Utf8Bytes(@NotNull byte[] $this$process3Utf8Bytes, int beginIndex, int endIndex, @NotNull Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter($this$process3Utf8Bytes, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        if (endIndex <= beginIndex + 2) {
            yield.invoke(65533);
            if (endIndex <= beginIndex + 1) {
                return 1;
            }
            byte byte$iv = $this$process3Utf8Bytes[beginIndex + 1];
            return !((byte$iv & 192) == 128) ? 1 : 2;
        }
        byte b0 = $this$process3Utf8Bytes[beginIndex];
        byte b1 = $this$process3Utf8Bytes[beginIndex + 1];
        if (!((b1 & 192) == 128)) {
            yield.invoke(65533);
            return 1;
        }
        byte b2 = $this$process3Utf8Bytes[beginIndex + 2];
        if (!((b2 & 192) == 128)) {
            yield.invoke(65533);
            return 2;
        }
        int codePoint = (((-123008) ^ b2) ^ (b1 << 6)) ^ (b0 << 12);
        if (codePoint < 2048) {
            yield.invoke(65533);
            return 3;
        }
        if (55296 <= codePoint ? codePoint < 57344 : false) {
            yield.invoke(65533);
            return 3;
        }
        yield.invoke(Integer.valueOf(codePoint));
        return 3;
    }

    public static final int process4Utf8Bytes(@NotNull byte[] $this$process4Utf8Bytes, int beginIndex, int endIndex, @NotNull Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter($this$process4Utf8Bytes, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        if (endIndex <= beginIndex + 3) {
            yield.invoke(65533);
            if (endIndex <= beginIndex + 1) {
                return 1;
            }
            byte byte$iv = $this$process4Utf8Bytes[beginIndex + 1];
            if (!((byte$iv & 192) == 128)) {
                return 1;
            }
            if (endIndex <= beginIndex + 2) {
                return 2;
            }
            byte byte$iv2 = $this$process4Utf8Bytes[beginIndex + 2];
            return !((byte$iv2 & 192) == 128) ? 2 : 3;
        }
        byte b0 = $this$process4Utf8Bytes[beginIndex];
        byte b1 = $this$process4Utf8Bytes[beginIndex + 1];
        if (!((b1 & 192) == 128)) {
            yield.invoke(65533);
            return 1;
        }
        byte b2 = $this$process4Utf8Bytes[beginIndex + 2];
        if (!((b2 & 192) == 128)) {
            yield.invoke(65533);
            return 2;
        }
        byte b3 = $this$process4Utf8Bytes[beginIndex + 3];
        if (!((b3 & 192) == 128)) {
            yield.invoke(65533);
            return 3;
        }
        int codePoint = (((3678080 ^ b3) ^ (b2 << 6)) ^ (b1 << 12)) ^ (b0 << 18);
        if (codePoint > 1114111) {
            yield.invoke(65533);
            return 4;
        }
        if (55296 <= codePoint ? codePoint < 57344 : false) {
            yield.invoke(65533);
            return 4;
        }
        if (codePoint < 65536) {
            yield.invoke(65533);
            return 4;
        }
        yield.invoke(Integer.valueOf(codePoint));
        return 4;
    }
}
