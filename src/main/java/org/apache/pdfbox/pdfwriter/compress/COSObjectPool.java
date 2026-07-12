package org.apache.pdfbox.pdfwriter.compress;

import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSObjectKey;

/* loaded from: target.jar:org/apache/pdfbox/pdfwriter/compress/COSObjectPool.class */
public class COSObjectPool {
    private final Map<COSObjectKey, COSBase> keyPool = new HashMap();
    private final Map<COSBase, COSObjectKey> objectPool = new HashMap();
    private long highestXRefObjectNumber;

    public COSObjectPool(long highestXRefObjectNumber) {
        this.highestXRefObjectNumber = 0L;
        this.highestXRefObjectNumber = Math.max(this.highestXRefObjectNumber, highestXRefObjectNumber);
    }

    public COSObjectKey put(COSObjectKey key, COSBase object) {
        if (object == null) {
            return null;
        }
        if (contains(object) && getKey(object).equals(key)) {
            return null;
        }
        COSObjectKey actualKey = key;
        if (actualKey == null || contains(actualKey)) {
            this.highestXRefObjectNumber++;
            actualKey = new COSObjectKey(this.highestXRefObjectNumber, 0);
            object.setKey(actualKey);
        } else {
            this.highestXRefObjectNumber = Math.max(key.getNumber(), this.highestXRefObjectNumber);
        }
        this.keyPool.put(actualKey, object);
        this.objectPool.put(object, actualKey);
        return actualKey;
    }

    public COSObjectKey getKey(COSBase object) {
        COSObjectKey key = null;
        if (object instanceof COSObject) {
            key = this.objectPool.get(((COSObject) object).getObject());
        }
        if (key == null) {
            return this.objectPool.get(object);
        }
        return key;
    }

    public boolean contains(COSObjectKey key) {
        return this.keyPool.containsKey(key);
    }

    public COSBase getObject(COSObjectKey key) {
        return this.keyPool.get(key);
    }

    public boolean contains(COSBase object) {
        return ((object instanceof COSObject) && this.objectPool.containsKey(((COSObject) object).getObject())) || this.objectPool.containsKey(object);
    }

    public long getHighestXRefObjectNumber() {
        return this.highestXRefObjectNumber;
    }
}
