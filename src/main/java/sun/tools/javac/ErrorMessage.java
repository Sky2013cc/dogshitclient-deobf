package sun.tools.javac;

@Deprecated
/* loaded from: target.jar:sun/tools/javac/ErrorMessage.class */
final class ErrorMessage {
    long where;
    String message;
    ErrorMessage next;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ErrorMessage(long j, String str) {
        this.where = j;
        this.message = str;
    }
}
