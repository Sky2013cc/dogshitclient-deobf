package okio.internal;

import kotlin.Metadata;

/* compiled from: ZipFiles.kt */
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018��2\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lokio/internal/EocdRecord;", "", "entryCount", "", "centralDirectoryOffset", "commentByteCount", "", "(JJI)V", "getCentralDirectoryOffset", "()J", "getCommentByteCount", "()I", "getEntryCount", "okio"})
/* loaded from: target.jar:okio/internal/EocdRecord.class */
final class EocdRecord {
    private final long entryCount;
    private final long centralDirectoryOffset;
    private final int commentByteCount;

    public EocdRecord(long entryCount, long centralDirectoryOffset, int commentByteCount) {
        this.entryCount = entryCount;
        this.centralDirectoryOffset = centralDirectoryOffset;
        this.commentByteCount = commentByteCount;
    }

    public final long getEntryCount() {
        return this.entryCount;
    }

    public final long getCentralDirectoryOffset() {
        return this.centralDirectoryOffset;
    }

    public final int getCommentByteCount() {
        return this.commentByteCount;
    }
}
