package com.sun.xml.internal.rngom.binary;

import javax.xml.namespace.QName;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/xml/internal/rngom/binary/RestrictionViolationException.class */
final class RestrictionViolationException extends Exception {
    private String messageId;
    private Locator loc;
    private QName name;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RestrictionViolationException(String messageId) {
        this.messageId = messageId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RestrictionViolationException(String messageId, QName name) {
        this.messageId = messageId;
        this.name = name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getMessageId() {
        return this.messageId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Locator getLocator() {
        return this.loc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void maybeSetLocator(Locator loc) {
        if (this.loc == null) {
            this.loc = loc;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public QName getName() {
        return this.name;
    }
}
