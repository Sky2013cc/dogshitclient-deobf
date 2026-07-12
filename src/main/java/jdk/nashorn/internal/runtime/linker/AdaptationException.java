package jdk.nashorn.internal.runtime.linker;

import jdk.nashorn.internal.runtime.linker.AdaptationResult;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/linker/AdaptationException.class */
final class AdaptationException extends Exception {
    private final AdaptationResult adaptationResult;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdaptationException(AdaptationResult.Outcome outcome, String classList) {
        super(null, null, false, false);
        this.adaptationResult = new AdaptationResult(outcome, classList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdaptationResult getAdaptationResult() {
        return this.adaptationResult;
    }
}
