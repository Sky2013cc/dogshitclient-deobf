package com.sun.tools.example.debug.tty;

import com.sun.jdi.ReferenceType;
import com.sun.jdi.event.ClassPrepareEvent;
import com.sun.jdi.request.ClassPrepareRequest;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.request.ExceptionRequest;
import java.util.ArrayList;

/* loaded from: target.jar:com/sun/tools/example/debug/tty/EventRequestSpec.class */
abstract class EventRequestSpec {
    final ReferenceTypeSpec refSpec;
    int suspendPolicy = 2;
    EventRequest resolved = null;
    ClassPrepareRequest prepareRequest = null;

    abstract EventRequest resolveEventRequest(ReferenceType referenceType) throws Exception;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventRequestSpec(ReferenceTypeSpec referenceTypeSpec) {
        this.refSpec = referenceTypeSpec;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized EventRequest resolve(ClassPrepareEvent classPrepareEvent) throws Exception {
        if (this.resolved == null && this.prepareRequest != null && this.prepareRequest.equals(classPrepareEvent.request())) {
            this.resolved = resolveEventRequest(classPrepareEvent.referenceType());
            this.prepareRequest.disable();
            Env.vm().eventRequestManager().deleteEventRequest(this.prepareRequest);
            this.prepareRequest = null;
            if ((this.refSpec instanceof PatternReferenceTypeSpec) && !((PatternReferenceTypeSpec) this.refSpec).isUnique()) {
                this.resolved = null;
                this.prepareRequest = this.refSpec.createPrepareRequest();
                this.prepareRequest.enable();
            }
        }
        return this.resolved;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void remove() {
        if (isResolved()) {
            Env.vm().eventRequestManager().deleteEventRequest(resolved());
        }
        if (this.refSpec instanceof PatternReferenceTypeSpec) {
            PatternReferenceTypeSpec patternReferenceTypeSpec = (PatternReferenceTypeSpec) this.refSpec;
            if (!patternReferenceTypeSpec.isUnique()) {
                ArrayList arrayList = new ArrayList();
                for (ExceptionRequest exceptionRequest : Env.vm().eventRequestManager().exceptionRequests()) {
                    if (patternReferenceTypeSpec.matches(exceptionRequest.exception())) {
                        arrayList.add(exceptionRequest);
                    }
                }
                Env.vm().eventRequestManager().deleteEventRequests(arrayList);
            }
        }
    }

    private EventRequest resolveAgainstPreparedClasses() throws Exception {
        for (ReferenceType referenceType : Env.vm().allClasses()) {
            if (referenceType.isPrepared() && this.refSpec.matches(referenceType)) {
                this.resolved = resolveEventRequest(referenceType);
            }
        }
        return this.resolved;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized EventRequest resolveEagerly() throws Exception {
        try {
            if (this.resolved == null) {
                this.prepareRequest = this.refSpec.createPrepareRequest();
                this.prepareRequest.enable();
                resolveAgainstPreparedClasses();
                if (this.resolved != null) {
                    this.prepareRequest.disable();
                    Env.vm().eventRequestManager().deleteEventRequest(this.prepareRequest);
                    this.prepareRequest = null;
                }
            }
            if ((this.refSpec instanceof PatternReferenceTypeSpec) && !((PatternReferenceTypeSpec) this.refSpec).isUnique()) {
                this.resolved = null;
                if (this.prepareRequest == null) {
                    this.prepareRequest = this.refSpec.createPrepareRequest();
                    this.prepareRequest.enable();
                }
            }
        } catch (VMNotConnectedException e) {
        }
        return this.resolved;
    }

    EventRequest resolved() {
        return this.resolved;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isResolved() {
        return this.resolved != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isJavaIdentifier(String str) {
        if (str.length() == 0) {
            return false;
        }
        int codePointAt = str.codePointAt(0);
        if (!Character.isJavaIdentifierStart(codePointAt)) {
            return false;
        }
        int charCount = Character.charCount(codePointAt);
        while (true) {
            int i = charCount;
            if (i < str.length()) {
                int codePointAt2 = str.codePointAt(i);
                if (Character.isJavaIdentifierPart(codePointAt2)) {
                    charCount = i + Character.charCount(codePointAt2);
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String errorMessageFor(Exception exc) {
        if (exc instanceof IllegalArgumentException) {
            return MessageOutput.format("Invalid command syntax");
        }
        if (exc instanceof RuntimeException) {
            throw ((RuntimeException) exc);
        }
        return MessageOutput.format("Internal error; unable to set", this.refSpec.toString());
    }
}
