package org.apache.pdfbox.cos;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* loaded from: target.jar:org/apache/pdfbox/cos/COSObject.class */
public class COSObject extends COSBase implements COSUpdateInfo {
    private COSBase baseObject;
    private long objectNumber;
    private int generationNumber;
    private ICOSParser parser;
    private boolean isDereferenced;
    private final COSUpdateState updateState;
    private static final Log LOG = LogFactory.getLog(COSObject.class);

    public COSObject(COSBase object) {
        this.isDereferenced = false;
        this.updateState = new COSUpdateState(this);
        this.baseObject = object;
        this.isDereferenced = true;
    }

    public COSObject(COSBase object, COSObjectKey objectKey) {
        this(objectKey, (ICOSParser) null);
        this.baseObject = object;
        this.isDereferenced = true;
    }

    public COSObject(COSBase object, ICOSParser parser) {
        this.isDereferenced = false;
        this.updateState = new COSUpdateState(this);
        this.baseObject = object;
        this.isDereferenced = object != null;
        this.parser = parser;
    }

    public COSObject(COSObjectKey key, ICOSParser parser) {
        this.isDereferenced = false;
        this.updateState = new COSUpdateState(this);
        this.parser = parser;
        this.objectNumber = key.getNumber();
        this.generationNumber = key.getGeneration();
        setKey(key);
    }

    public boolean isObjectNull() {
        return this.baseObject == null;
    }

    public COSBase getObject() {
        if (!this.isDereferenced && this.parser != null) {
            try {
                this.isDereferenced = true;
                this.baseObject = this.parser.dereferenceCOSObject(this);
                getUpdateState().dereferenceChild(this.baseObject);
            } catch (IOException e) {
                LOG.error("Can't dereference " + this, e);
            } finally {
                this.parser = null;
            }
        }
        return this.baseObject;
    }

    public final void setToNull() {
        if (this.baseObject != null) {
            getUpdateState().update();
        }
        this.baseObject = COSNull.NULL;
        this.parser = null;
    }

    public String toString() {
        return "COSObject{" + this.objectNumber + ", " + this.generationNumber + "}";
    }

    public long getObjectNumber() {
        return this.objectNumber;
    }

    public int getGenerationNumber() {
        return this.generationNumber;
    }

    @Override // org.apache.pdfbox.cos.COSBase
    public void accept(ICOSVisitor visitor) throws IOException {
        COSBase object = getObject();
        if (object != null) {
            object.accept(visitor);
        } else {
            COSNull.NULL.accept(visitor);
        }
    }

    public boolean isDereferenced() {
        return this.isDereferenced;
    }

    @Override // org.apache.pdfbox.cos.COSUpdateInfo
    public COSUpdateState getUpdateState() {
        return this.updateState;
    }
}
