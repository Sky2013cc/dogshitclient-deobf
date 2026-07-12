package org.apache.pdfbox.cos;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: target.jar:org/apache/pdfbox/cos/COSBase.class */
public abstract class COSBase implements COSObjectable {
    private boolean direct;
    private COSObjectKey key;

    public abstract void accept(ICOSVisitor iCOSVisitor) throws IOException;

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this;
    }

    public boolean isDirect() {
        return this.direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }

    public COSObjectKey getKey() {
        return this.key;
    }

    public void setKey(COSObjectKey key) {
        this.key = key;
    }
}
