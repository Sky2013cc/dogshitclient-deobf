package okhttp3.internal.idn;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;

/* compiled from: IdnaMappingTable.kt */
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\b��\u0018��2\u00020\u0001B\u001f\b��\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0002J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/idn/IdnaMappingTable;", "", "sections", "", "ranges", "mappings", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getMappings", "()Ljava/lang/String;", "getRanges", "getSections", "findRangesOffset", "", "codePoint", "position", "limit", "findSectionsIndex", "map", "", "sink", "Lokio/BufferedSink;", "okhttp"})
@SourceDebugExtension({"SMAP\nIdnaMappingTable.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IdnaMappingTable.kt\nokhttp3/internal/idn/IdnaMappingTable\n+ 2 IdnaMappingTable.kt\nokhttp3/internal/idn/IdnaMappingTableKt\n*L\n1#1,286:1\n272#2,13:287\n272#2,13:300\n*S KotlinDebug\n*F\n+ 1 IdnaMappingTable.kt\nokhttp3/internal/idn/IdnaMappingTable\n*L\n209#1:287,13\n237#1:300,13\n*E\n"})
/* loaded from: target.jar:okhttp3/internal/idn/IdnaMappingTable.class */
public final class IdnaMappingTable {

    @NotNull
    private final String sections;

    @NotNull
    private final String ranges;

    @NotNull
    private final String mappings;

    public IdnaMappingTable(@NotNull String sections, @NotNull String ranges, @NotNull String mappings) {
        Intrinsics.checkNotNullParameter(sections, "sections");
        Intrinsics.checkNotNullParameter(ranges, "ranges");
        Intrinsics.checkNotNullParameter(mappings, "mappings");
        this.sections = sections;
        this.ranges = ranges;
        this.mappings = mappings;
    }

    @NotNull
    public final String getSections() {
        return this.sections;
    }

    @NotNull
    public final String getRanges() {
        return this.ranges;
    }

    @NotNull
    public final String getMappings() {
        return this.mappings;
    }

    public final boolean map(int codePoint, @NotNull BufferedSink sink) {
        boolean z;
        boolean z2;
        boolean z3;
        Intrinsics.checkNotNullParameter(sink, "sink");
        int sectionsIndex = findSectionsIndex(codePoint);
        int rangesPosition = IdnaMappingTableKt.read14BitInt(this.sections, sectionsIndex + 2);
        int rangesLimit = sectionsIndex + 4 < this.sections.length() ? IdnaMappingTableKt.read14BitInt(this.sections, sectionsIndex + 6) : this.ranges.length() / 4;
        int rangesIndex = findRangesOffset(codePoint, rangesPosition, rangesLimit);
        int b1 = this.ranges.charAt(rangesIndex + 1);
        if (0 <= b1) {
            z = b1 < 64;
        } else {
            z = false;
        }
        if (!z) {
            if (64 <= b1) {
                z2 = b1 < 80;
            } else {
                z2 = false;
            }
            if (!z2) {
                if (80 <= b1) {
                    z3 = b1 < 96;
                } else {
                    z3 = false;
                }
                if (z3) {
                    int b2 = this.ranges.charAt(rangesIndex + 2);
                    int b3 = this.ranges.charAt(rangesIndex + 3);
                    int codepointDelta = ((b1 & 15) << 14) | (b2 << 7) | b3;
                    sink.writeUtf8CodePoint(codePoint + codepointDelta);
                    return true;
                }
                if (b1 != 119) {
                    if (b1 == 120) {
                        sink.writeUtf8CodePoint(codePoint);
                        return true;
                    }
                    if (b1 == 121) {
                        sink.writeUtf8CodePoint(codePoint);
                        return false;
                    }
                    if (b1 == 122) {
                        sink.writeByte(this.ranges.charAt(rangesIndex + 2));
                        return true;
                    }
                    if (b1 == 123) {
                        sink.writeByte(this.ranges.charAt(rangesIndex + 2) | 128);
                        return true;
                    }
                    if (b1 == 124) {
                        sink.writeByte(this.ranges.charAt(rangesIndex + 2));
                        sink.writeByte(this.ranges.charAt(rangesIndex + 3));
                        return true;
                    }
                    if (b1 == 125) {
                        sink.writeByte(this.ranges.charAt(rangesIndex + 2) | 128);
                        sink.writeByte(this.ranges.charAt(rangesIndex + 3));
                        return true;
                    }
                    if (b1 == 126) {
                        sink.writeByte(this.ranges.charAt(rangesIndex + 2));
                        sink.writeByte(this.ranges.charAt(rangesIndex + 3) | 128);
                        return true;
                    }
                    if (b1 == 127) {
                        sink.writeByte(this.ranges.charAt(rangesIndex + 2) | 128);
                        sink.writeByte(this.ranges.charAt(rangesIndex + 3) | 128);
                        return true;
                    }
                    throw new IllegalStateException(("unexpected rangesIndex for " + codePoint).toString());
                }
                return true;
            }
            int b22 = this.ranges.charAt(rangesIndex + 2);
            int b32 = this.ranges.charAt(rangesIndex + 3);
            int codepointDelta2 = ((b1 & 15) << 14) | (b22 << 7) | b32;
            sink.writeUtf8CodePoint(codePoint - codepointDelta2);
            return true;
        }
        int beginIndex = IdnaMappingTableKt.read14BitInt(this.ranges, rangesIndex + 2);
        sink.writeUtf8(this.mappings, beginIndex, beginIndex + b1);
        return true;
    }

    private final int findSectionsIndex(int codePoint) {
        int i;
        int target = (codePoint & 2097024) >> 7;
        int limit$iv = this.sections.length() / 4;
        int low$iv = 0;
        int high$iv = limit$iv - 1;
        while (true) {
            if (low$iv <= high$iv) {
                int mid$iv = (low$iv + high$iv) / 2;
                int entryIndex = mid$iv * 4;
                int b0b1 = IdnaMappingTableKt.read14BitInt(this.sections, entryIndex);
                int compareResult$iv = Intrinsics.compare(target, b0b1);
                if (compareResult$iv >= 0) {
                    if (compareResult$iv <= 0) {
                        i = mid$iv;
                        break;
                    }
                    low$iv = mid$iv + 1;
                } else {
                    high$iv = mid$iv - 1;
                }
            } else {
                i = (-low$iv) - 1;
                break;
            }
        }
        int offset = i;
        return offset >= 0 ? offset * 4 : ((-offset) - 2) * 4;
    }

    private final int findRangesOffset(int codePoint, int position, int limit) {
        int i;
        int target = codePoint & 127;
        int low$iv = position;
        int high$iv = limit - 1;
        while (true) {
            if (low$iv <= high$iv) {
                int mid$iv = (low$iv + high$iv) / 2;
                int entryIndex = mid$iv * 4;
                int b0 = this.ranges.charAt(entryIndex);
                int compareResult$iv = Intrinsics.compare(target, b0);
                if (compareResult$iv >= 0) {
                    if (compareResult$iv <= 0) {
                        i = mid$iv;
                        break;
                    }
                    low$iv = mid$iv + 1;
                } else {
                    high$iv = mid$iv - 1;
                }
            } else {
                i = (-low$iv) - 1;
                break;
            }
        }
        int offset = i;
        return offset >= 0 ? offset * 4 : ((-offset) - 2) * 4;
    }
}
