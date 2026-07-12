package jdk.nashorn.internal.codegen;

/* loaded from: target.jar:jdk/nashorn/internal/codegen/CompilationException.class */
public class CompilationException extends RuntimeException {
    /* JADX INFO: Access modifiers changed from: package-private */
    public CompilationException(String description) {
        super(description);
    }

    CompilationException(Exception cause) {
        super(cause);
    }
}
