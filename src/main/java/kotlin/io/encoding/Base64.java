package kotlin.io.encoding;

import com.sun.tools.internal.ws.wsdl.document.jaxws.JAXWSBindingsConstants;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.AbstractList;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Base64.kt */
@ExperimentalEncodingApi
@SinceKotlin(version = "1.8")
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��N\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\t\b\u0017\u0018�� >2\u00020\u0001:\u0002=>B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000e\u001a\u00020��2\u0006\u0010\u000f\u001a\u00020\u0006H\u0007J\"\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014J4\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00142\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014J\"\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014J=\u0010\u001b\u001a\u0002H\u001c\"\f\b��\u0010\u001c*\u00060\u001dj\u0002`\u001e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u0002H\u001c2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014¢\u0006\u0002\u0010\u001fJ\"\u0010 \u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014J4\u0010!\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00142\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014J\"\u0010 \u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\"2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014J4\u0010!\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\"2\u0006\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00142\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014J%\u0010#\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H��¢\u0006\u0002\b$J5\u0010%\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H��¢\u0006\u0002\b&J\u0015\u0010'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\u0014H��¢\u0006\u0002\b)J\b\u0010*\u001a\u00020\u0003H\u0002J0\u0010+\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J%\u0010,\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H��¢\u0006\u0002\b-J%\u0010.\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\"2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H��¢\u0006\u0002\b/J\u0015\u00100\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0011H��¢\u0006\u0002\b1J(\u00102\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u0014H\u0002J\u0010\u00105\u001a\u0002062\u0006\u00103\u001a\u00020\u0014H\u0002J \u00107\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J%\u00108\u001a\u0002062\u0006\u0010(\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H��¢\u0006\u0002\b9J \u0010:\u001a\u0002062\u0006\u0010;\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010<\u001a\u00020\u0014H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\r¨\u0006?"}, d2 = {"Lkotlin/io/encoding/Base64;", "", "isUrlSafe", "", "isMimeScheme", "paddingOption", "Lkotlin/io/encoding/Base64$PaddingOption;", Constants.CTOR, "(ZZLkotlin/io/encoding/Base64$PaddingOption;)V", "isUrlSafe$kotlin_stdlib", "()Z", "isMimeScheme$kotlin_stdlib", "getPaddingOption$kotlin_stdlib", "()Lkotlin/io/encoding/Base64$PaddingOption;", "withPadding", "option", "encodeToByteArray", "", "source", "startIndex", "", "endIndex", "encodeIntoByteArray", "destination", "destinationOffset", "encode", "", "encodeToAppendable", "A", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "([BLjava/lang/Appendable;II)Ljava/lang/Appendable;", "decode", "decodeIntoByteArray", "", "encodeToByteArrayImpl", "encodeToByteArrayImpl$kotlin_stdlib", "encodeIntoByteArrayImpl", "encodeIntoByteArrayImpl$kotlin_stdlib", "encodeSize", "sourceSize", "encodeSize$kotlin_stdlib", "shouldPadOnEncode", "decodeImpl", "decodeSize", "decodeSize$kotlin_stdlib", "charsToBytesImpl", "charsToBytesImpl$kotlin_stdlib", "bytesToStringImpl", "bytesToStringImpl$kotlin_stdlib", "handlePaddingSymbol", "padIndex", "byteStart", "checkPaddingIsAllowed", "", "skipIllegalSymbolsIfMime", "checkSourceBounds", "checkSourceBounds$kotlin_stdlib", "checkDestinationBounds", "destinationSize", "capacityNeeded", "PaddingOption", "Default", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/io/encoding/Base64.class */
public class Base64 {
    private final boolean isUrlSafe;
    private final boolean isMimeScheme;

    @NotNull
    private final PaddingOption paddingOption;
    private static final int bitsPerByte = 8;
    private static final int bitsPerSymbol = 6;
    public static final int bytesPerGroup = 3;
    public static final int symbolsPerGroup = 4;
    public static final byte padSymbol = 61;
    public static final int mimeLineLength = 76;
    private static final int mimeGroupsPerLine = 19;

    @NotNull
    public static final Default Default = new Default(null);

    @NotNull
    private static final byte[] mimeLineSeparatorSymbols = {13, 10};

    @NotNull
    private static final Base64 UrlSafe = new Base64(true, false, PaddingOption.PRESENT);

    @NotNull
    private static final Base64 Mime = new Base64(false, true, PaddingOption.PRESENT);

    public /* synthetic */ Base64(boolean isUrlSafe, boolean isMimeScheme, PaddingOption paddingOption, DefaultConstructorMarker $constructor_marker) {
        this(isUrlSafe, isMimeScheme, paddingOption);
    }

    private Base64(boolean isUrlSafe, boolean isMimeScheme, PaddingOption paddingOption) {
        this.isUrlSafe = isUrlSafe;
        this.isMimeScheme = isMimeScheme;
        this.paddingOption = paddingOption;
        if (!((this.isUrlSafe && this.isMimeScheme) ? false : true)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public final boolean isUrlSafe$kotlin_stdlib() {
        return this.isUrlSafe;
    }

    public final boolean isMimeScheme$kotlin_stdlib() {
        return this.isMimeScheme;
    }

    @NotNull
    public final PaddingOption getPaddingOption$kotlin_stdlib() {
        return this.paddingOption;
    }

    /* compiled from: Base64.kt */
    @SinceKotlin(version = JAXWSBindingsConstants.JAXB_BINDING_VERSION)
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0087\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lkotlin/io/encoding/Base64$PaddingOption;", "", Constants.CTOR, "(Ljava/lang/String;I)V", "PRESENT", "ABSENT", "PRESENT_OPTIONAL", "ABSENT_OPTIONAL", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/io/encoding/Base64$PaddingOption.class */
    public enum PaddingOption {
        PRESENT,
        ABSENT,
        PRESENT_OPTIONAL,
        ABSENT_OPTIONAL;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        @NotNull
        public static EnumEntries<PaddingOption> getEntries() {
            return $ENTRIES;
        }
    }

    @SinceKotlin(version = JAXWSBindingsConstants.JAXB_BINDING_VERSION)
    @NotNull
    public final Base64 withPadding(@NotNull PaddingOption option) {
        Intrinsics.checkNotNullParameter(option, "option");
        return this.paddingOption == option ? this : new Base64(this.isUrlSafe, this.isMimeScheme, option);
    }

    public static /* synthetic */ byte[] encodeToByteArray$default(Base64 base64, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeToByteArray");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return base64.encodeToByteArray(bArr, i, i2);
    }

    @NotNull
    public final byte[] encodeToByteArray(@NotNull byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        return encodeToByteArrayImpl$kotlin_stdlib(source, startIndex, endIndex);
    }

    public static /* synthetic */ int encodeIntoByteArray$default(Base64 base64, byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeIntoByteArray");
        }
        if ((i4 & 4) != 0) {
            i = 0;
        }
        if ((i4 & 8) != 0) {
            i2 = 0;
        }
        if ((i4 & 16) != 0) {
            i3 = bArr.length;
        }
        return base64.encodeIntoByteArray(bArr, bArr2, i, i2, i3);
    }

    public final int encodeIntoByteArray(@NotNull byte[] source, @NotNull byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        return encodeIntoByteArrayImpl$kotlin_stdlib(source, destination, destinationOffset, startIndex, endIndex);
    }

    public static /* synthetic */ String encode$default(Base64 base64, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encode");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return base64.encode(bArr, i, i2);
    }

    @NotNull
    public final String encode(@NotNull byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new String(encodeToByteArrayImpl$kotlin_stdlib(source, startIndex, endIndex), Charsets.ISO_8859_1);
    }

    public static /* synthetic */ Appendable encodeToAppendable$default(Base64 base64, byte[] bArr, Appendable appendable, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeToAppendable");
        }
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = bArr.length;
        }
        return base64.encodeToAppendable(bArr, appendable, i, i2);
    }

    @NotNull
    public final <A extends Appendable> A encodeToAppendable(@NotNull byte[] source, @NotNull A destination, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        String stringResult = new String(encodeToByteArrayImpl$kotlin_stdlib(source, startIndex, endIndex), Charsets.ISO_8859_1);
        destination.append(stringResult);
        return destination;
    }

    public static /* synthetic */ byte[] decode$default(Base64 base64, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decode");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return base64.decode(bArr, i, i2);
    }

    @NotNull
    public final byte[] decode(@NotNull byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
        int decodeSize = decodeSize$kotlin_stdlib(source, startIndex, endIndex);
        byte[] destination = new byte[decodeSize];
        int bytesWritten = decodeImpl(source, destination, 0, startIndex, endIndex);
        if (bytesWritten == destination.length) {
            return destination;
        }
        throw new IllegalStateException("Check failed.");
    }

    public static /* synthetic */ int decodeIntoByteArray$default(Base64 base64, byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
        }
        if ((i4 & 4) != 0) {
            i = 0;
        }
        if ((i4 & 8) != 0) {
            i2 = 0;
        }
        if ((i4 & 16) != 0) {
            i3 = bArr.length;
        }
        return base64.decodeIntoByteArray(bArr, bArr2, i, i2, i3);
    }

    public final int decodeIntoByteArray(@NotNull byte[] source, @NotNull byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
        checkDestinationBounds(destination.length, destinationOffset, decodeSize$kotlin_stdlib(source, startIndex, endIndex));
        return decodeImpl(source, destination, destinationOffset, startIndex, endIndex);
    }

    public static /* synthetic */ byte[] decode$default(Base64 base64, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decode");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return base64.decode(charSequence, i, i2);
    }

    @NotNull
    public final byte[] decode(@NotNull CharSequence source, int startIndex, int endIndex) {
        byte[] charsToBytesImpl$kotlin_stdlib;
        Intrinsics.checkNotNullParameter(source, "source");
        if (source instanceof String) {
            checkSourceBounds$kotlin_stdlib(((String) source).length(), startIndex, endIndex);
            String substring = ((String) source).substring(startIndex, endIndex);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            Charset charset = Charsets.ISO_8859_1;
            Intrinsics.checkNotNull(substring, "null cannot be cast to non-null type java.lang.String");
            charsToBytesImpl$kotlin_stdlib = substring.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(charsToBytesImpl$kotlin_stdlib, "getBytes(...)");
        } else {
            charsToBytesImpl$kotlin_stdlib = charsToBytesImpl$kotlin_stdlib(source, startIndex, endIndex);
        }
        byte[] byteSource = charsToBytesImpl$kotlin_stdlib;
        return decode$default(this, byteSource, 0, 0, 6, (Object) null);
    }

    public static /* synthetic */ int decodeIntoByteArray$default(Base64 base64, CharSequence charSequence, byte[] bArr, int i, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
        }
        if ((i4 & 4) != 0) {
            i = 0;
        }
        if ((i4 & 8) != 0) {
            i2 = 0;
        }
        if ((i4 & 16) != 0) {
            i3 = charSequence.length();
        }
        return base64.decodeIntoByteArray(charSequence, bArr, i, i2, i3);
    }

    public final int decodeIntoByteArray(@NotNull CharSequence source, @NotNull byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        byte[] charsToBytesImpl$kotlin_stdlib;
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (source instanceof String) {
            checkSourceBounds$kotlin_stdlib(((String) source).length(), startIndex, endIndex);
            String substring = ((String) source).substring(startIndex, endIndex);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            Charset charset = Charsets.ISO_8859_1;
            Intrinsics.checkNotNull(substring, "null cannot be cast to non-null type java.lang.String");
            charsToBytesImpl$kotlin_stdlib = substring.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(charsToBytesImpl$kotlin_stdlib, "getBytes(...)");
        } else {
            charsToBytesImpl$kotlin_stdlib = charsToBytesImpl$kotlin_stdlib(source, startIndex, endIndex);
        }
        byte[] byteSource = charsToBytesImpl$kotlin_stdlib;
        return decodeIntoByteArray$default(this, byteSource, destination, destinationOffset, 0, 0, 24, (Object) null);
    }

    @NotNull
    public final byte[] encodeToByteArrayImpl$kotlin_stdlib(@NotNull byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
        int encodeSize = encodeSize$kotlin_stdlib(endIndex - startIndex);
        byte[] destination = new byte[encodeSize];
        encodeIntoByteArrayImpl$kotlin_stdlib(source, destination, 0, startIndex, endIndex);
        return destination;
    }

    public final int encodeIntoByteArrayImpl$kotlin_stdlib(@NotNull byte[] source, @NotNull byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
        checkDestinationBounds(destination.length, destinationOffset, encodeSize$kotlin_stdlib(endIndex - startIndex));
        byte[] encodeMap = this.isUrlSafe ? Base64Kt.access$getBase64UrlEncodeMap$p() : Base64Kt.access$getBase64EncodeMap$p();
        int sourceIndex = startIndex;
        int destinationIndex = destinationOffset;
        int groupsPerLine = this.isMimeScheme ? 19 : Integer.MAX_VALUE;
        while (sourceIndex + 2 < endIndex) {
            int groups = Math.min((endIndex - sourceIndex) / 3, groupsPerLine);
            for (int i = 0; i < groups; i++) {
                int i2 = sourceIndex;
                int sourceIndex2 = sourceIndex + 1;
                int byte1 = source[i2] & 255;
                int sourceIndex3 = sourceIndex2 + 1;
                int byte2 = source[sourceIndex2] & 255;
                sourceIndex = sourceIndex3 + 1;
                int byte3 = source[sourceIndex3] & 255;
                int bits = (byte1 << 16) | (byte2 << 8) | byte3;
                int i3 = destinationIndex;
                int destinationIndex2 = destinationIndex + 1;
                destination[i3] = encodeMap[bits >>> 18];
                int destinationIndex3 = destinationIndex2 + 1;
                destination[destinationIndex2] = encodeMap[(bits >>> 12) & 63];
                int destinationIndex4 = destinationIndex3 + 1;
                destination[destinationIndex3] = encodeMap[(bits >>> 6) & 63];
                destinationIndex = destinationIndex4 + 1;
                destination[destinationIndex4] = encodeMap[bits & 63];
            }
            if (groups == groupsPerLine && sourceIndex != endIndex) {
                int i4 = destinationIndex;
                int destinationIndex5 = destinationIndex + 1;
                destination[i4] = mimeLineSeparatorSymbols[0];
                destinationIndex = destinationIndex5 + 1;
                destination[destinationIndex5] = mimeLineSeparatorSymbols[1];
            }
        }
        switch (endIndex - sourceIndex) {
            case 1:
                int i5 = sourceIndex;
                sourceIndex++;
                int byte12 = source[i5] & 255;
                int bits2 = byte12 << 4;
                int i6 = destinationIndex;
                int destinationIndex6 = destinationIndex + 1;
                destination[i6] = encodeMap[bits2 >>> 6];
                destinationIndex = destinationIndex6 + 1;
                destination[destinationIndex6] = encodeMap[bits2 & 63];
                if (shouldPadOnEncode()) {
                    int destinationIndex7 = destinationIndex + 1;
                    destination[destinationIndex] = 61;
                    destinationIndex = destinationIndex7 + 1;
                    destination[destinationIndex7] = 61;
                    break;
                }
                break;
            case 2:
                int i7 = sourceIndex;
                int sourceIndex4 = sourceIndex + 1;
                int byte13 = source[i7] & 255;
                sourceIndex = sourceIndex4 + 1;
                int byte22 = source[sourceIndex4] & 255;
                int bits3 = (byte13 << 10) | (byte22 << 2);
                int i8 = destinationIndex;
                int destinationIndex8 = destinationIndex + 1;
                destination[i8] = encodeMap[bits3 >>> 12];
                int destinationIndex9 = destinationIndex8 + 1;
                destination[destinationIndex8] = encodeMap[(bits3 >>> 6) & 63];
                destinationIndex = destinationIndex9 + 1;
                destination[destinationIndex9] = encodeMap[bits3 & 63];
                if (shouldPadOnEncode()) {
                    destinationIndex++;
                    destination[destinationIndex] = 61;
                    break;
                }
                break;
        }
        if (sourceIndex == endIndex) {
            return destinationIndex - destinationOffset;
        }
        throw new IllegalStateException("Check failed.");
    }

    public final int encodeSize$kotlin_stdlib(int sourceSize) {
        int groups = sourceSize / 3;
        int trailingBytes = sourceSize % 3;
        int size = groups * 4;
        if (trailingBytes != 0) {
            size += shouldPadOnEncode() ? 4 : trailingBytes + 1;
        }
        if (this.isMimeScheme) {
            size += ((size - 1) / 76) * 2;
        }
        if (size < 0) {
            throw new IllegalArgumentException("Input is too big");
        }
        return size;
    }

    private final boolean shouldPadOnEncode() {
        return this.paddingOption == PaddingOption.PRESENT || this.paddingOption == PaddingOption.PRESENT_OPTIONAL;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0176, code lost:
    
        if (r15 != (-2)) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0183, code lost:
    
        throw new java.lang.IllegalArgumentException("The last unit of input does not have enough bits");
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0188, code lost:
    
        if (r15 == (-8)) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x018d, code lost:
    
        if (r18 != false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0197, code lost:
    
        if (r7.paddingOption != kotlin.io.encoding.Base64.PaddingOption.PRESENT) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x01a4, code lost:
    
        throw new java.lang.IllegalArgumentException("The padding option is set to PRESENT, but the input is not properly padded");
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a7, code lost:
    
        if (r14 == 0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01b4, code lost:
    
        throw new java.lang.IllegalArgumentException("The pad bits must be zeros");
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01b5, code lost:
    
        r0 = skipIllegalSymbolsIfMime(r8, r16, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01c4, code lost:
    
        if (r0 >= r12) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01c7, code lost:
    
        r0 = r8[r0] & 255;
        r2 = new java.lang.StringBuilder().append("Symbol '").append((char) r0).append("'(");
        r3 = java.lang.Integer.toString(r0, kotlin.text.CharsKt.checkRadix(8));
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, "toString(...)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x021b, code lost:
    
        throw new java.lang.IllegalArgumentException(r2.append(r3).append(") at index ").append(r0 - 1).append(" is prohibited after the pad character").toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0220, code lost:
    
        return r17 - r10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int decodeImpl(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        int[] decodeMap = this.isUrlSafe ? Base64Kt.access$getBase64UrlDecodeMap$p() : Base64Kt.access$getBase64DecodeMap$p();
        int payload = 0;
        int byteStart = -8;
        int sourceIndex = startIndex;
        int destinationIndex = destinationOffset;
        boolean hasPadding = false;
        while (true) {
            if (sourceIndex >= endIndex) {
                break;
            }
            if (byteStart == -8 && sourceIndex + 3 < endIndex) {
                int i = sourceIndex;
                int sourceIndex2 = sourceIndex + 1;
                int symbol1 = decodeMap[source[i] & 255];
                int sourceIndex3 = sourceIndex2 + 1;
                int symbol2 = decodeMap[source[sourceIndex2] & 255];
                int sourceIndex4 = sourceIndex3 + 1;
                int symbol3 = decodeMap[source[sourceIndex3] & 255];
                sourceIndex = sourceIndex4 + 1;
                int symbol4 = decodeMap[source[sourceIndex4] & 255];
                int bits = (symbol1 << 18) | (symbol2 << 12) | (symbol3 << 6) | symbol4;
                if (bits >= 0) {
                    int i2 = destinationIndex;
                    int destinationIndex2 = destinationIndex + 1;
                    destination[i2] = (byte) (bits >> 16);
                    int destinationIndex3 = destinationIndex2 + 1;
                    destination[destinationIndex2] = (byte) (bits >> 8);
                    destinationIndex = destinationIndex3 + 1;
                    destination[destinationIndex3] = (byte) bits;
                } else {
                    sourceIndex -= 4;
                }
            }
            int symbol = source[sourceIndex] & 255;
            int symbolBits = decodeMap[symbol];
            if (symbolBits < 0) {
                if (symbolBits == -2) {
                    hasPadding = true;
                    sourceIndex = handlePaddingSymbol(source, sourceIndex, endIndex, byteStart);
                    break;
                }
                if (this.isMimeScheme) {
                    sourceIndex++;
                } else {
                    StringBuilder append = new StringBuilder().append("Invalid symbol '").append((char) symbol).append("'(");
                    String num = Integer.toString(symbol, CharsKt.checkRadix(8));
                    Intrinsics.checkNotNullExpressionValue(num, "toString(...)");
                    throw new IllegalArgumentException(append.append(num).append(") at index ").append(sourceIndex).toString());
                }
            } else {
                sourceIndex++;
                payload = (payload << 6) | symbolBits;
                byteStart += 6;
                if (byteStart >= 0) {
                    int i3 = destinationIndex;
                    destinationIndex++;
                    destination[i3] = (byte) (payload >>> byteStart);
                    payload &= (1 << byteStart) - 1;
                    byteStart -= 8;
                }
            }
        }
    }

    public final int decodeSize$kotlin_stdlib(@NotNull byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        int symbols = endIndex - startIndex;
        if (symbols == 0) {
            return 0;
        }
        if (symbols == 1) {
            throw new IllegalArgumentException("Input should have at least 2 symbols for Base64 decoding, startIndex: " + startIndex + ", endIndex: " + endIndex);
        }
        if (this.isMimeScheme) {
            int index = startIndex;
            while (true) {
                if (index >= endIndex) {
                    break;
                }
                int symbol = source[index] & 255;
                int symbolBits = Base64Kt.access$getBase64DecodeMap$p()[symbol];
                if (symbolBits < 0) {
                    if (symbolBits == -2) {
                        symbols -= endIndex - index;
                        break;
                    }
                    symbols--;
                }
                index++;
            }
        } else if (source[endIndex - 1] == 61) {
            symbols--;
            if (source[endIndex - 2] == 61) {
                symbols--;
            }
        }
        return (int) ((symbols * 6) / 8);
    }

    @NotNull
    public final byte[] charsToBytesImpl$kotlin_stdlib(@NotNull CharSequence source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        checkSourceBounds$kotlin_stdlib(source.length(), startIndex, endIndex);
        byte[] byteArray = new byte[endIndex - startIndex];
        int length = 0;
        for (int index = startIndex; index < endIndex; index++) {
            int symbol = source.charAt(index);
            if (symbol <= 255) {
                int i = length;
                length++;
                byteArray[i] = (byte) symbol;
            } else {
                int i2 = length;
                length++;
                byteArray[i2] = 63;
            }
        }
        return byteArray;
    }

    @NotNull
    public final String bytesToStringImpl$kotlin_stdlib(@NotNull byte[] source) {
        Intrinsics.checkNotNullParameter(source, "source");
        StringBuilder stringBuilder = new StringBuilder(source.length);
        for (byte b : source) {
            stringBuilder.append((char) b);
        }
        return stringBuilder.toString();
    }

    private final int handlePaddingSymbol(byte[] source, int padIndex, int endIndex, int byteStart) {
        switch (byteStart) {
            case -8:
                throw new IllegalArgumentException("Redundant pad character at index " + padIndex);
            case -7:
            case -5:
            case -3:
            default:
                throw new IllegalStateException("Unreachable".toString());
            case -6:
                checkPaddingIsAllowed(padIndex);
                return padIndex + 1;
            case -4:
                checkPaddingIsAllowed(padIndex);
                int secondPadIndex = skipIllegalSymbolsIfMime(source, padIndex + 1, endIndex);
                if (secondPadIndex == endIndex || source[secondPadIndex] != 61) {
                    throw new IllegalArgumentException("Missing one pad character at index " + secondPadIndex);
                }
                return secondPadIndex + 1;
            case -2:
                return padIndex + 1;
        }
    }

    private final void checkPaddingIsAllowed(int padIndex) {
        if (this.paddingOption == PaddingOption.ABSENT) {
            throw new IllegalArgumentException("The padding option is set to ABSENT, but the input has a pad character at index " + padIndex);
        }
    }

    private final int skipIllegalSymbolsIfMime(byte[] source, int startIndex, int endIndex) {
        if (!this.isMimeScheme) {
            return startIndex;
        }
        int sourceIndex = startIndex;
        while (sourceIndex < endIndex) {
            int symbol = source[sourceIndex] & 255;
            if (Base64Kt.access$getBase64DecodeMap$p()[symbol] != -1) {
                return sourceIndex;
            }
            sourceIndex++;
        }
        return sourceIndex;
    }

    public final void checkSourceBounds$kotlin_stdlib(int sourceSize, int startIndex, int endIndex) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, sourceSize);
    }

    private final void checkDestinationBounds(int destinationSize, int destinationOffset, int capacityNeeded) {
        if (destinationOffset < 0 || destinationOffset > destinationSize) {
            throw new IndexOutOfBoundsException("destination offset: " + destinationOffset + ", destination size: " + destinationSize);
        }
        int destinationEndIndex = destinationOffset + capacityNeeded;
        if (destinationEndIndex < 0 || destinationEndIndex > destinationSize) {
            throw new IndexOutOfBoundsException("The destination array does not have enough capacity, destination offset: " + destinationOffset + ", destination size: " + destinationSize + ", capacity needed: " + capacityNeeded);
        }
    }

    /* compiled from: Base64.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\b\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\nX\u0080T¢\u0006\u0002\n��R\u000e\u0010\u000b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n��R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n��R\u0014\u0010\r\u001a\u00020\u000eX\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0001¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0001¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0013¨\u0006\u0016"}, d2 = {"Lkotlin/io/encoding/Base64$Default;", "Lkotlin/io/encoding/Base64;", Constants.CTOR, "()V", "bitsPerByte", "", "bitsPerSymbol", "bytesPerGroup", "symbolsPerGroup", "padSymbol", "", "mimeLineLength", "mimeGroupsPerLine", "mimeLineSeparatorSymbols", "", "getMimeLineSeparatorSymbols$kotlin_stdlib", "()[B", "UrlSafe", "getUrlSafe", "()Lkotlin/io/encoding/Base64;", "Mime", "getMime", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/io/encoding/Base64$Default.class */
    public static final class Default extends Base64 {
        public /* synthetic */ Default(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Default() {
            super(false, false, PaddingOption.PRESENT, null);
        }

        @NotNull
        public final byte[] getMimeLineSeparatorSymbols$kotlin_stdlib() {
            return Base64.mimeLineSeparatorSymbols;
        }

        @NotNull
        public final Base64 getUrlSafe() {
            return Base64.UrlSafe;
        }

        @NotNull
        public final Base64 getMime() {
            return Base64.Mime;
        }
    }
}
