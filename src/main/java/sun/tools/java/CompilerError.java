package sun.tools.java;

/* loaded from: target.jar:sun/tools/java/CompilerError.class */
public class CompilerError extends Error {
    Throwable e;

    public CompilerError(String str) {
        super(str);
        this.e = this;
    }

    public CompilerError(Exception exc) {
        super(exc.getMessage());
        this.e = exc;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        if (this.e == this) {
            super.printStackTrace();
        } else {
            this.e.printStackTrace();
        }
    }
}
