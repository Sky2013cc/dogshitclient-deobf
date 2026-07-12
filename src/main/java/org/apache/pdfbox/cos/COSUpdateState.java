package org.apache.pdfbox.cos;

import java.util.Iterator;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: target.jar:org/apache/pdfbox/cos/COSUpdateState.class */
public class COSUpdateState {
    private final COSUpdateInfo updateInfo;
    private COSDocumentState originDocumentState = null;
    private boolean updated = false;

    public COSUpdateState(COSUpdateInfo updateInfo) {
        this.updateInfo = updateInfo;
    }

    public void setOriginDocumentState(COSDocumentState originDocumentState) {
        setOriginDocumentState(originDocumentState, false);
    }

    private void setOriginDocumentState(COSDocumentState originDocumentState, boolean dereferencing) {
        if (this.originDocumentState != null || originDocumentState == null) {
            return;
        }
        this.originDocumentState = originDocumentState;
        if (!dereferencing) {
            update();
        }
        if (this.updateInfo instanceof COSDictionary) {
            COSDictionary dictionary = (COSDictionary) this.updateInfo;
            for (COSObjectable cOSObjectable : dictionary.getValues()) {
                if (cOSObjectable instanceof COSUpdateInfo) {
                    ((COSUpdateInfo) cOSObjectable).getUpdateState().setOriginDocumentState(originDocumentState, dereferencing);
                }
            }
            return;
        }
        if (this.updateInfo instanceof COSArray) {
            COSArray array = (COSArray) this.updateInfo;
            Iterator<COSBase> it = array.iterator();
            while (it.hasNext()) {
                COSObjectable cOSObjectable2 = (COSBase) it.next();
                if (cOSObjectable2 instanceof COSUpdateInfo) {
                    ((COSUpdateInfo) cOSObjectable2).getUpdateState().setOriginDocumentState(originDocumentState, dereferencing);
                }
            }
            return;
        }
        if (this.updateInfo instanceof COSObject) {
            COSObject object = (COSObject) this.updateInfo;
            if (object.isDereferenced()) {
                COSObjectable object2 = object.getObject();
                if (object2 instanceof COSUpdateInfo) {
                    ((COSUpdateInfo) object2).getUpdateState().setOriginDocumentState(originDocumentState, dereferencing);
                }
            }
        }
    }

    public COSDocumentState getOriginDocumentState() {
        return this.originDocumentState;
    }

    boolean isAcceptingUpdates() {
        return this.originDocumentState != null && this.originDocumentState.isAcceptingUpdates();
    }

    public boolean isUpdated() {
        return this.updated;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void update() {
        update(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void update(boolean updated) {
        if (isAcceptingUpdates()) {
            this.updated = updated;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public void update(COSBase cOSBase) {
        update();
        if (cOSBase instanceof COSUpdateInfo) {
            ((COSUpdateInfo) cOSBase).getUpdateState().setOriginDocumentState(this.originDocumentState);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void update(COSArray children) {
        update((Iterable<COSBase>) children);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void update(Iterable<COSBase> children) {
        update();
        if (children == null) {
            return;
        }
        for (COSObjectable cOSObjectable : children) {
            if (cOSObjectable instanceof COSUpdateInfo) {
                ((COSUpdateInfo) cOSObjectable).getUpdateState().setOriginDocumentState(this.originDocumentState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public void dereferenceChild(COSBase cOSBase) {
        if (cOSBase instanceof COSUpdateInfo) {
            ((COSUpdateInfo) cOSBase).getUpdateState().setOriginDocumentState(this.originDocumentState, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public COSIncrement toIncrement() {
        return new COSIncrement(this.updateInfo);
    }
}
