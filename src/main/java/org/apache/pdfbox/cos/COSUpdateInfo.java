package org.apache.pdfbox.cos;

import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: target.jar:org/apache/pdfbox/cos/COSUpdateInfo.class */
public interface COSUpdateInfo extends COSObjectable {
    COSUpdateState getUpdateState();

    default boolean isNeedToBeUpdated() {
        return getUpdateState().isUpdated();
    }

    default void setNeedToBeUpdated(boolean flag) {
        getUpdateState().update(flag);
    }

    default COSIncrement toIncrement() {
        return getUpdateState().toIncrement();
    }
}
