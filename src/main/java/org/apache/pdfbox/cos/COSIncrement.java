package org.apache.pdfbox.cos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: target.jar:org/apache/pdfbox/cos/COSIncrement.class */
public class COSIncrement implements Iterable<COSBase> {
    private final COSUpdateInfo incrementOrigin;
    private final Set<COSBase> objects = new LinkedHashSet();
    private final Set<COSBase> excluded = new HashSet();
    private final Set<COSObject> processedObjects = new HashSet();
    private boolean initialized = false;

    public COSIncrement(COSUpdateInfo incrementOrigin) {
        this.incrementOrigin = incrementOrigin;
    }

    private boolean collect(COSBase base) {
        if (contains(base)) {
            return false;
        }
        if (base instanceof COSDictionary) {
            return collect((COSDictionary) base);
        }
        if (base instanceof COSObject) {
            return collect((COSObject) base);
        }
        if (base instanceof COSArray) {
            return collect((COSArray) base);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean collect(COSDictionary dictionary) {
        COSUpdateState updateState = dictionary.getUpdateState();
        if (!isExcluded(dictionary) && !contains(dictionary) && updateState.isUpdated()) {
            add(dictionary);
        }
        boolean childDemandsParentUpdate = false;
        for (COSBase cOSBase : dictionary.getValues()) {
            if ((cOSBase instanceof COSUpdateInfo) && !contains(cOSBase)) {
                COSUpdateInfo updatableEntry = (COSUpdateInfo) cOSBase;
                COSUpdateState entryUpdateState = updatableEntry.getUpdateState();
                updateDifferentOrigin(entryUpdateState);
                if (updatableEntry.isNeedToBeUpdated() && ((!(cOSBase instanceof COSObject) && cOSBase.isDirect()) || (cOSBase instanceof COSArray))) {
                    exclude(cOSBase);
                    childDemandsParentUpdate = true;
                }
                childDemandsParentUpdate = collect(cOSBase) || childDemandsParentUpdate;
            }
        }
        if (isExcluded(dictionary)) {
            return childDemandsParentUpdate;
        }
        if (childDemandsParentUpdate && !contains(dictionary)) {
            add(dictionary);
            return false;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean collect(COSArray array) {
        COSUpdateState updateState = array.getUpdateState();
        boolean childDemandsParentUpdate = updateState.isUpdated();
        Iterator<COSBase> it = array.iterator();
        while (it.hasNext()) {
            COSBase next = it.next();
            if ((next instanceof COSUpdateInfo) && !contains(next)) {
                COSUpdateState entryUpdateState = ((COSUpdateInfo) next).getUpdateState();
                updateDifferentOrigin(entryUpdateState);
                childDemandsParentUpdate = collect(next) || childDemandsParentUpdate;
            }
        }
        return childDemandsParentUpdate;
    }

    private boolean collect(COSObject object) {
        if (contains(object)) {
            return false;
        }
        addProcessedObject(object);
        COSUpdateState updateState = object.getUpdateState();
        updateDifferentOrigin(updateState);
        COSUpdateInfo actual = null;
        if (updateState.isUpdated() || object.isDereferenced()) {
            COSObjectable object2 = object.getObject();
            if (object2 instanceof COSUpdateInfo) {
                actual = (COSUpdateInfo) object2;
            }
        }
        if (actual == null || contains(actual.getCOSObject())) {
            return false;
        }
        boolean childDemandsParentUpdate = false;
        COSUpdateState actualUpdateState = actual.getUpdateState();
        if (actualUpdateState.isUpdated()) {
            childDemandsParentUpdate = true;
        }
        exclude(actual.getCOSObject());
        boolean childDemandsParentUpdate2 = collect(actual.getCOSObject()) || childDemandsParentUpdate;
        if (updateState.isUpdated() || childDemandsParentUpdate2) {
            add(actual.getCOSObject());
            return false;
        }
        return false;
    }

    public boolean contains(COSBase base) {
        return this.objects.contains(base) || ((base instanceof COSObject) && this.processedObjects.contains((COSObject) base));
    }

    private void updateDifferentOrigin(COSUpdateState updateState) {
        if (this.incrementOrigin != null && updateState != null && this.incrementOrigin.getUpdateState().getOriginDocumentState() != updateState.getOriginDocumentState()) {
            updateState.update();
        }
    }

    private void add(COSBase object) {
        if (object != null) {
            this.objects.add(object);
        }
    }

    private void addProcessedObject(COSObject base) {
        if (base != null) {
            this.processedObjects.add(base);
        }
    }

    public COSIncrement exclude(COSBase... base) {
        if (base != null) {
            this.excluded.addAll(Arrays.asList(base));
        }
        return this;
    }

    private boolean isExcluded(COSBase base) {
        return this.excluded.contains(base);
    }

    public Set<COSBase> getObjects() {
        if (!this.initialized && this.incrementOrigin != null) {
            collect(this.incrementOrigin.getCOSObject());
            this.initialized = true;
        }
        return this.objects;
    }

    @Override // java.lang.Iterable
    public Iterator<COSBase> iterator() {
        return getObjects().iterator();
    }
}
