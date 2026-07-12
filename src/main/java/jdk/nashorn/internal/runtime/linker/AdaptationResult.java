package jdk.nashorn.internal.runtime.linker;

import jdk.nashorn.internal.runtime.ECMAErrors;
import jdk.nashorn.internal.runtime.ECMAException;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/linker/AdaptationResult.class */
final class AdaptationResult {
    static final AdaptationResult SUCCESSFUL_RESULT = new AdaptationResult(Outcome.SUCCESS, "");
    private final Outcome outcome;
    private final String[] messageArgs;

    /* loaded from: target.jar:jdk/nashorn/internal/runtime/linker/AdaptationResult$Outcome.class */
    enum Outcome {
        SUCCESS,
        ERROR_FINAL_CLASS,
        ERROR_NON_PUBLIC_CLASS,
        ERROR_NO_ACCESSIBLE_CONSTRUCTOR,
        ERROR_MULTIPLE_SUPERCLASSES,
        ERROR_NO_COMMON_LOADER,
        ERROR_FINAL_FINALIZER,
        ERROR_OTHER
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdaptationResult(Outcome outcome, String... messageArgs) {
        this.outcome = outcome;
        this.messageArgs = messageArgs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Outcome getOutcome() {
        return this.outcome;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ECMAException typeError() {
        return ECMAErrors.typeError("extend." + this.outcome, this.messageArgs);
    }
}
