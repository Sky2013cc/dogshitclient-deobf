package sun.tools.java;

/* loaded from: target.jar:sun/tools/java/BinaryExceptionHandler.class */
public class BinaryExceptionHandler {
    public int startPC;
    public int endPC;
    public int handlerPC;
    public ClassDeclaration exceptionClass;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinaryExceptionHandler(int i, int i2, int i3, ClassDeclaration classDeclaration) {
        this.startPC = i;
        this.endPC = i2;
        this.handlerPC = i3;
        this.exceptionClass = classDeclaration;
    }
}
