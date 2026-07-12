package net.ccbluex.liquidbounce.utils.attack;

import com.sun.xml.internal.xsom.XSFacet;
import kotlin.Metadata;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: RollingArrayLongBuffer.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fR\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0011"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/attack/RollingArrayLongBuffer;", "", XSFacet.FACET_LENGTH, "", Constants.CTOR, "(I)V", "value", "", "contents", "getContents", "()[J", "currentIndex", "add", "", OperatorName.LINE_TO, "", "getTimestampsSince", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/attack/RollingArrayLongBuffer.class */
public final class RollingArrayLongBuffer {

    @NotNull
    private long[] contents;
    private int currentIndex;

    public RollingArrayLongBuffer(int length) {
        this.contents = new long[length];
    }

    @NotNull
    public final long[] getContents() {
        return this.contents;
    }

    public final void add(long l) {
        this.currentIndex = (this.currentIndex + 1) % this.contents.length;
        this.contents[this.currentIndex] = l;
    }

    public final int getTimestampsSince(long l) {
        int i = 0;
        int length = this.contents.length;
        while (i < length) {
            if (this.contents[this.currentIndex < i ? (this.contents.length - i) + this.currentIndex : this.currentIndex - i] >= l) {
                i++;
            } else {
                return i;
            }
        }
        return this.contents.length;
    }
}
